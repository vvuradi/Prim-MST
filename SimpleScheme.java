import java.util.ArrayList;
import java.util.HashSet;

/*
 * This class implements Prim's algorithm using Simple scheme.
 * This itereates through an array of sets to decide on
 * the next edge.
 */
public class SimpleScheme implements Prim {
	HashSet<Vertex> mstVertex = new HashSet<Vertex>();
	ArrayList<Edge> solEdge = new ArrayList<Edge>();
	ArrayList<Integer> mstVertexList = new ArrayList<Integer>();
	int sum;
	int MAX_VAL = Integer.MAX_VALUE;

	public SimpleScheme() {
	}

	/*
	 * spanTree -> responsible for spanning through the given graph. Each time
	 * it selects the minimum edge, thus finding the new vertex added and
	 * inserts all the edges of the new vertex into heap. Then it removes the
	 * new vertex from the initial vertex pool.
	 */
	public void spanTree(int noOfVertices, HashSet<Vertex> vertexPool)
			throws Exception {
		// System.out.println("In Simple Scheme");
		addInitVertex(0, vertexPool);
		for (int i = 0; i < noOfVertices - 1; i++) {
			Edge mEdge = getMinEdge();
			Vertex newV = getNewAddedVertex(mEdge);
			mstVertex.add(newV);
			mstVertexList.add(newV.name);
			removeEdgeFromVertice(mEdge);
			vertexPool.remove(newV);
		}
	}

	// returns all adjacent edges of all vertices in mstvertex
	private HashSet<Edge>[] getAllAdjEdges() {
		HashSet<Edge>[] adjEdges = new HashSet[mstVertex.size()];
		int i = 0;
		for (Vertex v : mstVertex) {
			adjEdges[i] = v.getAllEdges();
			i++;
		}
		return adjEdges;
	}

	/*
	 * gets minimun edge from array of sets and checks if it is valid.
	 */
	public Edge getMinEdge() throws Exception {
		HashSet<Edge>[] adjEdges = getAllAdjEdges();
		int min = MAX_VAL;
		Edge minEdge = null;
		for (HashSet<Edge> edgeSet : adjEdges) {
			for (Edge e : edgeSet) {
				if (e.weight < min && isMinValid(e)) {
					min = e.weight;
					minEdge = e;
				}
			}
		}
		// if minedge returned is null, the graph is disconnected
		if (minEdge == null) {
			throw new Exception("Disconnected Graph");
		}
		sum = sum + minEdge.weight;// weight of edge is added to sum.
		solEdge.add(minEdge);// min Edge is added to solution of edges.
		return minEdge;
	}

	/*
	 * inserts the edges of the first vertex. This is the initial setup for the
	 * Prim's algorithm.
	 */
	private void addInitVertex(int vName, HashSet<Vertex> vertexPool) {
		for (Vertex v : vertexPool) {
			if (v.name == vName) {
				mstVertex.add(v);
				mstVertexList.add(v.name);
				vertexPool.remove(v);
				return;
			}
		}
	}

	/*
	 * validity of min Edge is checked here. What it checks is that the both
	 * vertices of the edge are already included in the list.
	 */
	private boolean isMinValid(Edge e) {
		if (mstVertexList.contains(e.src.name)
				&& mstVertexList.contains(e.dst.name))
			return false;
		return true;
	}

	// returns the new added vertex from the edge given
	public Vertex getNewAddedVertex(Edge e) {
		if (mstVertexList.contains(e.src.name))
			return e.dst;
		else
			return e.src;
	}

	// removes the edge from both vertices
	private void removeEdgeFromVertice(Edge e) {
		Vertex src = e.src;
		Vertex dst = e.dst;
		src.removeEdge(e);
		dst.removeEdge(e);
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
