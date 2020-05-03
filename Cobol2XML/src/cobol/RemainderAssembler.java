package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class RemainderAssembler extends Assembler {

	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		Token t = (Token) a.pop();
 	}

}
