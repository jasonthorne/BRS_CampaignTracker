import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ChildClass extends ParentClass implements Serializable { 	//, ObjectInputValidation{

	
	private String childString;
	private int childInt;
	
	//getters & setters:
	public String getChildString() {
		return childString;
	}
	public void setChildString(String childString) {
		this.childString = childString;
	}
	public int getChildInt() {
		return childInt;
	}
	public void setChildInt(int childInt) {
		this.childInt = childInt;
	}
	
	
	@Override
	public String toString() {
		return "ChildClass [childString=" + childString + ", childInt=" + childInt + ", getParentString()="
				+ getParentString() + ", getParentInt()=" + getParentInt() + "]";
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//Helper method for seralization, to save/initialize parent class state:
	
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		
		/*
		 * Read the non-static and non-transient fields of the current class from this stream. 
		 * This may only be called from the readObject method of theclass being deserialized:
		 */
		ois.defaultReadObject();
		
		//===========The orders of thes in read & write should be the same ++++++++++++
		setParentString((String) ois.readObject());
		setParentInt(ois.readInt());
		
	}
	
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		
		oos.defaultWriteObject();
		
		oos.writeObject(getParentString());
		oos.writeInt(getParentInt());
		
	}
	
	
	
	
	//from 2.2.1.2- in tutorial
	/*
	@Override
	public void validateObject() throws InvalidObjectException {
		// TODO Auto-generated method stub
		
	}*/
	
	
}
