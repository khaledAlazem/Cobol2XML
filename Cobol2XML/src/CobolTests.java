import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;

public class CobolTests {

	Cobol c= new Cobol();
	private Object o1 ;
	private Object o2;
	private boolean results;

	/**
	 * what the equal(Object o1 , Object o2) does is that it takes two arguments of type Object
	 * and then checks whether they are equal 
	 */
	@Test
	public  void testTheEqualMethodForBooleanValues() {
		this.o1 = this.o2;
		this.results = Cobol.equal(o1, o2);
		assertTrue(this.results); //passes the test 


		this.o1 = new Object();
		this.results = Cobol.equal(o1, o2);
		assertFalse(this.results); //passes the test 


		this.o2 = new Object();
		this.results = Cobol.equal(o2, o1);
		assertFalse(this.results); //passes the test 

		this.o2 = this.o1;
		this.results = Cobol.equal(o2, o1);
		assertTrue(this.results); //passes the test 

	}

	@Test
	public void testTheEqualMethodForNullValues() {
		this.o1 = null;
		this.o2 = null;
		this.results = Cobol.equal(o1, o2);
		assertTrue(this.results); //passes the test 

	}

	/**
	 * what the equals(Object o1) does is that it takes one argument of type Object
	 * the object must be of type Cobol
	 */
	@Test
	public void testEqualsMethod() {
		Cobol c1 = new Cobol();
		
		//it is not instance of Cobol
		Object o3= new Object();  
		this.results = c1.equals(o3);
		assertFalse(this.results); //passes 
		
		//It is instance of cobol
		this.results = c1.equals(c);
		assertTrue(this.results); //passes 
		
		//program_id equals c.program_id
		c.setProgram_id("123");
		this.results = c.equals(c);
		assertTrue(this.results);
		


	}


}
