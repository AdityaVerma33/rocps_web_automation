package com.subex.rocps;

public class Test {

	public static void main(String[] args) {
		
		TestC obj = new TestC();
		obj.m1();
		obj.m2();

	}

}

class TestB
{
	private enum Kar{
		val1;
	}
	
	public void m1()
	{
		Kar val =Kar.val1.valueOf("karthik");
		System.out.println(val);
		
	}
}

class TestC extends TestB
{
	private enum Kar{
		val1,val2;
	}
	
	public void m2()
	{
		Kar val =Kar.val2.valueOf("manjunath");
		System.out.println(val);
	}
}