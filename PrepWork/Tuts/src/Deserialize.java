import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserialize {
	
	public static Object deSerializeStuff(String inputFile) throws IOException, ClassNotFoundException {
		
		FileInputStream fileToRead = new FileInputStream(inputFile);
		
		ObjectInputStream objToRead = new ObjectInputStream(fileToRead);
		
		Object obj = objToRead.readObject();
		
		objToRead.close();
		
		return obj;
		
	}

}
