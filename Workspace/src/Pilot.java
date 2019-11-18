
public class Pilot {
	
	private static int counter = 0;
	private int id;
	
	
	Pilot(){
		id = ++counter;
		
		
		
		
		System.out.println(this.toString());
	}
	
	 @Override
	 public String toString() { 
	     return "id:" + this.id;
	 } 

}


	