import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serialize {
	
	//serialize the java object and save it to a file:
	public static void SerializeStuff(Object obj, String filePath) throws IOException{
		
		FileOutputStream fileToWrite = new FileOutputStream(filePath);  //stream to write file (passing it the file path)
		
		ObjectOutputStream objToWrite = new ObjectOutputStream(fileToWrite); //
		
		objToWrite.writeObject(obj); 
		
		fileToWrite.close(); //close file 
		
	}
	
}
