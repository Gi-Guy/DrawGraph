
/**
 * @author BloodyError
 *
 */
public class Edge {
	private Node[] nodeList;
	
	/**
	 * Setup empty edge with empty nodes
	 */
	public Edge(){
		this.nodeList=new Node[2];
		for(int i=0; i<nodeList.length; i++)
			nodeList[i] = new Node();
	}
	
	/**
	 * Setup edge with 2 nodes
	 * @param arg1	node1
	 * @param arg2	node2
	 */
	public Edge(Node arg1, Node arg2){
		this.nodeList = new Node[2];
		this.nodeList[0] = new Node(arg1);
		this.nodeList[1] = new Node(arg2);
	}
	
	/**
	 * setup edge by names
	 * @param node1Name first node name
	 * @param node2Name	second node name
	 */
	public Edge(char node1Name, char node2Name){
		this.nodeList = new Node[2];
		this.nodeList[0] = new Node(node1Name);
		this.nodeList[1] = new Node(node2Name);
	}

	/**
	 * @return first node of the edge
	 */
	public Node getFirstNode() {
		return nodeList[0];
	}
	
	/**
	 * @return second node of the edge
	 */
	public Node getSecondNode() {
		return nodeList[1];
	}

	/**
	 * This method doing aliasing.
	 * @param arg new first node of the edge
	 */
	public void setFirstNode(Node arg){
		//this.nodeList[0] = new Node(arg);
		this.nodeList[0] = arg;
	}
	/**
	 * @param arg new second node of the edge
	 */
	public void setSecondNode(Node arg){
		//this.nodeList[1] = new Node(arg);
		this.nodeList[1] = arg;
	}

	@Override
	public String toString() {
		return "(" + this.nodeList[0].getNodeName() + "," + this.nodeList[1].getNodeName() + ")";
	}
	public boolean equals(Edge edge) {
		if(this.getFirstNode().equals(edge.getFirstNode()) && this.getSecondNode().equals(edge.getSecondNode()))
			return true;
		else if(this.getFirstNode().equals(edge.getSecondNode()) && this.getSecondNode().equals(edge.getFirstNode()))
			return true;
		
		return false;
	}
	
	
}
