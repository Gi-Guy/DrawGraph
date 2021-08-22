import java.util.ArrayList;

public class Graph {
	//any class of this program going to use those list, than should be protected.
	protected ArrayList<Node> NodesList;
	protected ArrayList<Edge> EdgesList;
	
	/**
	 * Create an empty graph
	 */
	public Graph(){
		this.NodesList = new ArrayList<Node>();
		this.EdgesList = new ArrayList<Edge>();
	}
	
	/**
	 * create an graph by nodes array and edges array
	 * @param nodes
	 * @param edges
	 */
	public Graph(Node[] nodes, Edge[] edges){
		this.NodesList = new ArrayList<Node>();
		this.EdgesList = new ArrayList<Edge>();
		if(nodes.length>0)//adding nodes
			for(int i=0; i<nodes.length; i++){
				if(!this.addNode(nodes[i]))
					System.out.println("can not add node " + "'" + nodes[i].getNodeName() + "'" + " to list."); //Node already exist in list.
			}
		if(edges.length>0)
			for(int i=0; i<edges.length; i++){
				if(!this.addEdge(edges[i].getFirstNode(), edges[i].getSecondNode()))
					System.out.println("can not add edge " + "[" + edges[i].getFirstNode().getNodeName() + ',' +  edges[i].getSecondNode().getNodeName() + ']' + " to the list.");
			}
	}
	//////////////////////////////////////////////////////////////////////////
	//Nodes Works
	//////////////////////////////////////////////////////////////////////////
	/**
	 * This method add new node to graph.
	 * @param node	
	 * @return false if node already exist, true if completed the task
	 */
	public boolean addNode(Node node){
		if(NodesList.isEmpty()){//list is empty, this is the first node
			this.NodesList.add(new Node(node));
			return true;
		}
		for(int i=0; i<NodesList.size(); i++)
			if(!nodeNameExist(node) && !NodeCroodExist(node)){
				this.NodesList.add(new Node(node));
				return true;
			}
		return false;
	}
	/**
	 * this method takes current node and check if the node name is already exist in list.
	 * @param node
	 * @return true if node already exist, otherwise return false.
	 */
	public boolean nodeNameExist(Node node){
		for(int i=0; i<NodesList.size(); i++)
			if(NodesList.get(i).equals(node))
				return true;
		return false;
	}
	/**
	 * this method takes current node and check if there is already existing node at those coordination.
	 * @param node
	 * @return true if node already exist, otherwise return false.
	 */
	public boolean NodeCroodExist(Node node){
		for(int i=0; i<NodesList.size(); i++)
			if(NodesList.get(i).getX()==node.getX() && NodesList.get(i).getY()==node.getY())
				return true;
		return false;
	}
	/**
	 * this method find the node in ArrayList and return it index
	 * @param node
	 * @return
	 */
	private int nodeIndex(Node node){
		for(int i=0; i<NodesList.size(); i++)
			if(NodesList.get(i).equals(node))
				return i;
		return -1;
	}
	/**
	 * This method remove 'node' from list, also remove it edge if exist.
	 * @param node
	 * @return
	 */
	public boolean removeNode(Node node){
		int nodeIndex = nodeIndex(node);
		int edgeIndex = edgeIndexRemoveNode(node);
		if(nodeIndex<0)//node isn't exist
			return false;
		else
			NodesList.remove(nodeIndex);//removing node
		//Removing all edges, if exist
		while(edgeIndex>=0){
			EdgesList.remove(edgeIndex);
			edgeIndex = edgeIndexRemoveNode(node);
		}
		/*if(edgeIndex>=0)
			EdgesList.remove(edgeIndex);//removing edge, if exist*/
		
		return true;
	}
	//////////////////////////////////////////////////////////////////////////
	//Edges Works
	//////////////////////////////////////////////////////////////////////////	
	
	/**
	 * This method get two nodes, creates an edge and add it to the list, if can. otherwise, if falling will return false.
	 * @param node1
	 * @param node2
	 * @return boolean return true if successfully created new edge and added it to list, otherwise return false
	 */
	public boolean addEdge(Node node1, Node node2){
		Node node1Aliasing = null,node2Aliasing = null;
		//Checking if nodes aren't equals
		if(node1.equals(node2))
			return false;
		//Checking if both nodes are exist in list
		if(!nodeNameExist(node1) || !nodeNameExist(node2))
			return false;
		//finding nodes in memory; doing some aliasing works
		int index=nodeIndex(node1);
		if(index>=0)
			node1Aliasing = NodesList.get(index);
		index=nodeIndex(node2);
		if(index>=0)
			node2Aliasing = NodesList.get(index);
		
		//checking if there is already existing edge for those nodes.
		Edge newEdge = new Edge(node1, node2);
		if(existEdge(newEdge))
			return false;//Edge already exist!
		
		//at this point there is no edge and we got the nodes.
		//adding the new edge
		newEdge.setFirstNode(node1Aliasing);
		newEdge.setSecondNode(node2Aliasing);
		EdgesList.add(newEdge);
		return true;
	}
	/**
	 * This Method checks if there is already existing edge by getting another edge to compare.
	 * @param edge
	 * @return
	 */
	public boolean existEdge(Edge edge){
		for(int i=0; i<EdgesList.size(); i++)
			if(EdgesList.get(i).equals(edge))
				return true;
		return false;
	}
	/**
	 * Use this method only when removing node and need to find and remove it edge, if exist.
	 * @param node
	 * @return -1 if no edge exist, otherwise return it index.
	 */
	private int edgeIndexRemoveNode(Node node){
		for(int i=0; i<EdgesList.size();i++)
			if(EdgesList.get(i).getFirstNode().equals(node) || EdgesList.get(i).getSecondNode().equals(node))
				return i;
		return -1;
	}
	private int edgeIndex(Node node1, Node node2){
		for(int i=0; i<EdgesList.size(); i++){
			if(EdgesList.get(i).getFirstNode().equals(node1) && EdgesList.get(i).getSecondNode().equals(node2))
				return i;
			else if(EdgesList.get(i).getFirstNode().equals(node2) && EdgesList.get(i).getSecondNode().equals(node1))
				return i;
		}
		return -1;
	}
	/**
	 * this method get two nodes and remove the edge between them, if exit.
	 * @param node1
	 * @param node2
	 * @return true if successfully removed edge, otherwise false.
	 */
	public boolean removeEdge(Node node1, Node node2){
		int index = edgeIndex(node1, node2);
		if(index>=0){//Edge is exist
			EdgesList.remove(index);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "NodesList=" + NodesList + "\n, EdgesList=" + EdgesList;
	}

	@Override
	public boolean equals(Object obj) {
		Graph tempGraph;
		if(obj instanceof Graph){
			tempGraph = (Graph) obj;
			if(this.NodesList.size()!=tempGraph.NodesList.size() || this.EdgesList.size()!=tempGraph.EdgesList.size())
				return false;
			for(int i=0; i<tempGraph.NodesList.size();i++)//checking if same nodes
				if(!this.nodeNameExist(tempGraph.NodesList.get(i)))//checking if node from tempGraph is exist in this graph
					return false;
			for(int i=0; i<tempGraph.EdgesList.size();i++)
				if(!this.existEdge(tempGraph.EdgesList.get(i)))
					return false;
			
		}
		else return false;//something went wrong or there is no Graph
		
		return true;
	}
	
}
