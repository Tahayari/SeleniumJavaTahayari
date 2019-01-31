package test;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestNGParametersDemo {
	
	@Test
	@Parameters({"MyName"})
	public void test (@Optional("Dan") String name){
		System.out.println("Name is: "+name);
	}

}