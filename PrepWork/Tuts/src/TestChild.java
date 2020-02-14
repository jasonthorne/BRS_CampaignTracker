import java.io.Serializable;

public class TestChild implements Serializable{
	
	private String testChildString;
	
	public String getTestChildString() {
		return testChildString;
	}
	
	
	public TestChild(String testChildString) {
		this.testChildString = testChildString;
	}
	
	
	

}
