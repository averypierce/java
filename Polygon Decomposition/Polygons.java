package polygons;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
//import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


/*
 * Avery VanKirk
 * CSCI 405 Fall 2016
 * Polygon Decomposition with Dynamic Programming
 * 
 */

public class Polygons {

	
	public static List<Float[]> verts;	
	public static int n;
	public static Double[][] memo;
	public static int[][] chords;
	
	public static List<int[]> output;
	
	public static void main(String[] args) {
		
		verts = loadGraph("polygon2.txt",verts);
		n = verts.size();
		output = new LinkedList<int[]>();

		memo = new Double[n][n];
		chords = new int[n][n];
		
		n--;
		
		for(int i = 0;i < n;i++){
			Arrays.fill(memo[i], Double.POSITIVE_INFINITY);
		}

		Double minsum = polygoner(1,n);
		
		pt(1,n);

		System.out.printf("Minimal sum of triangle perimeters = %.3f \n",minsum);
		System.out.println(output.size() + " chords are:");
		for(int i = 0;i<output.size();i++){
			System.out.println(output.get(i)[0]+ "   " + output.get(i)[1]);
		}
	
	}

	public static Double polygoner(int i, int j){
		
		Double min = Double.POSITIVE_INFINITY;
		int min2 = 0;
		if(i == j)
			return (double) 0;
		
		if(memo[i][j] < Double.POSITIVE_INFINITY)
			return memo[i][j];
		
		else{	
			for(int k = i;k <= j - 1;k++){
				Double q = polygoner(i,k) 
						+ polygoner(k+1 ,j) + perimeter(i-1,j,k);
				if(q < min){
					min = q;
					 min2 = k;
				}
			}
		}
		memo[i][j] = min;
		chords[i][j] = min2;
		return memo[i][j];
	}
	
	public static void pt(int i,int j){
		
		if(i != j){
			int[] temp = {i-1,j};
			output.add(temp);
			pt(chords[i][j]+1, j);
			pt(i,chords[i][j]);
			
		}
	}
	
	public static double perimeter(int i, int j, int k){
		
		float x1 = verts.get(i)[0];
		float x2 = verts.get(j)[0];
		float x3 = verts.get(k)[0];
		
		float y1 = verts.get(i)[1];
		float y2 = verts.get(j)[1];
		float y3 = verts.get(k)[1];
				
		double perimeter = Math.sqrt( Math.pow(x2-x1,2) + Math.pow(y2-y1,2) );
		
		perimeter += Math.sqrt( Math.pow(x3-x1,2) + Math.pow(y3-y1,2) );
		perimeter += Math.sqrt( Math.pow(x3-x2,2) + Math.pow(y3-y2,2) );
		return perimeter;
	}
	
	
		///Readin from file///
		public static List<Float[]> loadGraph(String filename,List<Float[]> verts){
			
			verts = new ArrayList<Float[]>();
			
			Scanner myScanner = new Scanner(System.in);
			try {
				myScanner = new Scanner(new File(filename));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			String[] current;
			while (myScanner.hasNext()) {
				current = myScanner.nextLine().trim().split("\\s+");
				Float[] temp = {Float.parseFloat(current[0]),Float.parseFloat(current[1])};
				verts.add(temp);
			}
			return verts;
		}
		
}


