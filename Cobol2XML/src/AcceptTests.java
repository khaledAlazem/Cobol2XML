import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class AcceptTests {

	@Test
	public void testAccept() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		
		t.setString("accept omitted");
		
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		
		Cobol c = new Cobol();
		c= (Cobol) out.getTarget();
//		System.out.println(c.getAccept());
		assertEquals(c.getAccept(), "omitted ");
 	}
	
	@Test
	public void testAccept1() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		
		t.setString("ACCEPT WK-INPUT ");
		
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		
		Cobol c = new Cobol();
		c= (Cobol) out.getTarget();
//		System.out.println(c.getAccept());
		assertEquals(c.getAccept(), "WK-INPUT ");
 	}
	

}
