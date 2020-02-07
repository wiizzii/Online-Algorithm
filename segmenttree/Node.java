public class Node {
	Node parent;
	Node left;
	Node right;
	int start;
	int stop;
	
	public Node(Node parent, Node left, Node right, int start, int stop) {
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.start = start;
		this.stop = stop;
	}
}