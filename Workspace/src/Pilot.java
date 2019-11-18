
public class Pilot {
	
	private static int counter = 0;
	private int id;
	
	
	Pilot(){
	
		counter++;
		
		id = counter;
		
		System.out.println("id: " + id);
	}
	
	 @Override
	 public String toString() { 
	     return "yo";
	 } 

}
