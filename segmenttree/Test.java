public class Test {
	public static void main(String[] args) {
		int stations = 8;
	
		Node root = new Node(null, null, null, 0, stations-1);
		SegmentTree tree = new SegmentTree(root);
		
		Node parent = root;
		for(int i = 0; i < stations; i++) {
			Node node = new Node(null, null, null, i, i+1);
		}
		
		Passenger pass1 = new Passenger("a", root);
	}
}