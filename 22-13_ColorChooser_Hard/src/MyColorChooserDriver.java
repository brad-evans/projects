import javax.swing.JFrame;

/**
 * color chooser driver that creates an instance of the color chooser GUI
 * @author baevans
 *
 */
public class MyColorChooserDriver {

	/**
	 * main method that is called when the program starts
	 * @param args command line arguments
	 */
	public static void main (String args[])
	{
		MyColorChooser myColorChooser = new MyColorChooser();
		
		myColorChooser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myColorChooser.setSize(700,200);
		myColorChooser.setVisible(true);
		myColorChooser.setResizable(true);
		
		
	}
	
	
	
}
