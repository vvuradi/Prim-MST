// interface defining prim's methods
public interface Prim {
	public Edge getMinEdge() throws Exception;
	public Vertex getNewAddedVertex(Edge e);
	public void printSol();
}
