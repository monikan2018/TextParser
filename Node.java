
public class Node{

	private String word;
	private int count;
	private Node lChild;
	private Node rChild;

	
	public Node(){
		word = null;
		lChild = null;
		rChild =null;
		count = 0;
	}
	public Node(String newword, int newcount) {
		setData(word = newword, count = newcount);
	}
	public void setData(String word, int count) {
		this.word =word;
		this.count= count;
	}
	public void setWord(String data) {
		this.word = data;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getWord() {
		return word;
	}
	public int getCount() {
		return count;
	}
	
	public Node getlChild() {
		return lChild;
	}
	public void setlChild(Node lChild) {
		this.lChild = lChild;
	}
	public Node getrChild() {
		return rChild;
	}
	public void setrChild(Node rChild) {
		this.rChild = rChild;
	}

	public static int treeSize(Node root) {
		if (root == null) {
			return 0;
		}else {
			return 1 + treeSize(root.lChild)+treeSize(root.rChild);
		}
	}
}
	
 
	