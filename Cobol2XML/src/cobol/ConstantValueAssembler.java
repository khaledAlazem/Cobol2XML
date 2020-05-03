package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class ConstantValueAssembler extends Assembler{

	@Override
	public void workOn(Assembly a) {
 		Cobol c = new Cobol();
 		Token t = (Token) a.pop();
  		c.setConstantValue(t.nval());
 		
 		t = (Token) a.pop();
 		
 		t = (Token) a.pop();
 		c.setConstantName(t.sval());
 
 		t= (Token) a.pop();
 		c.setLineNumber( (int) Math.round(t.nval()));
 		
 		a.setTarget(c);
	}

}
