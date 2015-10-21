import java.util.HashMap;
import java.util.Map;

/**
 * @author baevans
 * Encodes a barcode given a zipcode
 */
public class PostnetEncoder {

	/**
	 * String that represents the final zipcode after being decoded
	 */
	private final String zipCode;
	/**
	 * String that represents the binary value of the given barcode
	 */
	private final String binaryRepresentation;
	/**
	 * String that is made up of | and . to represent a barcode
	 */
	private final String barCode;
	
	/**
	 * Single argument constructor that verifies that the entered zipcode was valid
	 * @param zipCode zipcode entered by the user to be encoded
	 */
	public PostnetEncoder(String zipCode)
	{
		if(zipCode.length() == 5 || zipCode.length() == 9 || zipCode.length() == 11)
		{
			this.zipCode=addCheckSum(zipCode);
			this.binaryRepresentation = setBinaryRepresentation(this.zipCode);
			this.barCode = setBarCode(this.binaryRepresentation);
		}
		else
		{
			throw new IllegalArgumentException(
					"zip code must be either 5, 9, or 11 digits");
			//this.zipCodeLength=zipCode.length();
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
	 * method that adds the checksum to a given zipcode
	 * @param zipCode passes in the zipcode entered by the user
	 * @return returns the new zipcode with the checksum added to it
	 */
	public String addCheckSum(String zipCode)
	{
		int[] singleDigits = new int[zipCode.length()];
		int zipCodeSum = 0;
		int checkSum = 0;
		
		for(int i = 0; i<zipCode.length();i++)
		{
			singleDigits[i]= zipCode.charAt(i);
			zipCodeSum = zipCodeSum + singleDigits[i];
		}
		
		while ((zipCodeSum+ checkSum)%10 != 0)
		{
			checkSum++;
		}
		
		return zipCode + checkSum;
		
	}
	
	/**
	 * setter method that sets the binaryrepresentation
	 * @param zipCode zipcode with the checksum added to it
	 * @return string representation of the binary form of the zipcode
	 */
	public String setBinaryRepresentation(String zipCode)
	{
		
		String binaryRepresentation = new String("1");
		
		Map<Character,String> binaryMap = new HashMap<>();
		binaryMap.put('0', "11000");
		binaryMap.put('1', "00011");
		binaryMap.put('2', "00101");
		binaryMap.put('3', "00110");
		binaryMap.put('4', "01001");
		binaryMap.put('5', "01010");
		binaryMap.put('6', "01100");
		binaryMap.put('7', "10001");
		binaryMap.put('8', "10010");
		binaryMap.put('9', "10100");
		for(int i = 0; i<zipCode.length();i++)
		{
			binaryRepresentation += binaryMap.get(zipCode.charAt(i));
		}
		binaryRepresentation += "1";
		return binaryRepresentation;
	}
	
	/**
	 * setter method to create a visual for the barcode
	 * @param binaryRepresentation binary representation obtained from the setter
	 * @return the barcode formatted as . and |
	 */
	public String setBarCode(String binaryRepresentation)
	{
		
		String barCode = new String();
		for (int i =0; i<binaryRepresentation.length(); i++)
		{
			if(binaryRepresentation.charAt(i) == '0')
			{
				barCode += '.';
			}
			else if(binaryRepresentation.charAt(i) == '1')
			{
				barCode += '|';
			}
		}
		
		return barCode;
	}
	
}
