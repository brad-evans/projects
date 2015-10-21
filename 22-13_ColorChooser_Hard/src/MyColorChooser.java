import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * One public class that makes the formatted GUI
 * three private nested classes two of which are handlers and one that
 * does the paint component
 * @author baevans
 *
 */
public class MyColorChooser extends JFrame{

	/**
	 * textfield to display the value of red
	 */
	private final JTextField textField1;
	/**
	 * textfield to display the value of green
	 */
	private final JTextField textField2;
	/**
	 * text field to display the value of blue
	 */
	private final JTextField textField3;
	/**
	 * slider to manipulate how much red there is
	 */
	private final JSlider jSlider1;
	/**
	 * slider to manipulate how much green there is
	 */
	private final JSlider jSlider2;
	/**
	 * slider to manipulate how much blue there is
	 */
	private final JSlider jSlider3;
	
	/**
	 * panel to put all three text fields and all three sliders in
	 */
	private final JPanel jPanelTop;
	
	/**
	 * integer associated with the red portion of color
	 */
	private int redValue;
	/**
	 * integer associated with the green portion of color
	 */
	private int greenValue;
	/**
	 * integer associated with the blue portion of color
	 */
	private int blueValue;
	/**
	 * color used to keep track of the current color being displayed
	 */
	private Color myColor;
	
	
	
	
	
	/**
	 * zero parameter constructor that adds all the necessary J components to the GUI and handlers for those components
	 */
	public MyColorChooser()
	{
		super("Color Changer");
		//setLayout(new GridLayout(1,3,3,3));
		jSlider1 = new JSlider(0,255,0);
		jSlider2 = new JSlider(0,255,0);
		jSlider3 = new JSlider(0,255,0);
		
		textField1 = new JTextField(15);
		textField2 = new JTextField(15);
		textField3 = new JTextField(15);
		textField1.setText(Integer.toString(0));
		textField2.setText(Integer.toString(0));
		textField3.setText(Integer.toString(0));
		
		
		jPanelTop = new JPanel();
		
		
		add(jPanelTop,BorderLayout.NORTH);
		jPanelTop.setLayout(new GridLayout(0,3));
		jPanelTop.add(textField1);
		jPanelTop.add(textField2);
		jPanelTop.add(textField3);
		jPanelTop.add(jSlider1);
		jPanelTop.add(jSlider2);
		jPanelTop.add(jSlider3);
		
		SliderHandler sliderHandler = new SliderHandler();
		
		jSlider1.addChangeListener(sliderHandler);
		jSlider2.addChangeListener(sliderHandler);
		jSlider3.addChangeListener(sliderHandler);
		
		
		TextHandler textHandler = new TextHandler();
		
		textField1.addActionListener(textHandler);
		textField2.addActionListener(textHandler);
		textField3.addActionListener(textHandler);
		
	
		myColor = Color.BLACK;
		ColorPaint paint = new ColorPaint();
		add(paint);
		
		
	}
		/**
		 * Handler that listens for changes in the slider and sets the new values for each part
		 * of the color. Also repaints the rectangle that was made
		 * @author baevans
		 *
		 */
		private class SliderHandler implements ChangeListener
		{

			@Override
			public void stateChanged(ChangeEvent event) {
				// TODO Auto-generated method stub
				
				redValue = jSlider1.getValue();
				greenValue = jSlider2.getValue();
				blueValue = jSlider3.getValue();
				
				textField1.setText(Integer.toString(redValue));
				textField2.setText(Integer.toString(greenValue));
				textField3.setText(Integer.toString(blueValue));
				
				myColor = new Color(redValue,greenValue,blueValue);
				repaint();
				
				
			}
			
		}
		
		/**
		 * handler that listens for something to be typed into the text fields and then sets
		 * the new values for each part of the color. Also repaints the rectangle.
		 * @author baevans
		 *
		 */
		private class TextHandler implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				redValue = Integer.parseInt(textField1.getText());
				greenValue = Integer.parseInt(textField2.getText());
				blueValue = Integer.parseInt(textField3.getText());
				
				jSlider1.setValue(redValue);
				jSlider2.setValue(greenValue);
				jSlider3.setValue(blueValue);
				
				myColor = new Color(redValue,greenValue,blueValue);
				repaint();
				
			}
			
			
			
		}
		
		
		/**
		 * Color paint creates a rectangle that is filled with black and can be repainted
		 * using myColor because it is in the same scope
		 * @author baevans
		 *
		 */
		private class ColorPaint extends JPanel{

		
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				
				g.setColor(myColor);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				
			}

			
		}

		
		
		
		
		
		
		

}
