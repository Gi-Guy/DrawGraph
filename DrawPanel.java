import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class DrawPanel extends JPanel {
	protected Graph graphs;
	private String input;
	private char[] charName;
	private JLabel lblAddNode;
	public DrawPanel() {
		//Setting basic Things
		this.graphs=new Graph();
		setLayout(new BorderLayout(0, 0));//We want to put Jlabel msg down
		lblAddNode = new JLabel("Click anywhere to add new node");
		lblAddNode.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAddNode.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNode.setForeground(Color.RED);
		lblAddNode.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblAddNode, BorderLayout.SOUTH);
		
		/**Mouse click listener, When active will add new Node at current clicked location.
		 * I've put this here and not in MainPanel class because I want to work only on this JPanel*/
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//using dialog box for input node's name
				 input = JOptionPane.showInputDialog("Please enter 1 length node's name");
				if(input!=null){
					charName = input.toCharArray();
					if(charName.length!=1 || !Character.isLetter(charName[0])){//illegal input, cancel input
						JOptionPane.showMessageDialog(null, "Illegal input, try new node.");
						return;
					}
					//input is legal, creating new node and add it to Graph.
					Node tempNode = new Node(charName[0],e.getX(),e.getY());
					if(graphs.addNode(tempNode)){
						JOptionPane.showMessageDialog(null, "new node's " + charName[0] + " successfully added.");//Node's added to list
						repaint();
					}
					else//can not add node to list
						JOptionPane.showMessageDialog(null, "could not add node to list.");
				}
			}
		});
		
		
	}
	public void setGraph(Graph graph){
		//doing aliasing
		this.graphs=graph;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//checking if there are nodes, if does than remove label.
		if(graphs.NodesList.size()>0)
			remove(lblAddNode);
		else
			add(lblAddNode);
		//Draw things.
		//only Grapics2D can control thickness
		Graphics2D g2 = (Graphics2D) g;
		//g.setColor(Color.RED);
		g2.setColor(Color.RED);
		for(int i=0; i<graphs.EdgesList.size();i++){
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(graphs.EdgesList.get(i).getFirstNode().getX(), graphs.EdgesList.get(i).getFirstNode().getY(), graphs.EdgesList.get(i).getSecondNode().getX(), graphs.EdgesList.get(i).getSecondNode().getY());
		}
		
		g2.setColor(Color.BLUE);
		for(int i=0; i<graphs.NodesList.size();i++){
			g2.fillOval(graphs.NodesList.get(i).getX()-10, graphs.NodesList.get(i).getY()-10, 20,20);
			g2.setFont(new Font("Tahoma", 0, 30));
			g2.drawString(graphs.NodesList.get(i).toString(), graphs.NodesList.get(i).getX()-5, graphs.NodesList.get(i).getY()-20);
		}

	}
	/**This method clears the draw panel*/
	public void clear(){
		this.graphs=new Graph();
		repaint();
	}
}
