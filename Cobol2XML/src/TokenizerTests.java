import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

import org.junit.Test;

import parse.tokens.Token;
import parse.tokens.Tokenizer;

public class TokenizerTests {
	Tokenizer t = new Tokenizer();
	private Token token;
	private PushbackReader reader;
	
	
	
 
	@Test
	public void testNextTokenMethod() throws IOException {
 		this.t.setString("date-written.  07-mar-1995 - mb.");
		token = t.nextToken(); 
		assertEquals(token.toString(),"date-written"); 

		this.token = this.t.nextToken(); 
		assertEquals(token.toString(),".");


		//Testing the results of adding two consecutive symbols 
		this.t.setString("date-written@.  07-mar-1995 - mb.");
		token = t.nextToken(); 
		assertEquals(token.toString(),"date-written");

		this.token = this.t.nextToken(); 
		assertEquals(token.toString(),"@");
		
		t.setString(" ");
		token = t.nextToken();
		assertEquals(token.toString(), "EOF");;
		
		
		
 	}


}
