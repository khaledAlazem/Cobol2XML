import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class DivideTests {

	@Test
	public void test() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
	
		t.setString("divide current_base into w_number giving w_number");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		Cobol c = new Cobol();
		c = (Cobol) out.getTarget();
		assertEquals(c.getDivide(),"current_base into w_number giving w_number" );
 	}

}
