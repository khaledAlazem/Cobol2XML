import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class PerformTests {

	@Test
	public void testPerform() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		
		t.setString("perform decimal-to-base thru decimal-to-base-ex");
		
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		
		Cobol c = new Cobol();
		c= (Cobol) out.getTarget();
 //		System.out.println(c.getPerform());
		assertEquals(c.getPerform(), "decimal-to-base thru decimal-to-base-ex");
	}

}
