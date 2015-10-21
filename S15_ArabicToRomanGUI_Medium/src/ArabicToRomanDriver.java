import javax.swing.JFrame;

/**
 * driver class that creates a a new arabic to roman GUI
 * @author baevans
 *
 */
public class ArabicToRomanDriver {

	
	/**
	 * main method that is the first thing called
	 * @param args command line arguments
	 */
	public static void main (String args[])
	{
		ArabicToRomanGUI myGUI = new ArabicToRomanGUI();
		myGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myGUI.setSize(300,150);
		myGUI.setVisible(true);
		myGUI.setResizable(false);
	}
}
