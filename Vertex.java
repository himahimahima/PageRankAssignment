package graphs.freshcode;

import java.util.Iterator;

public class Vertex {
	/*
	 * class Vertex represents a vertex in a graph, containing the
	 * vertex label and other fields
	 * 
	 * contains a list of the vertex's neighbours, giving an adjacency
	 * list representation for the graph
	 */

	Integer id; // Vertex label
	VertexIDList adjs; // List of neighbours
	Boolean marked;  // Used to indicate previously visited
	Integer num;  // Used by Drozdek to indicate order of visit in traversal
	Integer visits = 0;
	double weight = 0;
	
	public Vertex(Integer n) {
		// Constructor
		id = n;
		marked = false;
		adjs = new VertexIDList();
		num = 0;
	}
	
	public void setMarked() {
		marked = true;
	}

	public void setUnmarked() {
		marked = false;
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public void addAdj (Integer n) {
		// Adds a neighbour n to the current vertex 
		adjs.push(n);
	}
	
	public void addAdj (Integer n, Double w) {
		// Adds a neighbour n to the current vertex 
		adjs.push(n,w);
	}
	
	public VertexIDList getAdjs() {
		return adjs;
	}

	public Integer getID() {
		return id;
	}
	
	public Integer getNum() {
		return num;
	}
	

	public void setNum(Integer n) {
		num = n;
	}
	
	public Double getWeight() {
		// PRE: -
		// POST: returns the value of attribute weight
		return weight;
	}
	
	public void setWeight(Double n) {
		// PRE: -
		// POST: sets the attribute of weight to n
		weight = n;
	}
	
	public Integer getVisits() {
		// PRE: -
		// POST: returns the value of attribute visits
		return visits;
	}
	
	public void setVisits(Integer n) {
		// PRE: -
		// POST: sets the attribute of visits to n
		visits = n;
	}
	
	public Integer getRandomLink() {
		// ADDED
		// PRE: vertex has non-empty adjacency list
		// POST: returns a vertex ID randomly selected from adjacent vertices,
		//         according to distribution of edge weights
		// adapted from Sedgewick: http://introcs.cs.princeton.edu/java/16pagerank/RandomSurfer.java.html
		// TODO
		int random = (int)(Math.random()*adjs.getWeightTotal())+1;
		Iterator<Integer> it = adjs.iterator();
		
		while(it.hasNext()){
			int thisVertex = it.next();
			if(random<=adjs.get(thisVertex))
					return thisVertex;
			else
				random = (int)(random - adjs.get(thisVertex));
		}
		return -1; //returns -1 if something went wrong (because I am forced to return something)
	}
	
	public void print() {
		System.out.print("Node " + id + ":");
		adjs.print();
	}
	
	public static void main(String[] args) {
		Vertex v = new Vertex(1);
		v.addAdj(2);
		v.addAdj(3);
		v.print();

		System.out.println("testing iterator ...");
		VertexIDList vAdjs = v.getAdjs();
		Iterator<Integer> it = vAdjs.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
