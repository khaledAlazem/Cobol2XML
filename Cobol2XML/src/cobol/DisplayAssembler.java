package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class DisplayAssembler extends Assembler {

	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		Token t = (Token) a.pop();
 		c.setDisplay(t.sval()+a.remainder(" ")); //a remainder is used here to consume whatever has been left for this element
 		a.setTarget(c);
	}
 
}
