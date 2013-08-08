package graphs.freshcode;

import java.io.IOException;
import java.util.*;

public class GraphApplic extends Graph {

	// PASS LEVEL
	
	public Integer surfWithJump(Integer v, Integer n, Double jumpThreshold) {
		// PRE: v is vertex to start surf; n >= 0; 0 <= jumpThreshold <= 1.0
		// POST: surfs the graph randomly for n moves, 
		//       choosing adjacent vertex according to distribution of edge weights if random number is below jumpThreshold, 
		//       choosing any vertex uniformly randomly otherwise;
		//       modifies # visits in vertices
		//       returns last visited vertex at end of surfing

		// TODO
		for(int i=0;i<n;i++){
			double random = Math.random();
			
			if(random<jumpThreshold){
				v = getVertex(v).getRandomLink();
				getVertex(v).setVisits(getVertex(v).getVisits()+1);
			}
			else{
				v=(int)(Math.random()*this.getVertexSet().size());
				getVertex(v).setVisits(getVertex(v).getVisits()+1);
			}
		}
		return v;
	}
	
	public ArrayList<Integer> topN(Integer N) {
		// PRE: none
		// POST: returns N vertices with highest number of visits, in order;
		//       returns all vertices if <N in the graph;
		//       returns vertices ranked 1,..,N,N+1,..,N+k if these k have the
		//         same number of visits as vertex N
		
		// TODO
		TreeMap<Integer,Integer> list = new TreeMap<Integer,Integer>();
		NavigableSet<Integer> vertList = this.getVertexSet();
		ArrayList<Integer> topList = new ArrayList<Integer>();
		
		Iterator<Integer> it = vertList.iterator();
		while(it.hasNext()){
			System.out.println("putted");
			int currentV = it.next();
			list.put(getVertex(currentV).getVisits(),currentV);
		}
		System.out.println(list.keySet());
		System.out.println(list.values());
		//Collection<Integer> tList = list.values();
		//Iterator<Integer> it2 = tList.iterator();
		//for(int i=0;it2.hasNext() && i<N;i++){
		//	int currentV = it.next();
		//	topList.add(currentV);
		//}	
		
		//for(int i =0;i<topList.size();i++){
		//	System.out.println(i+ "vertex = " +topList.get(i));
		//}
		return topList;
	}
	
	// CREDIT LEVEL
	
	public void setVertexWeights () {
		// PRE: -
		// POST: set weights of each vertex v to be v.visits divided by
		//         the total of visits for all vertices

		// TODO
	}
	
	public void convergenceWeights(Integer dp, Double jumpThreshold) {
		// PRE: dp >= 0 representing number of decimal places;
		//         n >= 0; 0 <= jumpThreshold <= 1.0
		// POST: web is surfed until all weights are constant to dp decimal places,
		//         for at least one iteration
		
		// TODO
	}

	// DISTINCTION LEVEL

	public Integer surfWithJumpUntilHit(Integer v, Integer n, Double jumpThreshold) {
		// PRE: v is vertex to start surf; n >= 0; 0 <= jumpThreshold <= 1.0
		// POST: surfs the graph randomly until visit v for second time (maximum n moves), 
		//       choosing adjacent vertex according to distribution of edge weights if random number is below jumpThreshold, 
		//       choosing any vertex uniformly randomly otherwise;
		//       modifies # visits in vertices
		//       returns number of vertices visited

		// TODO
		
		return 0;
	}

	public Double averageHittingTime(Integer v, Integer n, Integer maxHits, Double jumpThreshold) {
		// PRE: n >= 1 is number of iterations for averaging; maxHits >= 0; 0 <= jumpThreshold <= 1.0
		// POST: returns average number of vertices visited until hitting, across n iterations,
		//         (not including those which stopped because they reached maxHits)
		//       returns 0 if all iterations reached maxVisits
		
		// TODO
		
		return 0.0;
	}

	public Integer surfWithJumpUntilCover(Integer v, Integer n, Double jumpThreshold) {
		// PRE: v is vertex to start surf; n >= 0; 0 <= jumpThreshold <= 1.0
		// POST: surfs the graph randomly until all vertices visited (with maximum n vertices visited), 
		//       choosing adjacent vertex according to distribution of edge weights if random number is below jumpThreshold, 
		//       choosing any vertex uniformly randomly otherwise;
		//       modifies # visits in vertices
		//       returns number of vertices visited
		
		// TODO
		
		return 0;
	}
	
	public Double averageCoverTime(Integer n, Integer maxVisits, Double jumpThreshold) {
		// PRE: n >= 1 is number of iterations for averaging; maxVisits >= 0; 0 <= jumpThreshold <= 1.0
		// POST: returns average number of vertices visited until cover, across n iterations,
		//         (not including those which stopped because they reached maxVisits)
		//         randomly selecting start vertex each iteration
		//       returns 0 if all iterations reached maxVisits
		
		// TODO
		
		return 0.0;
	}

	public Integer boostVertex(Integer v, Integer dp) {
		// PRE: v is a vertex in the graph
		// POST: returns a vertex v2 (!= v) such that when edge (v,v2,1.0) is added to the graph,
		//         the weight of v is larger than when edge (v,v3,1.0) is added to the graph
		//         (for any v3), when the graph is surfed to convergence
		//       if there is no such vertex v2 (i.e. v is already connected to all other vertices),
		//         return v
		//       edges are only added if they do not already exist in the graph
		
		// TODO
		
		return 0;
	}
	
	
	public static void main(String[] args) {
		GraphApplic g = new GraphApplic();
		Vector<Edge> eList;
		
		try {
			eList = g.readWeightedFromFile("C:/Users/Admin/Documents/Uni/2113Sem1/COMP225/assignment-sample-graphs/tiny-weight.txt");
			g.setDirected(eList);
			g.print();
			g.surfWithJump(2,30,0.5);
			System.out.println("vertex 0 visits = " + g.getVertex(0).getVisits());
			g.topN(1);
		}
		catch (IOException e) {
			System.out.println("in exception: " + e);
		}


	}
}
