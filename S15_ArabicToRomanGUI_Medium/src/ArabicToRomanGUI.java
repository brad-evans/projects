import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * One public GUI class that extends JFrame 
 * and one private nested class to implement my own handler
 * 
 * @author baevans
 *
 */
public class ArabicToRomanGUI extends JFrame{

	/**
	 * JLabel that tells you which box is arabic
	 */
	private final JLabel arabicLabel;
	/**
	 * Jlabel that tells you which box is roman
	 */
	private final JLabel romanLabel;
	/**
	 * Text Area used to display the arabic equivalent of the roman number
	 */
	private final JTextArea arabicTextArea;
	/**
	 * Text Area used to display and enter the Roman numerals
	 */
	private final JTextArea romanTextArea;
	/**
	 * Panel used to put the label and text area together
	 */
	private final JPanel arabicPanel;
	/**
	 * Panel used to put the label and text area together
	 */
	private final JPanel romanPanel;
	/**
	 * map to change the roman numerals to certain Arabic digits
	 */
	private final Map<Character,String> romanToArabicMap;
	/**
	 * string of roman numerals that will be converted and summed together
	 */
	private String sumString;
	/**
	 *  the arabic equivalent of the sum string
	 */
	private int arabicSum;
	
	
	/**
	 * no argument constructor that creates a GUI to be used to convert numbers from Roman to Arabic
	 */
	public ArabicToRomanGUI()
	{
		super("Arabic To Roman");
		//setLayout(new FlowLayout());
		
		
		romanPanel = new JPanel();
		
		romanLabel = new JLabel("Roman Number");
		romanTextArea = new JTextArea(3,15);
		RomanHandler romanHandler = new RomanHandler();
		romanTextArea.addKeyListener(romanHandler);
		add(romanPanel,BorderLayout.NORTH);
		romanPanel.add(romanLabel);
		romanPanel.add(romanTextArea);
		
		
		arabicPanel = new JPanel();
		
		arabicLabel = new JLabel("Arabic Number");
		arabicTextArea = new JTextArea(3,15);
		add(arabicPanel,BorderLayout.SOUTH);
		arabicPanel.add(arabicLabel);
		arabicPanel.add(arabicTextArea);
		
		
		
		
		romanToArabicMap =  new HashMap<>();
		romanToArabicMap.put('I',"1");
		romanToArabicMap.put('V',"5");
		romanToArabicMap.put('X',"10");
		romanToArabicMap.put('L',"50");
		romanToArabicMap.put('C',"100");
		romanToArabicMap.put('D',"500");
		romanToArabicMap.put('M',"1000");
		
		sumString = "";
		
	}	
		/**
		 * handler that listens for events in the roman text area and takes appropiate action
		 * @author baevans
		 *
		 */
		private class RomanHandler implements KeyListener
		{

			/** 
			 * Overrided keyPressed method that gets ride of extra white space when entere is pressed
			 */
			@Override
			public void keyPressed(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getKeyCode()==KeyEvent.VK_ENTER)
				{
					event.consume();
				}
			}

			/** 
			 * Overrided keyReleased method that uses the string argument from the Roman text area
			 * and maps it to the Arabic equivalent using certain rules to check the numeral after each one
			 */
			@Override
			public void keyReleased(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getKeyCode()==KeyEvent.VK_ENTER)
				{
					
					arabicSum=0;
					
					sumString += romanTextArea.getText();
					System.out.println(sumString.length());
					
					
					
					for(int i =0; i<sumString.length();i++)
					{
						
						if(i<(sumString.length()-1))
						{
							
							if(sumString.charAt(i)=='I' && (sumString.charAt(i+1)=='V' || sumString.charAt(i+1)== 'X'))
							{
								arabicSum += (Integer.parseInt(romanToArabicMap.get(sumString.charAt(i+1))) 
										- Integer.parseInt(romanToArabicMap.get(sumString.charAt(i))));
								
								i++;
							}
							else if(sumString.charAt(i)=='X' && (sumString.charAt(i+1)=='L' || sumString.charAt(i+1)== 'C'))
							{
								arabicSum += (Integer.parseInt(romanToArabicMap.get(sumString.charAt(i+1))) 
										- Integer.parseInt(romanToArabicMap.get(sumString.charAt(i))));
							
								i++;
							}
							else if(sumString.charAt(i)=='C' && (sumString.charAt(i+1)=='D' || sumString.charAt(i+1)== 'M'))
							{
								arabicSum += (Integer.parseInt(romanToArabicMap.get(sumString.charAt(i+1))) 
										- Integer.parseInt(romanToArabicMap.get(sumString.charAt(i))));
							
								i++;
							}
							else
							{
								arabicSum += Integer.parseInt(romanToArabicMap.get(sumString.charAt(i)));
							}
						}
						else if (i<sumString.length())
						{
							arabicSum += Integer.parseInt(romanToArabicMap.get(sumString.charAt(i)));
						}
					}
					
					sumString = "";
					arabicTextArea.setText(Integer.toString(arabicSum));
					
					
					
					
				}
				
			}

			@Override
			public void keyTyped(KeyEvent event) {
				// TODO Auto-generated method stub
				
				
			}
			
		}
		
		
	}


