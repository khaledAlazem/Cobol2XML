package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class VariableAssembler extends Assembler {

	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		
		String remainderText= a.remainder(" "); // text that comes after word "pic"
		String pictureClauseData="";
		//since the Cobol variable form might vary; some checks are necessary
		if(remainderText.contains("(")) {
			System.out.println("index of brackt"+" "+remainderText.indexOf('(')); //find index of (
		}
		
		Token t = (Token) a.pop();
		c.setPictureClause(t.sval());//picture clause

		t = (Token) a.pop();//variable name
		c.setVariableName(t.sval());

		t = (Token) a.pop();//identifier
		c.setIdentifier((int) t.nval());

		

		//		 System.out.println("pic"+" "+t);
		//		 t = (Token) a.pop();
		//		 System.out.println("variable name"+" "+t);
		//		 t = (Token) a.pop();
		//		 System.out.println("idenftifier"+" "+t);
		//pop level number
		//pop data name
		//pop picture clause
		//remainders are  

		//
		//		System.out.println(remainderText);
		//		if(remainderText.contains("(")) {
		// 			System.out.println("index of brackt"+" "+remainderText.indexOf('(')); //find index of (
		//		}
		//
		//		if(remainderText.contains("value"))
		//			System.out.println("it contains value");
		//	
		//		System.out.println("from Variable"+" "+a.elementsConsumed());
		a.setTarget(c);
	}

}
