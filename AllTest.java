package graphs.freshcode;

import static org.junit.Assert.*;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
 
import org.junit.Test;
 
public class AllTest {
 
	String PATH = "C:/Users/Admin/Documents/Uni/2113Sem1/COMP225/assignment-sample-graphs/";
	
	@Test
	public void testNewVertexWeight() {
		Vertex v1 = new Vertex(3);
		assertEquals(v1.getWeight(), Double.valueOf(0.0));
	}
	
	@Test
	public void testNewVertexVisits() {
		Vertex v1 = new Vertex(3);
		assertEquals(v1.getVisits(), Integer.valueOf(0));
	}
	
	@Test
	public void testOneRandomLink() {
		GraphApplic g = new GraphApplic();
		Vector<Edge> eList;
		
		try {
			eList = g.readWeightedFromFile(PATH + "tiny-weight.txt");
			g.setDirected(eList);
			//g.print();
		}
		catch (IOException e) {
			System.out.println("in exception: " + e);
		}
		Integer v = g.getVertex(0).getRandomLink();
		assertEquals(v, Integer.valueOf(1));
	}
	
	@Test
	public void testSeveralRandomLinks() {
		GraphApplic g = new GraphApplic();
		Vector<Edge> eList;
		
		try {
			eList = g.readWeightedFromFile(PATH + "tiny-weight.txt");
			g.setDirected(eList);
			//g.print();
		}
		catch (IOException e) {
			System.out.println("in exception: " + e);
		}
 
		Integer[] counts = {0, 0, 0, 0, 0};
		for (int i = 0; i < 100; i++) {
			Integer v = g.getVertex(1).getRandomLink();
			counts[v] += 1;
		}
/*		for (int j = 0; j < 5; j++)
			System.out.println(counts[j]);
*/		
		assertTrue(counts[4] < counts[2]);
		assertTrue(counts[4] < counts[3]);
	}
	
	@Test
	public void testSurfWithJump() {
		GraphApplic g = new GraphApplic();
		Vector<Edge> eList;
		
		try {
			eList = g.readWeightedFromFile(PATH + "tiny-weight.txt");
			g.setDirected(eList);
			//g.print();
		}
		catch (IOException e) {
			System.out.println("in exception: " + e);
		}
 
		g.surfWithJump(g.getFirstVertexID(), 10000, 0.9);
		int[] upperLimits = {2793, 2722, 1554, 2545, 764};
		int[] lowerLimits = {2676, 2594, 1360, 2376, 586};
		for (int i = 0; i < upperLimits.length; i++) {
			//System.out.println(i + "(" + upperLimits.length + ") : " + g.getVertex(i).getVisits() + " : " + upperLimits[i]);
			assertTrue(g.getVertex(i).getVisits() < upperLimits[i]);
			//System.out.println(i + "(" + lowerLimits.length + ") : " + g.getVertex(i).getVisits() + " : " + lowerLimits[i]);
			assertTrue(g.getVertex(i).getVisits() > lowerLimits[i]);
		}
	}
	
	@Test
	public void testSurfWithJumpUniform() {
		GraphApplic g = new GraphApplic();
		Vector<Edge> eList;
		
		try {
			eList = g.readWeightedFromFile(PATH + "tiny-weight.txt");
			g.setDirected(eList);
			//g.print();
		}
		catch (IOException e) {
			System.out.println("in exception: " + e);
		}
 
		g.surfWithJump(g.getFirstVertexID(), 10000, 0.0);
		for (int i = 0; i < 5; i++) {
			//System.out.println(i + " : " + g.getVertex(i).getVisits());
			assertTrue(g.getVertex(i).getVisits() < 2200);
			assertTrue(g.getVertex(i).getVisits() > 1800);
		}
	}
	
	@Test
	public void testTopNTiny() {
		GraphApplic g = new GraphApplic();
		Vector<Edge> eList;
		
		try {
			eList = g.readWeightedFromFile(PATH + "tiny-weight.txt");
			g.setDirected(eList);
			//g.print();
		}
		catch (IOException e) {
			System.out.println("in exception: " + e);
		}
 
		g.surfWithJump(g.getFirstVertexID(), 10000, 0.9);
		int N = 3;
		ArrayList<Integer> visits = g.topN(N);
		Set<Integer> visitsSet = new HashSet<Integer>(visits);
		Integer[] valuesArray = {0, 1, 3};
		Set<Integer> valuesSet = new HashSet<Integer>(Arrays.asList(valuesArray));
		assertTrue(valuesSet.containsAll(visitsSet));
		assertTrue(visitsSet.containsAll(valuesSet));
	}
 
