import java.util.Scanner;

/**
 * Driver class that lets the user input to the console and creates necessary objects
 * @author baevans
 *
 */
public class testBarcodes {

	/**
	 * @param args command line arguments
	 */
	public static void main(String args[])
	{
		System.out.print("Enter zip code: ");
		
		Scanner scanner = new Scanner(System.in);
		
		String zipCode = scanner.next();
		
		
		
		PostnetEncoder pe = new PostnetEncoder(zipCode);
		
		System.out.println("Number to encode " + pe.getZipCode());
		System.out.println("add a full height \"frame bar\" to each side");
		System.out.println(pe.getBarCode());
		System.out.println(pe.getBinaryRepresentation());
		
		System.out.println("Enter bar code to decode: ");
		
		
		
		String barCode = scanner.next();
		
		
		PostnetDecoder pd = new PostnetDecoder(barCode);
		
		System.out.println("binary representation of the barcode: ");
		System.out.println(pd.getBinaryRepresentation());
		System.out.println(pd.getZipCode());
		
		System.out.println("Enter a valid product code: ");
		String productCode = scanner.next();
		
		UpcEncoder ue = new UpcEncoder(productCode);
		System.out.println(ue.getBarCode());
		
		
		System.out.println("Enter a valid barcode: ");
		String uBarCode = scanner.next();
		
		UpcDecoder ud = new UpcDecoder(uBarCode);	
		System.out.println("Product Code: " + ud.getProductCode());
		
		
		scanner.close();
		
		
		
	}
	
}
