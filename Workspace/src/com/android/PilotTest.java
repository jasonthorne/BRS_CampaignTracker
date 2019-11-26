package com.android;

public class PilotTest {
	
	private int age;
	private String name;
	
	//constructor:
	private PilotTest() {
		System.out.println("PilotTest Constructed");
	}


	//toString:
	@Override
	public String toString() {
		return "PilotTest [age=" + age + ", name=" + name + "]";
	}
	
	
	//Builder class:
	static class PilotTestBuilder{
		
		private PilotTest pilotTest = new PilotTest();
		
		public PilotTestBuilder setAge(int age) {
			pilotTest.age = age;
			return this;
		}
		
		public PilotTestBuilder setName(String name) {
			pilotTest.name = name;
			return this;
		}
		
		public PilotTest build() {
			return pilotTest;
		}
		
	}

}
