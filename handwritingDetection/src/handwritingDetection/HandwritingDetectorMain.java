package handwritingDetection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HandwritingDetectorMain {
	
	public static void main(String args[]) {
		try 
		{
			File file = new File("resource/output.txt");
			file.createNewFile();
		}
		catch (IOException e) 
		{
			System.out.println("An error occurred creating the file.");
			e.printStackTrace();
		 }
		
		try 
		{
			String result = HandwritingDetector.detectDocumentText("resource/image2.jpg");
			FileWriter myWriter = new FileWriter("resource/output.txt");
		    myWriter.write(result);
		    myWriter.close();
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
}
