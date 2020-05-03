import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class DisplayTests {

	
	/**
	 * Testing the DisplayAssembeler Class 
	 * The string used here is different from the one in the cobol.cbl and the reason of doing this-
	 * is to make sure earlier expectations are still valid
	 */
	@Test
	public void testDisplay() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
	
		t.setString("display \"I AN AM EXAMPLE OF MOST USED SIMPLE COBOL DISPLAY \" Hey");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		Cobol c = new Cobol();
		c = (Cobol) out.getTarget();
//		System.out.println(c.getDisplay());
		assertEquals(c.getDisplay(),"\"I AN AM EXAMPLE OF MOST USED SIMPLE COBOL DISPLAY \"Hey" );
 	}
	

}
