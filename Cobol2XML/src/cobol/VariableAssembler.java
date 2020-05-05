package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class VariableAssembler extends Assembler {

	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
 
		String remainderText= a.remainder(" ");// text that comes after word "pic"
		
		//extracts value
		if(remainderText.contains("value")) {
			String value = remainderText.substring(remainderText.indexOf("value")+5);
 			value = value.trim().replaceAll("[\"\\s]",""); //removes quotation marks , dots and white spaces 
			c.setVariableValue(value);//setValue
			remainderText = remainderText.substring(0, remainderText.indexOf("value")).replaceAll("[\\s]",""); //extracts picture clause
		}
		
		remainderText = remainderText.replaceAll("[\\s]*", "");
 
		Token t = (Token) a.pop();
		c.setPictureClause(t.sval()+" "+remainderText);//picture clause

		t = (Token) a.pop();//variable name
		c.setVariableName(t.sval());

		t = (Token) a.pop();//identifier
		c.setIdentifier((int) t.nval());

		a.setTarget(c);
	}

}
