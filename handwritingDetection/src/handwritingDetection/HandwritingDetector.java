package handwritingDetection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Block;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.Page;
import com.google.cloud.vision.v1.Paragraph;
import com.google.cloud.vision.v1.Symbol;
import com.google.cloud.vision.v1.TextAnnotation;
import com.google.cloud.vision.v1.Word;
import com.google.protobuf.ByteString;

public class HandwritingDetector {
	
	/**
	 * Main method of detecting texts from image file.
	 * @link https://cloud.google.com/vision/docs/handwriting
	 * @param filePath      The path to the image file
	 * @throws IOException
	 */
	public static String detectDocumentText(String filePath) throws IOException {
		  List<AnnotateImageRequest> requests = new ArrayList<>();

		  ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

		  Image img = Image.newBuilder().setContent(imgBytes).build();
		  Feature feat = Feature.newBuilder().setType(Type.DOCUMENT_TEXT_DETECTION).build();
		  AnnotateImageRequest request =
		      AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
		  requests.add(request);

		  // Initialize client that will be used to send requests. This client only needs to be created
		  // once, and can be reused for multiple requests. After completing all of your requests, call
		  // the "close" method on the client to safely clean up any remaining background resources.
		  try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) 
		  {
		    BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
		    List<AnnotateImageResponse> responses = response.getResponsesList();
		    client.close();

		    for (AnnotateImageResponse res : responses) 
		    {
		    	if (res.hasError()) {
			        System.out.format("Error: %s%n", res.getError().getMessage());
			        return "Error: " + res.getError().getMessage() + "\n";
		    	}

		      // For full list of available annotations, see http://g.co/cloud/vision/docs
		      TextAnnotation annotation = res.getFullTextAnnotation();
		      for (Page page : annotation.getPagesList()) {
		        String pageText = "";
		        for (Block block : page.getBlocksList()) {
		          String blockText = "";
		          for (Paragraph para : block.getParagraphsList()) {
		            String paraText = "";
		            for (Word word : para.getWordsList()) {
		              String wordText = "";
		              for (Symbol symbol : word.getSymbolsList()) {
		                wordText = wordText + symbol.getText();
		              }
		              
		              paraText = String.format("%s %s", paraText, wordText);
		              		              
		            }
		            blockText = blockText + paraText;
		          }
		          pageText = pageText + blockText;
		        }
		      }
		      return annotation.getText();
		    }
		  }
		  return "";
		}

}
