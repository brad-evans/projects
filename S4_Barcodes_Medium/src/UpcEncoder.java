import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author baevans
 * dencodes a barcode given a product code
 */
public class UpcEncoder {

	/**
	 * string that represents the 11 digit product code
	 */
	private final String productCode;
	/**
	 * string that represents the binary barcode
	 */
	private final String barCode;
	
	
	/**
	 * single argument constructor taking in the product code and making sure its valid
	 * @param productCode product code entered by the user
	 */
	public UpcEncoder(String productCode)
	{
		if(productCode.length()==11)
		{
			this.productCode = productCode;
			this.barCode = setBarCode(this.productCode);
		}
		else
		{
			throw new IllegalArgumentException(
					"product code must be 11 digits");
		}
	}
	
	/**
	 * getter method for the product code
	 * @return string representation of the product code
	 */
	public String getProductCode()
	{
		return this.productCode;
	}
	/**
	 * getter method for the barcode
	 * @return string representation of the barcode
	 */
	public String getBarCode()
	{
		return this.barCode;
	}
	
	/**
	 * setter method that converts the given product code into the binary representation 
	 * barcode including a check digit and barrier values
	 * @param productCode product code entered by the user
	 * @return returns the final barcode associated with the product code
	 */
	public String setBarCode(String productCode)
	{
		String leftDigits = productCode.substring(0,6);
		String rightDigits = productCode.substring(6,11);
		
		Map<Character,String> leftMap = new HashMap<>();
		leftMap.put('0', "0001101");
		leftMap.put('1', "0011001");
		leftMap.put('2', "0010011");
		leftMap.put('3', "0111101");
		leftMap.put('4', "0100011");
		leftMap.put('5', "0110001"); 
		leftMap.put('6', "0101111");
		leftMap.put('7', "0111011");
		leftMap.put('8', "0110111");
		leftMap.put('9', "0001011");
		
		Map<Character,String> rightMap = new HashMap<>();
		rightMap.put('0', "1110010");
		rightMap.put('1', "1100110");
		rightMap.put('2', "1101100");
		rightMap.put('3', "1000010");
		rightMap.put('4', "1011100");
		rightMap.put('5', "1001110");
		rightMap.put('6', "1010000");
		rightMap.put('7', "1000100");
		rightMap.put('8', "1001000");
		rightMap.put('9', "1110100");
		
		
		String barCode = "101";
		
		for(int i = 0;i<leftDigits.length();i++)
		{
			barCode += leftMap.get(leftDigits.charAt(i));
		}
		
		barCode += "01010";
		
		for (int i=0; i <rightDigits.length();i++)
		{
			barCode +=rightMap.get(rightDigits.charAt(i));
		}
		
		int sum = 0;
		for (int i = 0;i<productCode.length();i++)
		{
			if(i%2==0)
			{
				sum += (3*Character.getNumericValue(productCode.charAt(i)));
			}
			else if(i%2==1)
			{
				sum += Character.getNumericValue(productCode.charAt(i));
			}	
		}
		int checkDigit = 10 - (sum%10);

		char charCheckDigit = (char) (checkDigit+48);
		
		barCode += rightMap.get(charCheckDigit);
		
		barCode += "101";
		
		return barCode;
		
	}
		
	
	
	
	
}
