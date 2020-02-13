import java.io.Serializable;

public class ClassToSerialize implements Serializable{
	
	private String myString;
	private int myInt;
	private transient int myTransInt;
	

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
	public int getMyTransInt() {
		return myTransInt;
	}
	public void setMyTransInt(int myTransInt) {
		this.myTransInt = myTransInt;
	}
	
	
	@Override
	public String toString() {
		return "ClassToSerialize [myString=" + myString + ", myInt=" + myInt + ", myTransInt=" + myTransInt + "]";
	}
	
	
}
