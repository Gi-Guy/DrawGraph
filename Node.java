

public class Node {

	private char nodeName;
	private int x, y;
	
	/**
	 * setup empty node
	 */
	public Node(){
		this.nodeName='-';
		this.x = 0;
		this.y = 0;
	}
	/**
	 * setup new node by copy another node
	 * @param arg
	 */
	public Node(Node arg){
		this.setNodeName(arg.getNodeName());
		this.setX(arg.getX());
		this.setY(arg.getY());
	}
	/**
	 * setup new node by all parameters
	 * @param name
	 * @param x
	 * @param y
	 */
	public Node(char name, int x, int y){
		this.nodeName=name;
		this.x=x;
		this.y=y;
	}
	/**
	 * set new node by name, this node has no location
	 * @param name
	 */
	public Node(char name){
		this.nodeName=name;
		this.x=0;
		this.y=0;
	}
	public char getNodeName() {
		return nodeName;
	}
	public void setNodeName(char nodeName) {
		this.nodeName = nodeName;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return Character.toString(getNodeName());
	}
	/**
	 * This method return true is this node and new node are equals, otherwise return false.
	 * @param node
	 * @return
	 */
	public boolean equals(Node node){
		if(this.getNodeName()==node.getNodeName())
			return true;
		return false;
	}
	
}
