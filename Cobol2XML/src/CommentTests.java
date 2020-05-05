import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class CommentTests {

	@Test
	public void testComment() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
	
		t.setString("move entry_number  to w_number");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		Cobol c = new Cobol();
		c = (Cobol) out.getTarget();
		//Another defect , probably it is the because whitespace between strings in the cobol.cbl are not even
		//I had to remove an extra whitespace between "to" and "w_number"
		assertEquals(c.getMove(),"entry_number to w_number" );


 	}

}
