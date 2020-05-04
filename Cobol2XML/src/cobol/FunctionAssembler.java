package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class FunctionAssembler extends Assembler{

	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		Token t = (Token) a.pop();
//		c.setFunction(t.sval());
		
		
		System.out.println("from function assembler"+" t value"+t);
//		System.out.println("from function  remainder "+" "+ a.remainder("-"));
		System.out.println("from function assembler"+" "+a.elementsConsumed());
		
		a.setTarget(c);
		
	}

}
