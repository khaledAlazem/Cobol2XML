import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class VariableTests {
	Tokenizer t = CobolParser.tokenizer();
	Parser p = CobolParser.start();
	Assembly in;
	Assembly out ;
	@Test
	public void testVariableValue() { 
		t.setString("01 WS-NUM3 PIC S9(3)V9(2) value -123.45.");

		  in = new TokenAssembly(t);
		  out = p.bestMatch(in);

		Cobol c = new Cobol();
		c = (Cobol) out.getTarget();
		System.out.println(c.getVariableValue());
		//important note: due to a parsing defect , when a variable is parsed ,
		//the final dot is not eliminated. So when testing , please do not forget to add the dot.
		assertEquals(c.getVariableValue(),"-123.45." );

	}

	@Test
	public void testVariablePictureClause() {
		t.setString("01 WS-NUM3 PIC S9(3)V9(2) value -123.45.");
		
		  in = new TokenAssembly(t);
		  out = p.bestMatch(in);
		Cobol c = new Cobol();
		c = (Cobol) out.getTarget();
		
		//another defect , integers are always casted to double , so make sure you add .0  when testing
		assertEquals(c.getPictureClause(),"PIC S9(3.0)V9(2.0)" );
	}

	@Test
	public void testVariableName() {
		t.setString("01 WS-NUM3 PIC S9(3)V9(2) value -123.45.");
		
		  in = new TokenAssembly(t);
		  out = p.bestMatch(in);
		Cobol c = new Cobol();
		c = (Cobol) out.getTarget();
		assertEquals(c.getVariableName(),"WS-NUM3" );
	}
	
	@Test 
	public void testVariableIdentifier() {
		t.setString("01 WS-NUM3 PIC S9(3)V9(2) value -123.45.");
		
		  in = new TokenAssembly(t);
		  out = p.bestMatch(in);
		Cobol c = new Cobol();
		c = (Cobol) out.getTarget();
		assertEquals(c.getIdentifier(),01 );
	}
}
