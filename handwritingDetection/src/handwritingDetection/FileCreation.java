package handwritingDetection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreation {
	
	public static void createFile(String result) {
		try 
		{
			File file = new File("resource/pdf_output.txt");
			file.createNewFile();
		}
		catch (IOException e) 
		{
			System.out.println("An error occurred creating the file.");
			e.printStackTrace();
		 }
		
		try 
		{
			FileWriter myWriter = new FileWriter("resource/pdf_output.txt");
		    myWriter.write(result);
		    myWriter.close();
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
}
