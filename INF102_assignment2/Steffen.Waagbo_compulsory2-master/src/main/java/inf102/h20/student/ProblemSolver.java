package inf102.h20.student;

import java.util.*;

import inf102.h20.graph.Edge;
import inf102.h20.graph.Graph;
import inf102.h20.graph.WeightedGraph;

public class ProblemSolver implements IProblem {

	@Override
	//Runtime: O(m*log m)
	public <T, E extends Comparable<E>> ArrayList<Edge<T>> mst(WeightedGraph<T, E> g) {
		ArrayList<Edge<T>> mstEdgeList = new ArrayList<>();
		T start = g.vertices().iterator().next();
		HashSet<T> visitedVertices = new HashSet<>();
		visitedVertices.add(start);
		PriorityQueue<Edge<T>> edges = new PriorityQueue<>(g);

		for (Edge<T> i : g.adjacentEdges(start))
			edges.add(i);

		while (mstEdgeList.size() < edges.size() - 1) {
			Edge<T> newEdge = edges.poll();

			if (visitedVertices.contains(newEdge.a) && visitedVertices.contains(newEdge.b))
				continue;

			T newVertex;
			if (visitedVertices.contains(newEdge.a)) {
				newVertex = newEdge.b;
			} else newVertex = newEdge.a;

			for (Edge<T> i : g.adjacentEdges(newVertex))
				edges.add(i);

			visitedVertices.add(newVertex);
			mstEdgeList.add(newEdge);
		}
		return mstEdgeList;
	}

	@Override
	//Runtime: O(n)
	public <T> T lca(Graph<T> g, T root, T u, T v) {
		HashMap<T, Integer> depths = DFS(g, root);

		while (!u.equals(v)) {
			if (depths.get(u).equals(depths.get(v))) {
				u = visitParent(g, depths, u);
				v = visitParent(g, depths, v);
			} else if (depths.get(v) > depths.get(u))
				v = visitParent(g, depths, v);
			else
				u = visitParent(g, depths, u);
		}
		return v;
	}

	//Runtime: O(n)
	private <T> HashMap<T, Integer> DFS(Graph<T> g, T root) {
		HashSet<T> visitedVertices = new HashSet<>();
		HashMap<T, Integer> depths = new HashMap<>();
		Stack<T> stack = new Stack<>();

		depths.put(root, 0);
		stack.push(root);

		while (!stack.isEmpty()) {
			T vertex = stack.pop();
			visitedVertices.add(vertex);

			for (T child : g.neighbours(vertex)) {
				if (visitedVertices.contains(child))
					continue;
				depths.put(child, depths.get(vertex) + 1);
				stack.push(child);
			}
		}
		return depths;
	}

	//Runtime: O(degree)
	private <T> T visitParent(Graph<T> g, HashMap<T, Integer> depths, T child) {
		T parent = child;
		for (T adjacent : g.neighbours(child)) {
			if (depths.get(adjacent) < depths.get(child))
				parent = adjacent;
		}
		return parent;
	}

	@Override
	//Runtime: O(n*log n)
	public <T> Edge<T> addRedundant(Graph<T> tree, T root) {
		HashSet<T> visited = new HashSet<>();
		HashMap<T, Integer> size = new HashMap<>();
		size(tree, root, visited, size);

		PriorityQueue<T> sortedRootNeighbours = getChildren(tree, root, size);
		T vertex1 = bestVertex(tree, root, sortedRootNeighbours.poll(), size, 0);
		T vertex2 = bestVertex(tree, root, sortedRootNeighbours.poll(), size, size.get(sortedRootNeighbours.poll()));

		return new Edge<>(vertex1, vertex2);
	}

	//Runtime: O(n)
	private <T> int size(Graph<T> tree, T node, HashSet<T> visited, HashMap<T, Integer> size) {
		if (visited.contains(node)) return 0;
		visited.add(node);
		int sz = 1;
		for (T child : tree.neighbours(node)) sz += size(tree, child, visited, size);
		size.put(node, sz);
		return sz;
	}

	//Runtime: O(degree*log n)
	private <T> PriorityQueue<T> getChildren(Graph<T> tree, T parent, HashMap<T, Integer> size) {
		PriorityQueue<T> children = new PriorityQueue<>((o1, o2) -> -Integer.compare(size.get(o1), size.get(o2)));

		for (T neighbour : tree.neighbours(parent)) {
			if (size.get(neighbour) < size.get(parent))
				children.add(neighbour);
		}
		return children;
	}

	//Runtime: O(n*log n)
	private <T> T bestVertex(Graph<T> tree, T root, T vertices, HashMap<T, Integer> size, Integer thisOutbreak) {
		if (vertices == null)
			return root;

		int largestOutbreak = (thisOutbreak == null ? 0 : thisOutbreak);
		int pathSize = size.get(vertices);

		while (largestOutbreak <= pathSize) {
			PriorityQueue<T> children = getChildren(tree, vertices, size);
			vertices = children.poll();
			if(size.get(vertices) != null)
				pathSize = size.get(vertices);
			if (size.get(children.poll()) != null)
				 try {
				 	if(largestOutbreak < size.get(children.poll()))
						 largestOutbreak = size.get(children.poll());
				 } catch (NullPointerException ignored) {
				 }
		}
		return vertices;
	}
}

