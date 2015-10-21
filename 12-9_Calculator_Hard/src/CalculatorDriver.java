import javax.swing.JFrame;

public class CalculatorDriver {
/**@author baevans
 * driver class to create a new calulator GUI object
 * @param args command line arguments
 */
	
	public static void main (String args[])
	{
		Calculator myCalculator = new Calculator();
		myCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myCalculator.setSize(275,375);
		myCalculator.setVisible(true);
		myCalculator.setResizable(false);
	}
}
