package handwritingDetection;

import java.io.IOException;

public class DetectorMain {
	
	/**
	 * Main method
	 * @throws Exception 
	 */
	public static void main(String args[]) throws Exception {
		try 
		{
			String result = Detect.detectDocumentsGcs("gs://swamphacks21/SwampHacks.pdf", "gs://swamphacks21/SwampHacks/output");
			FileCreation obj = new FileCreation();
			obj.createFile(result);
			
			
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
}
