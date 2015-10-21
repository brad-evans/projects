import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author baevans
 *This class has a constructor method to create a Calculator GUI
 *and three nested inner classes to implement my own handlers
 */
public class Calculator extends JFrame{

	
	/**
	 * Holds an array of JButtons that will be used for the numbers
	 */
	private final JButton[] numberCalcButtons;
	/**
	 * Holds an array of JButtons that will be used for the operators
	 */
	private final JButton[] operatorCalcButtons;
	/**
	 * String array with hardcoded button numbers in the order they will be placed
	 */
	private final String[] numberButtonNames = 
		{"7","8","9","4","5","6","1","2","3","0","."};
	/**
	 * String array with hardcoded button operator signs in the order they appear
	 */
	private final String[] operatorButtonNames = 
		{"/","*","-","+","="};
	/**
	 * String leftHandSide keeps track of what the user is typing in
	 */
	private String leftHandSide;
	/**
	 * String rightHandSide is used to store whats been enter after an operator is pushed
	 */
	private String rightHandSide;
	/**
	 * String that stores which operator has been pressed
	 */
	private String operator;
	/**
	 * text field at the top of the calculator that displays what is being typed and the output
	 */
	private JTextField textField;
	/**
	 * Panel that the textfield is put into to make the layout nicer
	 */
	private JPanel textPanel;
	/**
	 * Panel that is filled with all of the buttons in order
	 */
	private JPanel buttonPanel;
	/**
	 * counter used to see if the equals operator has been pressed twice in a row
	 */
	private int reset = 0;
	
	/**
	 * zero argument constructor that creates a calculator GUI
	 */
	public Calculator()
	{
		super("Calculator");
		
		
		textPanel = new JPanel();
		
		textField = new JTextField(20);
		textField.setEditable(false);
	
		
		
		textField.setHorizontalAlignment(JTextField.RIGHT);
		
		
		add(textPanel,BorderLayout.NORTH);
		textPanel.add(textField);
		
		leftHandSide = "";
		rightHandSide = "";
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4,4,3,3));
		buttonPanel.setSize(225, 300);
		
		
		
		
		numberCalcButtons = new JButton[numberButtonNames.length];
		NumberButtonHandler numberHandler = new NumberButtonHandler();
		
		for(int i = 0; i<numberButtonNames.length;i++)
		{
			numberCalcButtons[i] = new JButton(numberButtonNames[i]);
			numberCalcButtons[i].addActionListener(numberHandler);
		}
		
		operatorCalcButtons = new JButton[operatorButtonNames.length];
		OperatorButtonHandler operatorHandler = new OperatorButtonHandler();
		
		EqualsButtonHandler equalsHandler = new EqualsButtonHandler();
		
		for(int i = 0; i<operatorButtonNames.length;i++)
		{
			operatorCalcButtons[i] = new JButton(operatorButtonNames[i]);
			if(i<(operatorButtonNames.length-1))
			{
				operatorCalcButtons[i].addActionListener(operatorHandler);
			}
			else
			{
				operatorCalcButtons[i].addActionListener(equalsHandler);
			}
			
		}
		
		buttonPanel.add(numberCalcButtons[0]);
		buttonPanel.add(numberCalcButtons[1]);
		buttonPanel.add(numberCalcButtons[2]);
		buttonPanel.add(operatorCalcButtons[0]);
		buttonPanel.add(numberCalcButtons[3]);
		buttonPanel.add(numberCalcButtons[4]);
		buttonPanel.add(numberCalcButtons[5]);
		buttonPanel.add(operatorCalcButtons[1]);
		buttonPanel.add(numberCalcButtons[6]);
		buttonPanel.add(numberCalcButtons[7]);
		buttonPanel.add(numberCalcButtons[8]);
		buttonPanel.add(operatorCalcButtons[2]);
		buttonPanel.add(numberCalcButtons[9]);
		buttonPanel.add(numberCalcButtons[10]);
		buttonPanel.add(operatorCalcButtons[4]);
		buttonPanel.add(operatorCalcButtons[3]);
		add(buttonPanel,BorderLayout.CENTER);
		

	}
	/**
	 * Nested class that implements my own handler to store the buttons being pushed in a string
	 * @author baevans
	 * 
	 *
	 */
	private class NumberButtonHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event) {
		
			
			leftHandSide += event.getActionCommand();
			textField.setText(leftHandSide);

			
		}
		
	}
	/**
	 * Nested class that implements my own handler to store which operator was pressed and save the string typed in
	 *thus far
	 * @author baevans
	 *
	 */
	private class OperatorButtonHandler implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent event) {
			
			rightHandSide = leftHandSide;
			leftHandSide = "";
			reset= 0;
			textField.setText("");
			operator = event.getActionCommand();
			
			
		}
		
	}
	/**
	 * Nested class that completes the necessary operations once an event is created for the equals button
	 * @author baevans
	 *
	 */
	private class EqualsButtonHandler implements ActionListener
	{
		
		
		@Override
		public void actionPerformed(ActionEvent event) {
		
			
			if(reset>0)
			{
				rightHandSide = "";
				leftHandSide = "";
				reset = 0;
				textField.setText("");
			}
			else
			{
				try{
					
					reset++;
					
					if(operator.equals("+"))
					{
						rightHandSide = Double.toString((Double.parseDouble(rightHandSide)+Double.parseDouble(leftHandSide)));
						textField.setText(rightHandSide);
						leftHandSide = rightHandSide;
						
					}
					else if (operator.equals("-"))
					{
						rightHandSide = Double.toString((Double.parseDouble(rightHandSide)-Double.parseDouble(leftHandSide)));
						textField.setText(rightHandSide);
						leftHandSide = rightHandSide;
					}
					else if (operator.equals("*"))
					{
						rightHandSide = Double.toString((Double.parseDouble(rightHandSide)*Double.parseDouble(leftHandSide)));
						textField.setText(rightHandSide);	
						leftHandSide = rightHandSide;
					}
					else if (operator.equals("/"))
					{
						rightHandSide = Double.toString((Double.parseDouble(rightHandSide)/Double.parseDouble(leftHandSide)));
						textField.setText(rightHandSide);
						leftHandSide = rightHandSide;
					}
				}
				catch(NullPointerException e)
				{
					textField.setText(leftHandSide);
				}
			}	
			
		}
		
	}
}

