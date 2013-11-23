public class Edge {
	Vertex src; // source vertex
	Vertex dst; // destination vertex
	int weight; // weight of edge

	public Edge() {
	}

	/*
	 * constructor for edge which takes source vertex, dest vertex and 
	 * weight of the edge as parameters
	 */
	public Edge(Vertex src, Vertex dst, int weight) {
		this.src = src;
		this.dst = dst;
		this.weight = weight;
	}

	public Vertex getSrc() {
		return this.src;
	}

	public Vertex getDst() {
		return this.dst;
	}

	public int getWeight() {
		return this.weight;
	}
}
