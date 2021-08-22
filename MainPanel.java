import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainPanel extends JFrame {
	private enum InputType {RemoveNode,AddEdge,RemoveEdge};
	private JPanel mainPanel;
	private JButton btnClear;
	private JButton btnAddEdge;
	private JButton btnRemoveEgde;
	private JButton btnRemoveNode;
	private JButton btnExample;
	private JPanel dialogPanel;
	private dialogPanel dialog;
	private DrawPanel drawPanel;
	private JPanel buttonsPanel;
	private JButton btnTostring;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPanel frame = new MainPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainPanel() {
		/////////////////////////////////////////////////////
		//Step 1: Define window location and 'lookAndFell'
		/////////////////////////////////////////////////////
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainPanel.class.getResource("/resources/icon2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		setTitle("Draw Graph");
		//change JFrame screen location (mid screen)
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//Setting new looks for JFrame, I hate the default looks.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		/////////////////////////////////////////////////////
		//Step 2: initializing and running.
		/////////////////////////////////////////////////////
		initComponets();
		createEvents();

	}
	/**This method responsible for all initializing component and adding them to JFrame panel*/
	private void initComponets(){

		///////////////////////////////////////////
		//Setting main panel
		///////////////////////////////////////////
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		///////////////////////////////////////////
		//Setting drawPanel
		///////////////////////////////////////////
		drawPanel = new DrawPanel();
		drawPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		///////////////////////////////////////////
		//Setting buttons Panel
		///////////////////////////////////////////
		buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		buttonsPanel.setLayout(new GridLayout(0, 6, 0, 0));
		
		btnClear = new JButton("Clear");
		btnClear.setToolTipText("Click to clear the panel");
		buttonsPanel.add(btnClear);
		
		btnAddEdge = new JButton("Add Edge");
		btnAddEdge.setToolTipText("Click to add new a,b edge");
		buttonsPanel.add(btnAddEdge);
		
		btnRemoveEgde = new JButton("Remove Edge");
		btnRemoveEgde.setToolTipText("Click to remove Edge a.b");
		buttonsPanel.add(btnRemoveEgde);
		
		btnRemoveNode = new JButton("Remove Node");
		btnRemoveNode.setToolTipText("Click to remove node 'x'");
		buttonsPanel.add(btnRemoveNode);
		
		btnExample = new JButton("Example");
		btnExample.setToolTipText("Press to see example");
		buttonsPanel.add(btnExample);
		
		btnTostring = new JButton("toString");
		btnTostring.setToolTipText("Click to string the graph");
		buttonsPanel.add(btnTostring);
		///////////////////////////////////////////
		//Setting main panel layout
		///////////////////////////////////////////
		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.add(drawPanel, BorderLayout.CENTER);
		mainPanel.add(buttonsPanel, BorderLayout.NORTH);
	}
	/**This method responsible for all handler events of this program*/
	private void createEvents(){
		/**
		 * AddEdge button Listener, When called will add new edge to graph*/
		btnAddEdge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] charInput = inputGUI(InputType.AddEdge);
				if(charInput!=null){
					//at this point input is legal
					Node node1 =  new Node(charInput[0]);
					Node node2 = new Node(charInput[2]);
					if(drawPanel.graphs.addEdge(node1, node2)){
						JOptionPane.showMessageDialog(null, "new edge successfully added.");//Edge added to list
						repaint();
					}
					else
						JOptionPane.showMessageDialog(null, "could not add edge to list.");
				}
			}
		});
		/**
		 * RemoveEdge button Listener, when called will remove node1,node2 edge from graph.*/
		btnRemoveEgde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//calling to input gui method
				char[] charInput = inputGUI(InputType.RemoveEdge);
				if(charInput!=null){
					//at this point input is legal
					Node node1 = new Node(charInput[0]);
					Node node2 = new Node(charInput[2]);
					if(drawPanel.graphs.removeEdge(node1, node2)){
						JOptionPane.showMessageDialog(null, "Edge successfully removed.");//Edge removed from list
						repaint();
					}
					else
						JOptionPane.showMessageDialog(null, "could not remove edge.");
				}
			}
		});
		/**RemoveNode button listener, when called will remove node from graph, will also remove edge if needed.*/
		btnRemoveNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Calling to input gui method
				char[] charInput = inputGUI(InputType.RemoveNode);
				if(charInput!=null){
					//input is legal at this point
					
					Node node = new Node(charInput[0]);
					if(drawPanel.graphs.removeNode(node)){
						JOptionPane.showMessageDialog(null, "Node's successfully removed.");//Edge removed from list
						repaint();
					}
					else
						JOptionPane.showMessageDialog(null, "could not remove node.");
				}
			}
		});
		/**
		 * Example button Listener, When called will active Example dialog calls and show an example of graph.
		 */
		btnExample.addActionListener(
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog = new dialogPanel();
				dialog.setExample();
				dialog.setVisible(true);
			}
		});
		/**Clear button, when called will clear the drawPanel*/
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPanel.clear();
			}
		});
		btnTostring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog = new dialogPanel();
				dialog.setToString();
				dialog.setVisible(true);
			}
		});
	}
	/**This method active when there is needing in user input
	 * @param InputType type of input program want
	 * @return char[] legal input to work with*/
	private char[] inputGUI(InputType type){
		String input=null;
		char[] charInput=null;
		if(type==InputType.AddEdge || type==InputType.RemoveEdge){
			if(type==InputType.AddEdge)//pop new edge msg
				input=JOptionPane.showInputDialog("please enter node1,node2 edge");
			else//pop remove edge msg
				input=JOptionPane.showInputDialog("please enter n1,n2 edge to remove");
			if(input!=null){
				charInput=input.toCharArray();
				if(charInput.length!=3 || charInput[1]!=','){
					JOptionPane.showMessageDialog(null, "Illegal input, please try again.");
					return null;
				}
				else{
					if(Character.isLetter(charInput[0]) && Character.isLetter(charInput[2]))
						return charInput;
					else
						JOptionPane.showMessageDialog(null, "please enter only letters");
				}
					
			}
		}	
		else if(type==InputType.RemoveNode){
			input = JOptionPane.showInputDialog("please enter node char to remove.");
			if(input!=null){
				charInput=input.toCharArray();
				if(charInput.length!=1){
					JOptionPane.showMessageDialog(null, "Illegal input, please try again.");
					return null;
				}
				else
					return charInput;
			}
		}
			
		return null;
	}
	/**This private class set an JDialog component and show what in needed at the time*/
	private class dialogPanel extends JDialog{
		public dialogPanel() {//basic define of JDialog
			dialogPanel = new JPanel();
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setIconImage(Toolkit.getDefaultToolkit().getImage(MainPanel.class.getResource("/resources/icon.png")));
			getContentPane().setLayout(new BorderLayout());
			dialogPanel.setLayout(new FlowLayout());
			dialogPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			//change the panel screen location (mid screen)
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		}
		/**This method set the dialog JLabel for the example image of the graph*/
		public void setExample(){
			setTitle("Exapmle Graph");
			getContentPane().add(dialogPanel, BorderLayout.CENTER);
			{
				JLabel lblExample = new JLabel("");//Add JDialog
				lblExample.setIcon(new ImageIcon(dialogPanel.class.getResource("/resources/GraphNodesEdges_1000.gif")));//Add Image
				dialogPanel.add(lblExample);
				this.pack();
			}
		}
		/**This method set the dialog JLabel to the toString graph*/
		public void setToString(){
			setTitle("toString graph");
			getContentPane().add(dialogPanel, BorderLayout.CENTER);
			{
				JLabel lbltoString = new JLabel("");//Add JDialog
				String str = "";
				str = drawPanel.graphs.toString();	
				lbltoString.setText(str);
				lbltoString.setFont(new Font("Tahoma", Font.PLAIN, 14));
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				this.setLocation(dim.width/2-this.getSize().width/2-100, dim.height/2-this.getSize().height/2);
				dialogPanel.add(lbltoString);
				this.pack();
			}
		}
	}
}
