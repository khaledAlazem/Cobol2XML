import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import parse.Assembly;
import utensil.PubliclyCloneable;

public class AssemblyTests {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testPushAndPopMethods() {
		Object o1 = new Object();
		AssemblyTest at = new AssemblyTest();
		at.push(o1);
 		Object o2 = at.pop();

		assertEquals(o2, o1);	
	}

	@Test
	public void testStackIsEmptyMethod() {
		AssemblyTest at = new AssemblyTest();
		boolean isEmpty = at.stackIsEmpty();
		assertTrue(isEmpty);
	}

	@Test
	public void testElementsConsumendMethod() {
		AssemblyTest at = new AssemblyTest();
		Object o1 = new Object();
	}
	
	@Test 
	public void testGetSetTargetMethods() {
		AssemblyTest at = new AssemblyTest();
		AssemblyTest at1 = new AssemblyTest();
  	    at.setTarget(at1);
  	    
  	    assertTrue(at.getTarget() instanceof PubliclyCloneable);
		
	}
	
	@Test 
	public void testelementsRemainingMethod() {
		AssemblyTest at = new AssemblyTest();
		assertEquals(at.elementsRemaining(), 0);
	}

	/**
	 *This class represents a mock class of the Assembly class
	 *Those implemented methods are not tested here.
	 * Those methods are not covered here, but testing any subclass-
	 *that provides concrete realisation of those methods would do the job.
	 */
	private class AssemblyTest extends Assembly{

		public AssemblyTest() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public Object nextElement() {
			return null;
		}

		@Override
		public String consumed(String delimiter) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String defaultDelimiter() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int length() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object peek() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String remainder(String delimiter) {
			// TODO Auto-generated method stub
			return null;
		}

	}

}
