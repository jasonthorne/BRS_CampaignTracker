import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//public class ParentClass { //note, parent does NOT implement Serializable +++++++++++++++
public class ParentClass implements Serializable{
	
	private String parentString;
	private int parentInt;
	
	//----------
	TestChild testChild = new TestChild("yo"); //inner class (made with parent during serialization)
	
	List<TestChild>testChildList = new ArrayList<TestChild>();
	
	
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
	
	
	//-------
	
	public void addChild(String testChildString) {
		testChildList.add(new TestChild(testChildString));
	}
	//-------
	
	@Override
	public String toString() {
		return "ParentClass [parentString=" + parentString + ", parentInt=" + parentInt + "getPtestChildString() "
				+ testChild.getTestChildString() + "testChildList: " + testChildList.get(0).getTestChildString() + "]";
	}
	
}
