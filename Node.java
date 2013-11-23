// node class extends edge class to incorporate edge
// into node
public class Node extends Edge{
	int degree;
	int data; // this will have the weight of edge
	Node child;
	Node leftS;
	Node rightS;
	Node parent;
	boolean childCut;
	
	public Node(Vertex src, Vertex dst, int weight){
		// calling edge constructor
		super(src, dst, weight);
		this.data = weight;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getChild() {
		return child;
	}

	public void setChild(Node child) {
		this.child = child;
	}

	public Node getRightS() {
		return rightS;
	}

	public void setRightS(Node right) {
		rightS = right;
	}

	public Node getLeftS() {
		return leftS;
	}

	public void setLeftS(Node left) {
		leftS = left;
	}
}
