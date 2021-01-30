package handwritingDetection;

import java.io.IOException;

public class HandwritingDetectorMain {
	
	/**
	 * Main method
	 */
	public static void main(String args[]) {
		
		
		try 
		{
			HandwritingDetector.detectDocumentText("resource/helloWorld.jpg");
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
}
