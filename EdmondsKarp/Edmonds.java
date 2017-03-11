package edmondskarp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.lang.Math;

//Avery vanKirk 2016

public class Edmonds {
	
	public static List<Node> nodes;
	public static int MAXFLOW = 0;
	public static int mincutcap = 0;
	
	//java wont let me name con continue
	public static boolean con = true;
	public static int flow = 0;
	public static int fakeInfinity = 99999999;
	
	public static int MAXCAP = 0;
	
	public static void main(String[] args) {

		nodes = loadGraph("g2.txt");
		printAdjList();
		
		while(con){
			flow = fakeInfinity;
			augbfs();
		}
		System.out.println("Max flow: " + MAXFLOW);
		//System.out.println("Min cut capacity: " + MAXFLOW);
		System.out.println("Min cut capacity: " + mincutcap);
	}
	
	public static void mincut(){
		
	}
	
	public static void augbfs(){
		
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).reset();
		}
		
		List<Edge> curEdges;
		List<Node> queue = new LinkedList<Node>();
		
		Node u = nodes.get(0);
		u.color = Color.GRAY; //So we dont visit start node again
		queue.add(u);
		int d = 0;
		
		while (queue.size() > 0) {
			u = ((LinkedList<Node>) queue).pop();
			curEdges = u.getEdges();
			ListIterator<Edge> li = curEdges.listIterator();
			while (li.hasNext()) {
				Edge edge = li.next();
				int k = edge.destination;
				
				if (nodes.get(k).color == Color.WHITE && edge.hasCapacity()){
					d += 1;
					nodes.get(k).discover(u.label,d);
					queue.add(nodes.get(k));
				}
			}
		}
		
		List<Integer> path = new ArrayList<Integer>();
		getPath(nodes,0,nodes.size()-1,path);
	
		for(int i = 0;i < path.size()-1;i++){
			nodes.get(path.get(i)).edgefoo.get(path.get(i+1)).addFlow(flow);
		}
		
		
		
		System.out.println("=================");
		ListIterator<Integer> li = path.listIterator();
		while (li.hasNext()){
			System.out.println(li.next());
		}
		
		if(flow != fakeInfinity){
			System.out.println("========= " + flow + " ==========");
			MAXFLOW += flow;
		}
		
		//If we dont get to last node, we are done. print min cut.
		List<Integer> Tcut = new ArrayList<Integer>();
		if((nodes.get(nodes.size()-1).color == Color.WHITE)){
			int k = 0;
			System.out.print("Min cut (s,t) is:\nS:\n");
			for(int i = 0;i <= nodes.size()-1;i++){
				
				if(nodes.get(i).color == Color.GRAY){
					k++;
					System.out.format("%2d ",i);
					if(k % 10 == 0)
						System.out.print("\n");
				}
					
				else
					Tcut.add(i);
			}
			//System.out.print(nodes.get(5).color);
			System.out.print("\nT: " );
			for(int i = 0; i < Tcut.size();i++){
				if(i % 10 == 0)
					System.out.print("\n");
				System.out.format("%2d ",Tcut.get(i));
				
				
			}
			System.out.println("\n=================");
		
			//actually do the mincut counting i guess
			for(int i = 0;i <= nodes.size()-1;i++){
				ListIterator<Edge> list = nodes.get(i).getEdges().listIterator();
				while(list.hasNext()){
					Edge temp = list.next();
					
					if(nodes.get(temp.source).color != Color.WHITE && nodes.get(temp.destination).color == Color.WHITE){
						mincutcap += temp.capacity;
					}
				}
				
			}
		}
			
	}
	
	//Recursive traversal of BFS tree
	public static void getPath(List<Node> graph,int s, int t,List<Integer> path){
		
		if(t == s){
			path.add(t);
		}
		else if(graph.get(t).pi == null){
			System.out.println("No more augmenting paths found");
			//System.out.println(t);
			//System.out.println(nodes.get(3).edgefoo.get(5).load);
			//System.out.println(nodes.get(5).pi);
			con = false;
		}
		else{
			getPath(graph,s,graph.get(t).pi,path);
			flow = Math.min(flow,graph.get(graph.get(t).pi).edgefoo.get(t).availCap());
			path.add(t);
		}
		
	}
	
	
	///Boring stuff past here///
	
	public static List<Node> loadGraph(String filename){
		
		Scanner myScanner = new Scanner(System.in);
		try {
			myScanner = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String[] current = myScanner.nextLine().split("\\s+");
		// Create array of nodes
		nodes = new ArrayList<Node>(Integer.parseInt(current[0]));
		for (int i = 0; i < Integer.parseInt(current[0]); i++) {
			nodes.add(new Node(i));
		}
		// Populate nodes with edges from file
		while (myScanner.hasNext()) {
			current = myScanner.nextLine().split("\\s+");
			// System.out.println(Integer.parseInt(current[0]));
			for (int i = 1; i < current.length - 1; i += 2) {
				nodes.get(Integer.parseInt(current[0])).addEdge(Integer.parseInt(current[i]),
						Integer.parseInt(current[i + 1]));
				MAXCAP = Math.max(MAXCAP, Integer.parseInt(current[i + 1]));
			}
		}
		return nodes;
	}
	
	// Print out array to check that it matches input
	public static void printAdjList(){
		for (int i = 0; i < nodes.size(); i++) {
			System.out.print(i);
			System.out.print(": ");
			nodes.get(i).print();
			System.out.print("\n");
		}
	}
	
}
