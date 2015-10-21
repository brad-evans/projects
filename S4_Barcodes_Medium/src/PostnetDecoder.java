import java.util.HashMap;
import java.util.Map;

/**
 * @author baevans
 * decodes a zipcode given a barcode
 */
public class PostnetDecoder {

	/**
	 * String that is made up of | and . to represent a barcode
	 */
	private final String barCode;
	/**
	 * String that represents the binary value of the given barcode
	 */
	private final String binaryRepresentation;
	/**
	 * String that represents the final zipcode after being decoded
	 */
	private final String zipCode;
	
	/**
	 * Single argument constructor that verifies a valid barcode was entered and 
	 * creates that barcode and its other representations
	 * @param barCode barcode that is entered by the user
	 */
	public PostnetDecoder(String barCode)
	{
		if(barCode.length() == 32 || barCode.length() == 52 || barCode.length() == 62)
		{
			this.barCode = barCode;
			this.binaryRepresentation = setBinaryRepresentation(this.barCode);
			this.zipCode = setZipCode(this.binaryRepresentation);
			
		}
		else
		{
			throw new IllegalArgumentException(
					"bar code must be either 32, 52, or 62 characters");
		}
		
		
	}
	/**
	 * getting method to return the zipcode
	 * @return string that represents the zipcode
	 */
	public String getZipCode()
	{
		return this.zipCode;
	}
	
	/**
	 * getter method for the binary representation
	 * @return string that represents the binary representation
	 */
	public String getBinaryRepresentation()
	{
		return this.binaryRepresentation;
	}
	/**
	 * getter method for the barcode
	 * @return string that represents the barcode
	 */
	public String getBarCode()
	{
		return this.barCode;
	}
	
	/**
	 * setter method that changes all of the dots and lines to 0's and 1's
	 * @param barCode the barcode that was entered by the user
	 * @return String that is the binary representation
	 */
	public String setBinaryRepresentation(String barCode)
	{
		String binaryRepresentation = new String();
		for(int i = 0; i<barCode.length();i++)
		{
			if(barCode.charAt(i) == '.')
			{
				binaryRepresentation += '0';
			}
			else if(barCode.charAt(i) == '|') 
			{
				binaryRepresentation += '1';
			}
		}
		
		return binaryRepresentation;
	}
	
	/**
	 * setter method that finds the zipcode given a binary representation
	 * @param binaryRepresentation binary representation that was made from setBinaryRepresentation
	 * @return final decoded zipcode
	 */
	public String setZipCode (String binaryRepresentation)
	{
		String zipCode = new String();
		String newBinaryRepresentation = binaryRepresentation.substring(1,(binaryRepresentation.length()-6));
		System.out.println(newBinaryRepresentation);
		
		Map<String,String> zipMap = new HashMap<>();
		zipMap.put("11000", "0");
		zipMap.put("00011", "1");
		zipMap.put("00101", "2");
		zipMap.put("00110", "3");
		zipMap.put("01001", "4");
		zipMap.put("01010", "5");
		zipMap.put("01100", "6");
		zipMap.put("10001", "7");
		zipMap.put("10010", "8");
		zipMap.put("10100", "9");
		
		for (int i = 0; i<(newBinaryRepresentation.length()/5);i++)
		{
			zipCode += zipMap.get(newBinaryRepresentation.substring(i*5,(i*5)+5));
		}
		
		return zipCode;
	}
	
}
