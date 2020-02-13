
public class Main {
	
	/**
	 * 
	 *Serialization tutorial from: https://www.techbeamers.com/java-serialization-tutorial/
	 *
	 */

	public static void main(String[] args) {
		
		String fileName1 = "testFile1.txt";
		
		//create obj and set vars:
		myObject objToSerialize = new myObject();
		objToSerialize.setMyString("here be my string");
		objToSerialize.setMyInt(333);
		objToSerialize.setMyTransInt("I shouldn't be serialized!");
		
		//---------------------------------------------------------------
		//serialise the object to a file:
		try {
			Serialize.serializeStuff(objToSerialize, fileName1);
			
		}catch(Exception e) {
			System.out.println("Exception is: " + e);
		}
		
		//----------------------------------------------------------------
		//deserialise the file to an object:
		myObject deserializedObj = null;
		
		try {
			deserializedObj = (myObject) Deserialize.deSerializeStuff(fileName1);
			
		}catch(Exception e) {
			System.out.println("Exception is: " + e);
		}
		
		//----------------------------------------------------------------
		//print deserializedObject:
		System.out.println(deserializedObj);
			
		//print objectToSerialze:
		System.out.println(objToSerialize);
		
		
		//=============================================================================
		//2.2- Advance Java Serialization With Inheritance.
		
		
		String fileName2 = "childClass.txt";
		
		ChildClass childClass = new ChildClass(); 
		
		//set parent vars through the child
		childClass.setParentString("Im the parent string");
		childClass.setParentInt(333);
		
		//set child vars:
		childClass.setChildString("Im the child string");
		childClass.setChildInt(3);
		
		//---------------------------------------------------------------
		//serialise the child object to a file:
		
		try {
			Serialize.serializeStuff(childClass, fileName2);
			
		}catch(Exception e) {
			System.out.println("Exception is: " + e);
		}
		
		
		//---------------------------------------------------------------
		//deserialise the child file to an object:
		
		try {
			ChildClass newChild = (ChildClass) Deserialize.deSerializeStuff(fileName2);
			
			System.out.println(newChild); //print new child toString
			
			
		}catch(Exception e) {
			System.out.println("Exception is: " + e);
		}
		
		//=============================================================================
		
		
		ParentClass parentClass = new ParentClass();
		
		String fileName3 = "parentClass.txt";
		
		try {
			Serialize.serializeStuff(parentClass, fileName3);
			
			System.out.println(parentClass); //print new child toString
			
		}catch(Exception e) {
			System.out.println("Exception is: " + e);
		}
		

	}

}
