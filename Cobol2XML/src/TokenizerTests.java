import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
		this.t.setString("date-hiiden.  07-mar-1995 - mb.");
		token = t.nextToken(); 
		assertEquals(token.toString(),"date-hiiden"); 

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

	@Test
	public void testSetStringMethod() throws IOException{
		
		Tokenizer tt = new Tokenizer();
 		String s = "date-written.";
 		tt.setString(s);
		while(true) {
			Token tn =  tt.nextToken();
			if(tn.equals(Token.EOF))
				break;
			System.out.println(tn);
		}
	}
	
	
	/**
	 * This class was used to mock the tokenizer class so 
	 * we can override any method that has dependency on other classes to run.
	 * This class has helped a lot to break dependencies.
	 */

//	private class TestableTokenizer extends Tokenizer {
//		private FileWriter fw;
//		private FileReader fr;
// 		public TestableTokenizer() {}

	
//		@Override
//		public void setString(String s) {
//			System.out.println("from setString single");
//			setString(s, DEFAULT_SYMBOL_MAX);
//		}
		
//		@Override
//		public void setString(String s, int symbolMax) {
//
//			try {
////				System.out.println("from testable setString");
//				setReader(getReaderTestable(s, symbolMax));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

//		@Override
//		public PushbackReader getReaderTestable(String s )  {
//			System.out.println("from testable setString");
//			fw = new FileWriter("test.txt"); //creates file to write string to
//			fw.write(s); // write to text file
//			fw.close(); // close buffer
//			
//			fr = new FileReader("test.txt"); //creates file reader to read text back from
//
//			PushbackReader pr = new PushbackReader(fr , symbolMax);	//creates PushbackReader and assigns file to let reader read from
//			return pr;
//		}

//	}



}
