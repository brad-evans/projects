import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author baevans
 * decodes a product code given a barcode
 */
public class UpcDecoder {

	/**
	 * string that represents the binary barcode
	 */
	private final String barCode;
	/**
	 * string that represents the 11 digit product code
	 */
	private final String productCode;
	
	/**
	 * single argument constructor taking in the binary barcode and making sure its valid
	 * @param barCode barcode entered by the user
	 */
	public UpcDecoder(String barCode) {
		
		if(barCode.length()==95 && barCode.substring(0, 3).equals("101") 
				&& barCode.substring(92, 95).equals("101") 
				&& barCode.substring(45, 50).equals("01010"))
		{
			this.barCode = barCode;
			this.productCode = setProductCode(this.barCode);
		}
		else
		{
			throw new IllegalArgumentException(
					"the barcode entered was invalid");
		}
		
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
	 * getter method for the product code
	 * @return string representation of the product code
	 */
	public String getProductCode()
	{
		return this.productCode;
	}
	
	/**
	 * setter method that converts the binary representation to a product code 
	 * using hasp maps to change it, also checks for valid check digit
	 * @param barCode barcode entered by the user
	 * @return returns the final product code associated with the barcode
	 */
	public String setProductCode(String barCode)
	{
		String leftBarCodes = barCode.substring(3,45);
		String rightBarCodes = barCode.substring(50,92);
		
		Map<String,String> leftMap = new HashMap<>();
		leftMap.put("0001101", "0");
		leftMap.put("0011001", "1");
		leftMap.put("0010011", "2");
		leftMap.put("0111101", "3");
		leftMap.put("0100011", "4");
		leftMap.put("0110001", "5");
		leftMap.put("0101111", "6");
		leftMap.put("0111011", "7");
		leftMap.put("0110111", "8");
		leftMap.put("0001011", "9");
		
		Map<String,String> rightMap = new HashMap<>();
		rightMap.put("1110010", "0");
		rightMap.put("1100110", "1");
		rightMap.put("1101100", "2");
		rightMap.put("1000010", "3");
		rightMap.put("1011100", "4");
		rightMap.put("1001110", "5");
		rightMap.put("1010000", "6");
		rightMap.put("1000100", "7");
		rightMap.put("1001000", "8");
		rightMap.put("1110100", "9");
		
		
		String productCode = new String();
		for (int i = 0; i<6; i++)
		{
			productCode += leftMap.get(leftBarCodes.substring(i*7,(i*7)+7));
		}
		
		for (int i = 0; i<5; i++)
		{
			productCode += rightMap.get(rightBarCodes.substring(i*7,(i*7)+7));
		}
		
		int sum = 0;
		for(int i = 0;i<11;i++)
		{
			if(i%2 ==0)
			{
				sum += (3*Character.getNumericValue(productCode.charAt(i)));
			}
			else if(i%2==1)
			{
				sum += Character.getNumericValue(productCode.charAt(i));
			}	
		}
		
		int checkDigit = 10 - (sum%10);

		if(checkDigit == Integer.parseInt(rightMap.get(rightBarCodes.substring(35))))
		{
			System.out.println("Check digit - " + checkDigit + ", ok");
		}
		else
		{
			throw new IllegalArgumentException(
					"the check digit for the barcode entered was invalid");
		}
		
		
		return productCode;
	}
	
}
