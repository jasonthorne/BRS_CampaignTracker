import java.io.Serializable;

//public class ParentClass { //note, parent does NOT implement Serializable +++++++++++++++
public class ParentClass implements Serializable{
	
	private String parentString;
	private int parentInt;
	
	//----------
	TestChild testChild = new TestChild(); //inner class (made with parent during serialization)
	
	//---------
	
	//getters & setters:
	public String getParentString() {
		return parentString;
	}
	public void setParentString(String parentString) {
		this.parentString = parentString;
	}
	public int getParentInt() {
		return parentInt;
	}
	public void setParentInt(int parentInt) {
		this.parentInt = parentInt;
	}
	
	@Override
	public String toString() {
		return "ParentClass [parentString=" + parentString + ", parentInt=" + parentInt + "getPtestChildString() " + testChild.getTestChildString() + "]";
	}
	
}
