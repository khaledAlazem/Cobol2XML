package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class RemarksAssembler extends Assembler {

	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		Token t = (Token) a.pop();
		t = (Token) a.pop();
 		a.setTarget(c);
	}

}
