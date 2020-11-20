package model;

import javafx.beans.property.SimpleStringProperty;

public class TEST {
	
	String a = "I am a";
	String b = "I am b";
	
	private final SimpleStringProperty a2 = new SimpleStringProperty(a);
	private final SimpleStringProperty b2 = new SimpleStringProperty(b);
	
    /*
	public TEST(String a, String b) {
		super();
		this.a = a;
		this.b = b;
	}
	*/
	
	

	/*public String getA() {
		return a;
	}*/

	public String getA2() {
		return a2.get();
	}

	/*public void setA2(SimpleStringProperty a2) {
		this.a2 = a2;
	}*/

	public String getB2() {
		return b2.get();
	}

	/*public void setB2(SimpleStringProperty b2) {
		this.b2 = b2;
	}*/

	public void setA(String a) {
		this.a = a;
	}

	/*public String getB() {
		return b;
	}*/

	public void setB(String b) {
		this.b = b;
	}

	

	
	

}
