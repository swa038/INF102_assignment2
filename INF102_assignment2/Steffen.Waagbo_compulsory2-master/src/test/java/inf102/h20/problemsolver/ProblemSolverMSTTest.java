package inf102.h20.problemsolver;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf102.h20.graph.*;
import inf102.h20.student.*;

class ProblemSolverMSTTest {
	private WeightedGraph<Integer, Integer> g;
	private int mstWeight;
	private IProblem problemSolver;
	
	@BeforeEach()
	void getProblemSolver() {
		// Enter your implementation here
		problemSolver = new ProblemSolver();
	}
	
	private void generateTestCase(int i) throws IOException{
		g = new GraphBuilder().readWeightedGraphFromFile("input/MST" + i + ".in");
		Scanner sc = new Scanner(new FileReader(new File("input/MST" + i + ".ans")));
		mstWeight = sc.nextInt();
		sc.close();
	}
	
	private boolean isConnected(ArrayList<Edge<Integer>> edges) {
		Graph<Integer> tmp = new Graph<Integer>();
		for (Integer vertex: g.vertices()) tmp.addVertex(vertex);
		for (Edge<Integer> edge: edges) tmp.addEdge(edge.a, edge.b);
		HashSet<Integer> visited = new HashSet<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(0);
		while (!queue.isEmpty()) {
			Integer u = queue.poll();
			if (visited.contains(u)) continue;
			visited.add(u);
			for (Integer v: tmp.neighbours(u)) {
				queue.add(v);
			}
		}
		for (Integer u: g.vertices()) {
			if (!visited.contains(u)) {
				return false;
			}
		}
		
		return true;
	}
	
	private int weightOfTree(ArrayList<Edge<Integer>> edges) {
		int sum = 0;
		for (Edge<Integer> edge: edges) {
			sum += g.getWeight(edge.a, edge.b);
		}
		return sum;
	}

	@Test
	void test1() throws IOException {
		generateTestCase(0);
		ArrayList<Edge<Integer>> edges = problemSolver.mst(g.clone());
		assertTrue(isConnected(edges), "Your Tree is not connected");
		assertEquals(mstWeight, weightOfTree(edges), "Your tree is too heavy");
	}
	
	@Test
	void test2() throws IOException {
		generateTestCase(1);
		ArrayList<Edge<Integer>> edges = problemSolver.mst(g.clone());
		assertTrue(isConnected(edges), "Your Tree is not connected");
		assertEquals(mstWeight, weightOfTree(edges), "Your tree is too heavy");
	}
	
	@Test
	void test3() throws IOException {
		generateTestCase(2);
		ArrayList<Edge<Integer>> edges = problemSolver.mst(g.clone());
		assertTrue(isConnected(edges), "Your Tree is not connected");
		assertEquals(mstWeight, weightOfTree(edges), "Your tree is too heavy");
	}
	
	@Test
	void test4() throws IOException {
		generateTestCase(3);
		ArrayList<Edge<Integer>> edges = problemSolver.mst(g.clone());
		assertTrue(isConnected(edges), "Your Tree is not connected");
		assertEquals(mstWeight, weightOfTree(edges), "Your tree is too heavy");
	}
	
	@Test
	void test5() throws IOException {
		generateTestCase(4);
		ArrayList<Edge<Integer>> edges = problemSolver.mst(g.clone());
		assertTrue(isConnected(edges), "Your Tree is not connected");
		assertEquals(mstWeight, weightOfTree(edges), "Your tree is too heavy");
	}
}