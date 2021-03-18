import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class Project4_GUI extends JFrame{

private static final long serialVersionUID = 1L;

//create GUI variables
private JPanel panel;
private JPanel outputAreaPanel;
private JPanel inputAreaPanel;
private JLabel inputFileNameLabel;
private JLabel classToRecompileLabel;
private static JTextField inputFileName;
private static JTextField classToRecompile;
private static JButton buildDirectedGraph;
private static JButton topologicalOrder;
private static JTextPane textAreaP;
private JScrollPane scroll;
private static String s;
private static String s2;


public Project4_GUI() {
	
	panel = new JPanel();
 	outputAreaPanel = new JPanel();
 	inputAreaPanel = new JPanel();
	setFont(new Font("Arial", Font.PLAIN, 16));
 	setTitle("Class Dependency Graph");
 	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 	setBounds(500, 200, 700, 450);
 	panel.setLayout(null);
 	outputAreaPanel.setLayout(null);
 	inputAreaPanel.setLayout(null);
 	setContentPane(panel);
 	
 	inputFileNameLabel = new JLabel("Input file name:");
 	classToRecompileLabel = new JLabel("Class to recompile:");
 	inputFileName = new JTextField("");
 	classToRecompile = new JTextField("");
 	buildDirectedGraph = new JButton("Build Directed Graph");
 	topologicalOrder = new JButton("Topological Order");
	
 	panel.add(inputAreaPanel);
 	inputAreaPanel.setBounds(10, 10, 675, 140);
 	inputAreaPanel.setBorder(BorderFactory.createTitledBorder(""));
 	inputAreaPanel.add(inputFileNameLabel);
 	inputAreaPanel.add(classToRecompileLabel);
 	inputAreaPanel.add(inputFileName);
 	inputAreaPanel.add(classToRecompile);
 	inputAreaPanel.add(buildDirectedGraph);
 	inputAreaPanel.add(topologicalOrder);
	
	panel.add(outputAreaPanel);
	outputAreaPanel.setBounds(10, 150, 675, 250);
	outputAreaPanel.setBorder(BorderFactory.createTitledBorder("Recompliation Order"));
	outputAreaPanel.setBackground(Color.WHITE);
    
    //set fonts
    inputFileNameLabel.setFont(new Font("Arial", Font.BOLD, 12));
    classToRecompileLabel.setFont(new Font("Arial", Font.BOLD, 12));
    inputFileName.setFont(new Font("Arial", Font.BOLD, 12));
    classToRecompile.setFont(new Font("Arial", Font.BOLD, 12));
    buildDirectedGraph.setFont(new Font("Arial", Font.BOLD, 12));
    topologicalOrder.setFont(new Font("Arial", Font.BOLD, 12));
    
    //set layouts
    inputFileNameLabel.setBounds(100, 30, 150, 25);
    inputFileName.setBounds(230, 30, 150, 25);
    buildDirectedGraph.setBounds(400, 30, 200, 25); 
    classToRecompileLabel.setBounds(80, 80, 150, 25);
    classToRecompile.setBounds(230, 80, 150, 25);
    topologicalOrder.setBounds(400, 80, 200, 25);
    
    //set columns for textfields
    inputFileName.setColumns(20);
    classToRecompile.setColumns(20);
    
    //add action listeners for evaluate button
    Project4_Main actionHandler = new Project4_Main();
    buildDirectedGraph.addActionListener(actionHandler);
    topologicalOrder.addActionListener(actionHandler);
    
    //enable enter button to submit list
    panel.getRootPane().setDefaultButton(topologicalOrder);
    
    textAreaP = new JTextPane();
	
	StyledDocument doc = textAreaP.getStyledDocument();
	SimpleAttributeSet center = new SimpleAttributeSet();
	StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
	doc.setParagraphAttributes(0, doc.getLength(), center, false);
	
	textAreaP.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
	textAreaP.setBounds(55, 20, 500, 100);
	scroll = new JScrollPane(textAreaP);
	textAreaP.setEditable(false);
	outputAreaPanel.add(textAreaP);
	outputAreaPanel.add(scroll);
	
}

/*
 * create getters and setters to make variables accessible to other classes
 */
public static JButton getBuildDirectedGraph() {
	return buildDirectedGraph;
	
}

public static JButton getTopologicalOrder() {
	return topologicalOrder;
	
}

public static JTextField getInputFileName() {
	return inputFileName;
}

public static JTextField getClassToRecompile() {
	return classToRecompile;
}

public static String getClassToRecompileText() {
	s = String.valueOf(classToRecompile.getText());
	return s;
}

public static String getInputFileNameText() {
	s2 = String.valueOf(inputFileName.getText());
	return s2;
}

public static void resetInputFileNameText(String resetString) {
	inputFileName.setText(resetString);
}

public static void resetClassToRecompileText(String resetString) {
	classToRecompile.setText(resetString);
}

public static void setTextAreaP(String fileContent) {
	textAreaP.setText(fileContent);
}

}

   