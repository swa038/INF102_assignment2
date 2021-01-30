package inf102.h20.problemsolver;

import java.io.File;
import inf102.h20.graph.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GraphBuilder {
	
	/**
	 * Constructs a WeightedGraph from a given graph file. format is
	 * <n> <m>
	 * <u> <v> <w> ... for m lines
	 * 
	 * n is number of vertices, m is number of edges, u-v is endpoints of edge, w is weight of edge
	 * 
	 * @param file path of graph-file, given as string
	 * @throws Exception
	 */
	public WeightedGraph<Integer, Integer> readWeightedGraphFromFile(String file) throws IOException{
		Scanner sc = new Scanner(new FileReader(new File(file)));
		int n = sc.nextInt(), m = sc.nextInt();
		WeightedGraph<Integer, Integer> g = new WeightedGraph<>();
		for (int u = 0; u < n; u++) g.addVertex(u);
		for (int i = 0; i < m; i++) {
			int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
			g.addEdge(u,  v,  w);
		}
		sc.close();
		return g;
	}
	
	/**
	 * Constructs an LCA instance from file, graph is returned, queries are stored in queries.
	 * 
	 * @param file
	 * @param queries
	 * @return
	 */
	public Graph<Integer> readLCAInstance(String file, ArrayList<Edge<Integer>> queries) throws IOException{
		Scanner sc = new Scanner(new FileReader(new File(file)));
		int n = sc.nextInt(), q = sc.nextInt();
		Graph<Integer> g = new Graph<>();
		for (int u = 0; u < n; u++) g.addVertex(u);
		for (int i = 0; i < n-1; i++) {
			int u = sc.nextInt(), v = sc.nextInt();
			g.addEdge(u,  v);
		}
		
		for (int i = 0; i < q; i++) {
			queries.add(new Edge<Integer>(sc.nextInt(), sc.nextInt()));
		}
		sc.close();
		return g;
	}
	
	/**
	 * Constructs a graph of type tree from file, graph is returned
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Graph<Integer> readTreeFromFile(String file) throws IOException{
		Scanner sc = new Scanner(new FileReader(new File(file)));
		int n = sc.nextInt();
		int m = n-1;
		Graph<Integer> g = new Graph<>();
		for (int u = 0; u < n; u++) g.addVertex(u);
		for (int i = 0; i < m; i++) {
			int u = sc.nextInt(), v = sc.nextInt();
			g.addEdge(u,  v);
		}
		sc.close();
		return g;
	}
}
