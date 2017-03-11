package edmondskarp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class Node {
	
	private List<Edge> edges;
	//yeahh not fixing the duplicate data structures
	public HashMap<Integer, Edge> edgefoo = new HashMap<Integer, Edge>();
	public Integer discovery = 0; //Infinity! Not.
	public int label;
	
	public Integer pi = null;
	public int by = 0;
	public Color color = Color.WHITE;
	
	public Node(int label){
		edges = new LinkedList<Edge>();		
		this.label = label;
	}
	
	
	public void addEdge(int destination, int capacity){
		Edge temp = new Edge(this.label,destination,capacity);
		edges.add(temp);
		edgefoo.put(destination,temp);
	}
	
	public void discover(int pi, int d){
		this.color = Color.GRAY;
		this.pi = pi;
		this.discovery = d;
	}
	
	public List<Edge> getEdges(){
		return edges;
	}
	
	public void reset(){
		this.pi = null;
		this.color = Color.WHITE;
	}
	
	public void print(){
		ListIterator<Edge> li = edges.listIterator();
		if(li.hasNext()){
			while(li.hasNext()){
				li.next().print();
			}
		}
		else{
			System.out.print("SINK");
		}
	}
	
}
