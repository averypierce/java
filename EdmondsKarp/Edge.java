package edmondskarp;

public class Edge {
	
	public int source;
	public int destination;
	
	public int capacity;
	public int load = 0; //how much edge is using from total capacity
	
	public Edge(int source, int destination,int capacity){
		this.source = source;
		this.destination = destination;
		this.capacity = capacity;
	}
	
	public void addFlow(int flow){
		if((capacity-load) >= flow)
			load += flow;
	}
	
	public boolean hasCapacity(){
		if(capacity - load > 0)
			return true;
		else
			return false;
	}
	
	public int availCap(){
		return capacity - load;
	}

	public void print(){
		System.out.print(destination);
		System.out.print("c");
		System.out.print(capacity);
		System.out.print(", ");
	}
}
