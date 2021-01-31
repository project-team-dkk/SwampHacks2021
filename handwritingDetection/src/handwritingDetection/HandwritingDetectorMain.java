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
			
			String pdfFilePath = (args.length >= 1) ? args[0] : "gs://swamphacks21/SwampHacks.pdf";
			File pdfFile = new File(pdfFilePath);
			String pdfFileName = pdfFile.getName();
			UploadObject.uploadObject("symmetric-flare-303303", "swamphacks21",
					pdfFileName, pdfFilePath);
			String gcsSrcPath = "gs://swamphacks21/" + pdfFileName;
			
//<<<<<<< HEAD
//			String result = HandwritingDetector.detectDocumentText("resource/image2.jpg");
//			FileWriter myWriter = new FileWriter("resource/output2.txt");
//			myWriter.write(result);
//			myWriter.close();
			
			String text = Detect.detectDocumentsGcs(gcsSrcPath, "gs://swamphacks21/sample");
			FileWriter myWriter = new FileWriter("resource/pdf_output.txt");
			myWriter.write(text);
			myWriter.close();
//=======
//			String result = HandwritingDetector.detectDocumentText("resource/image3.jpg");
//			FileWriter myWriter = new FileWriter("resource/output.txt");
//		    myWriter.write(result);
//		    myWriter.close();
//>>>>>>> branch 'main' of https://github.com/project-team-dkk/SwampHacks2021
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
	}
	
}
