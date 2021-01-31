package handwritingDetection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreation {
	
	public static void createFile(String filePath) {
		try 
		{
			File file = new File("resource/output2.txt");
			file.createNewFile();
		}
		catch (IOException e) 
		{
			System.out.println("An error occurred creating the file.");
			e.printStackTrace();
		 }
		
		try 
		{
			String result = HandwritingDetector.detectDocumentText(filePath);
			FileWriter myWriter = new FileWriter("resource/output2.txt");
		    myWriter.write(result);
		    myWriter.close();
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
}
