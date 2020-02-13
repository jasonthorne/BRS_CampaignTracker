import java.io.Serializable;

public class myObject implements Serializable{
	
	private String myString;
	private int myInt;
	private transient String myTransInt;
	

	//getters & setters:
	public String getMyString() {
		return myString;
	}
	public void setMyString(String myString) {
		this.myString = myString;
	}
	public int getMyInt() {
		return myInt;
	}
	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}
	public String getMyTransInt() {
		return myTransInt;
	}
	public void setMyTransInt(String myTransInt) {
		this.myTransInt = myTransInt;
	}
	
	
	@Override
	public String toString() {
		return "myObject [myString=" + myString + ", myInt=" + myInt + ", myTransInt=" + myTransInt + "]";
	}
	
	
	
}
