package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class DivideAssembler extends Assembler  {

	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		Token t = (Token) a.pop();
 //		t = (Token) a.pop();
//		t = (Token) a.pop();
//		t = (Token) a.pop();
 //		System.out.println(a.remainder(" "));
 

		
//		
//		System.out.println("results"+" "+t);//result of division 
//
//		t = (Token) a.pop();
//		System.out.println("giving"+" "+t);//giving 
//		
//		t = (Token) a.pop();
//		System.out.println("something else"+" "+t);
//		
//		t = (Token) a.pop();
//		System.out.println("into"+" "+t); // into 
//		
//		t = (Token) a.pop();
//		System.out.println("something"+" "+t); //something
//		
//		t = (Token) a.pop();
//		System.out.println("divide"+" "+t); //divide
		
//		t = (Token) a.pop();
   a.setTarget(c);
		
		
	}

}
