import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Project4_Main implements ActionListener {
	
	private Project4_Graph<String> dGraph;
	
	public void actionPerformed(ActionEvent event) {
	
		/*
		 * Build Directed Graph Button
		 */
		if (event.getSource() == Project4_GUI.getBuildDirectedGraph()) {

			/*
			 *  Create new instance of Project4_Graph
			 */
			dGraph = new Project4_Graph<>();

			try {
				/*
				 *  check input is not empty
				 */
				if (Project4_GUI.getInputFileNameText().isEmpty()) {
					throw new NullPointerException();
				}

				/*
				 *  build graph from input file
				 */
				dGraph.buildGraph(dGraph.tokenize(Project4_GUI.getInputFileNameText()));

				/*
				 *  notify user if but successfully
				 */
				JOptionPane.showMessageDialog(null, "Graph Built Successfully", "Message",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(null, "File name field is empty", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e2) {
				JOptionPane.showMessageDialog(null, "File did not open", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		/*
		 * Topological Button
		 */
		else if (event.getSource() == Project4_GUI.getTopologicalOrder()) {

			try {
				Project4_GUI.setTextAreaP(dGraph.topologicalOrder(Project4_GUI.getClassToRecompileText()));

			} catch (InvalidClassNameE e) {
				JOptionPane.showMessageDialog(null, "Invalid Class Name: " + Project4_GUI.getClassToRecompileText(),
						"Error", JOptionPane.ERROR_MESSAGE);
			} catch (ContainsCycleE f) {
				JOptionPane.showMessageDialog(null, "Graph contains cycle", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}


		/*
		 *  clear data if user clicks the textbar again after creating graph and getting topological order
		 */
		Project4_GUI.getInputFileName().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Project4_GUI.resetInputFileNameText("");
				Project4_GUI.resetClassToRecompileText("");

			}
		});
	}

	/*
	 * main method
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {

					Project4_GUI frame = new Project4_GUI();
					frame.setVisible(true);

				} 
				catch (Exception t) {
					t.printStackTrace();
				}
				;
			};
		});
	}
}
/*
 * class to handle invalid class names
 */
class InvalidClassNameE extends Exception {
   
	private static final long serialVersionUID = 1L;

	InvalidClassNameE() {}
}
/*
 * class to handle cycle exceptions
 */
class ContainsCycleE extends Exception {
   
	private static final long serialVersionUID = 1L;

	ContainsCycleE() {}
}