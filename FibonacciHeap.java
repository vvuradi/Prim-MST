/*
 * Fibonacci Heap is implemented here. methods that are reqd for
 * implementing Prim's algorithm alone are given.
 */
public class FibonacciHeap {

	Node root = null;
	int noOfNodes;

	// returns true if heap is empty, false otherwise
	public boolean isEmpty() {
		if (noOfNodes == 0)
			return true;
		else
			return false;
	}

	/*
	 * responsible for handling insertion into the heap. Takes node to be
	 * inserted as parameter.
	 */
	public void insert(Node e) {
		if (e == null)
			return;
		// if root is null, make the given node as root
		if (this.root == null) {
			this.root = e;
			this.root.leftS = this.root;//
			this.root.rightS = this.root;
			this.root.child = null;
		} else { // else adding node to the top level to the
					// right of the root
			Node temp = this.root.rightS;
			this.root.rightS = e;
			e.rightS = temp;
			temp.leftS = e;
			e.leftS = this.root;
		}
		e.child = null;
		e.parent = null;
		e.degree = 0;
		e.childCut = false;
		if (this.root.data > e.data)// if new node value is less than that
			this.root = e;// of old root val, then update root
		noOfNodes++;
	}

	// returns the min value from the heap.
	public Node removeMin() {
		if (noOfNodes == 0)
			return null;
		Node temp = this.root;
		if (this.root.leftS != this.root) {
			// case of root having siblings
			// Removing root from linked list
			this.root.leftS.rightS = this.root.rightS;
			this.root.rightS.leftS = this.root.leftS;
			Node temp1 = this.root.rightS;
			// Updating parents of the top level nodes to null
			updateParentNull();
			meld(temp1, this.root.child);
		} else {// case of root having no siblings
			updateParentNull();
			meld(null, this.root.child);
		}
		// pairwise combine after removeMin operation
		pairwiseCombine(temp);
		noOfNodes--;
		return temp;
	}

	// intended to set all child's parent pointers to null
	private void updateParentNull() {
		if (this.root.child != null) {
			Node rChild = this.root.child;
			do {
				rChild.parent = null;
				rChild = rChild.rightS;
			} while (rChild != this.root.child);
		}
	}

	// melding of two nodes
	private void meld(Node node1, Node node2) {
		if (node1 == null && node2 == null) {
			this.root = null;
			return;
		}
		if (node1 == null) {
			this.root = getMin(node2);
			return;
		}
		if (node2 == null) {
			this.root = getMin(node1);
			return;
		}
		Node temp1 = node1.rightS;
		Node temp2 = node2.leftS;
		node1.rightS = node2;
		node2.leftS = node1;
		temp1.leftS = temp2;
		temp2.rightS = temp1;
		node1.parent = null;
		node2.parent = null;
		this.root = getMin(node1);
	}

	// this method returns min node in the level across siblings.
	private Node getMin(Node node) {
		Node min = node;
		Node temp = node.rightS;

		while (temp != node) {
			if (min.data > temp.data)
				min = temp;
			temp = temp.rightS;
		}
		return min;
	}

	private void pairwiseCombine(Node nde) {
		// Array 'a' contains the top levelnodes
		Node[] a = new Node[noOfNodes];
		Node temp = this.root;
		Node[] b = new Node[noOfNodes];
		int index = 0;

		if (temp == null)
			return;
		do {
			b[index++] = temp;
			temp = temp.rightS;
		} while (!temp.equals(this.root));

		for (int i = 0; i < b.length; i++) {
			if (b[i] == null) {
				break;
			}
			b[i].rightS = b[i].leftS = b[i];
			b[i].parent = null;
			// If 'a' already contains a node of the same
			while (a[b[i].degree] != null) {
				Node temp2 = a[b[i].degree];
				a[b[i].degree] = null;
				// if they are of same degree, then combine them
				b[i] = mergeNodes(b[i], temp2);
			}
			a[b[i].degree] = b[i];
		}

		this.root = null;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != null) {
				meld(this.root, a[i]);
			}
		}
	}

	private Node mergeNodes(Node node1, Node node2) {
		if (node1 == null && node2 == null) {
			return null;
		} else if (node1 == null && node2 != null) {
			node2.rightS = node2;
			node2.leftS = node2;
			return node2;
		} else if (node1 != null && node2 == null) {
			node1.rightS = node1;
			node1.leftS = node1;
			return node1;
		}

		node1.childCut = node2.childCut = false;
		// Compare the distances to determine the parent
		if (node1.data > node2.data) {
			if (node2.child == null) {
				node2.child = node1;
				node1.parent = node2;
			} else {
				// make smaller one as parent of bigger
				Node temp = node2.child.rightS;
				node2.child.rightS = node1;
				node1.leftS = node2.child;
				node1.rightS = temp;
				temp.leftS = node1;
				node1.parent = node2;
			}
			node2.degree++;
			node2.rightS = node2.leftS = node2;
			return node2;
		} else {
			if (node1.child == null) {
				node1.child = node2;
				node2.parent = node1;
			} else {
				Node temp = node1.child.rightS;
				node1.child.rightS = node2;
				node2.leftS = node1.child;
				node2.rightS = temp;
				temp.leftS = node2;
				node2.parent = node1;
			}
			node1.degree++;
			node1.rightS = node1.leftS = node1;
			return node1;
		}
	}
}