	@Test
	public void testTopNMedium() {
		GraphApplic g = new GraphApplic();
		Vector<Edge> eList;
		
		try {
			eList = g.readWeightedFromFile(PATH + "medium-weight.txt");
			g.setDirected(eList);
			//g.print();
		}
		catch (IOException e) {
			System.out.println("in exception: " + e);
		}
 
		g.surfWithJump(g.getFirstVertexID(), 500000, 0.9);
		int N = 10;
		ArrayList<Integer> visits = g.topN(N);
		Set<Integer> visitsSet = new HashSet<Integer>(visits);
		Integer[] valuesArray = {6, 22, 13, 23, 9, 28, 30, 21, 37, 11};
		Set<Integer> valuesSet = new HashSet<Integer>(Arrays.asList(valuesArray));
		assertTrue(valuesSet.containsAll(visitsSet));
		assertTrue(visitsSet.containsAll(valuesSet));
	}
 
	@Test
	public void testSetVertexWeights() {
		GraphApplic g = new GraphApplic();
		Vector<Edge> eList;
		
		try {
			eList = g.readWeightedFromFile(PATH + "tiny-weight.txt");
			g.setDirected(eList);
			//g.print();
		}
		catch (IOException e) {
			System.out.println("in exception: " + e);
		}
		
		Integer N = 20000;
		g.surfWithJump(g.getFirstVertexID(), N, 0.9);
		g.setVertexWeights();
		for (int i = 0; i < 5; i++)
			assertEquals(g.getVertex(i).getWeight(), (double) g.getVertex(i).getVisits() / N, 0.01);
	}
 
	@Test
	public void testConvergenceWeights() {
		GraphApplic g = new GraphApplic();
		Vector<Edge> eList;
		
		try {
			eList = g.readWeightedFromFile(PATH + "tiny-weight.txt");
			g.setDirected(eList);
			//g.print();
		}
		catch (IOException e) {
			System.out.println("in exception: " + e);
		}
		
		g.convergenceWeights(3, 0.9);
		//g.print();
		Double[] weights = {0.274, 0.265, 0.146, 0.247, 0.068};
		for (int i = 0; i < 5; i++)
			assertEquals(g.getVertex(i).getWeight(), weights[i], 0.01);
	}
 
	@Test
	public void testAverageHittingTimeTiny() {
		GraphApplic g = new GraphApplic();
		Vector<Edge> eList;
		
		try {
			eList = g.readWeightedFromFile(PATH + "tiny-weight.txt");
			g.setDirected(eList);
			//g.print();
		}
		catch (IOException e) {
			System.out.println("in exception: " + e);
		}
		
		Integer v = g.getFirstVertexID();
		Double avgHit = g.averageHittingTime(v, 10000, 1000000, 0.9);
		g.setVertexWeights();
		//System.out.println("avgHit: " + avgHit);
		assertTrue(avgHit < 4);
		assertTrue(avgHit > 3.5);
		assertEquals(1 / g.getVertex(v).getWeight(), avgHit, 0.1);
	}
 
	@Test
	public void testAverageHittingTimeMedium() {
		GraphApplic g = new GraphApplic();
		Vector<Edge> eList;
		
		try {
			eList = g.readWeightedFromFile(PATH + "medium-weight.txt");
			g.setDirected(eList);
			//g.print();
		}
		catch (IOException e) {
			System.out.println("in exception: " + e);
		}
		
		Integer v = g.getFirstVertexID();
		Double avgHit = g.averageHittingTime(v, 10000, 1000000, 0.9);
		g.setVertexWeights();
		//System.out.println("avgHit: " + avgHit);
		assertTrue(avgHit < 500);
		assertTrue(avgHit > 400);
		assertEquals(1 / g.getVertex(v).getWeight(), avgHit, 0.1);
	}
 
	@Test
	public void testAverageHittingTimeTinyMaxed() {
		GraphApplic g = new GraphApplic();
		Vector<Edge> eList;
		
		try {
			eList = g.readWeightedFromFile(PATH + "tiny-weight.txt");
			g.setDirected(eList);
			//g.print();
		}
		catch (IOException e) {
			System.out.println("in exception: " + e);
		}
		
		Integer v = g.getFirstVertexID();
		Double avgHit = g.averageHittingTime(v, 10000, 1, 1.0);
		g.setVertexWeights();
		//System.out.println("avgHit: " + avgHit);
		assertEquals(avgHit, 0.0, 0.01);
	}
 
	@Test
	public void testAverageCoverTime() {
		GraphApplic g = new GraphApplic();
		Vector<Edge> eList;
		
		try {
			eList = g.readWeightedFromFile(PATH + "tiny-weight.txt");
			g.setDirected(eList);
			//g.print();
		}
		catch (IOException e) {
			System.out.println("in exception: " + e);
		}
		
		Double avgCover = g.averageCoverTime(10000, 10000, 0.9);
		//System.out.println("avgCover: " + avgCover);
		assertTrue(avgCover < 16.5);
		assertTrue(avgCover > 15.0);
	}
 
}