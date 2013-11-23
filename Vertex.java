import java.util.HashSet;

/*
 * vertex class consisting of a name variable and set
 * of edges for the vertex
 */
public class Vertex {
	int name;
	HashSet<Edge> setOfEdges = new HashSet<Edge>();

	public Vertex(int name, Edge ed) {
		this.name = name;
		setOfEdges.add(ed);
	}

	public Vertex(int name) {
		this.name = name;
	}

	public void addEdge(Edge ed) {
		setOfEdges.add(ed);
	}

	public HashSet<Edge> getAllEdges() {
		return setOfEdges;
	}

	// removes the particular edge from the set of edges
	public void removeEdge(Edge e) {
		for (Edge ed : setOfEdges) {
			if ((e.src.name == ed.src.name || e.src.name == ed.dst.name)
					&& (e.dst.name == ed.src.name || e.dst.name == ed.dst.name)) {
				setOfEdges.remove(ed);
				return;
			}
		}
	}
}
