package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class PerformAssembler extends Assembler {

	
	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		Token t = (Token) a.pop();
//		System.out.println("perform"+" "+t+a.remainder(" "));
		System.out.println("perform");
		c.setPerform(t.sval()+" "+a.remainder(" "));
		a.setTarget(c);
	}

}
