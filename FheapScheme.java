import java.util.ArrayList;
import java.util.HashSet;

/* 
 * This class implements Prim's algorithm using Fibonacci Heap.
 * This calls removeMin() method of Fibonacci Heap to decide on
 * the next edge.
 */
public class FheapScheme implements Prim {
	FibonacciHeap fHeap = new FibonacciHeap();
	HashSet<Vertex> mstVertex = new HashSet<Vertex>();
	ArrayList<Edge> solEdge = new ArrayList<Edge>();
	int sum; // holds min cost for the graph

	public FheapScheme() {
	}

	/*
	 * spanTree -> responsible for spanning through the given graph. Each time
	 * it selects the minimum edge, thus finding the new vertex added and
	 * inserts all the edges of the new vertex into heap. Then it removes the
	 * new vertex from the initial vertex pool.
	 */
	public void spanTree(int noOfVertices, HashSet<Vertex> vertexPool)
			throws Exception {
		// System.out.println("In Fibonacci Scheme");
		addInitVertex(0, vertexPool);
		for (int i = 0; i < noOfVertices - 1; i++) {
			Edge mEdge = getMinEdge();
			Vertex newV = getNewAddedVertex(mEdge);
			addEdgesToHeap(newV);
			mstVertex.add(newV);
			vertexPool.remove(newV);
		}
	}

	/*
	 * gets minimun edge from heap using removeMin() and checks if it is valid.
	 * If not removeMin() is retrived again.
	 */
	public Node getMinEdge() throws Exception {
		Node minEdge;
		do {
			minEdge = fHeap.removeMin();
			// if minedge returned is null, the graph is disconnected
			if (minEdge == null) {
				throw new Exception("Disconnected Graph..");
			}
		} while (!isMinValid(minEdge));
		sum = sum + minEdge.weight; // weight of edge is added to sum.
		solEdge.add(minEdge); // min Edge is added to solution of edges.
		return minEdge;
	}

	/*
	 * validity of min Edge is checked here. What it checks is that the both
	 * vertices of the edge are already included in the list.
	 */
	private boolean isMinValid(Edge e) {
		if (mstVertex.contains(e.src) && mstVertex.contains(e.dst))
			return false;
		return true;
	}

	// returns the new added vertex from the edge given
	public Vertex getNewAddedVertex(Edge e) {
		if (mstVertex.contains(e.src))
			return e.dst;
		else
			return e.src;
	}

	/*
	 * inserts all edges from the given vertex whose dest is not already part of
	 * mstVertex to the Fibonacci heap.
	 */
	private void addEdgesToHeap(Vertex v) {
		HashSet<Edge> temp = v.getAllEdges();
		for (Edge e : temp) {
			if (!mstVertex.contains(e.dst))
				fHeap.insert(new Node(e.src, e.dst, e.weight));
		}
	}

	/*
	 * inserts the edges of the first vertex. This is the initial setup for the
	 * Prim's algorithm.
	 */
	private void addInitVertex(int vName, HashSet<Vertex> vertexPool) {
		for (Vertex v : vertexPool) {
			if (v.name == vName) {
				mstVertex.add(v);
				addEdgesToHeap(v);
				vertexPool.remove(v);
				return;
			}
		}
	}

	/*
	 * prints the results at the end. Sum is printed first followed by vertices
	 * of the edge.
	 */
	public void printSol() {
		System.out.println(sum);
		for (Edge e : solEdge) {
			System.out.println(e.src.name + " " + e.dst.name);
		}
	}
}
