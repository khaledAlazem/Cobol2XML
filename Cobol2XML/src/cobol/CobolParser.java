/*
 * @(#)CobolParser.java	 0.1.0
 *
 * Copyright (c) 2019 Julian M. Bass
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 */
package cobol;

import parse.Alternation;
import parse.Assembler;
import parse.Repetition;
import parse.Empty;
import parse.Parser;
import parse.Sequence;
import parse.tokens.CaselessLiteral;
import parse.tokens.Literal;
import parse.tokens.Num;
import parse.tokens.QuotedString;
import parse.tokens.Symbol;
import parse.tokens.Tokenizer;
import parse.tokens.WhitespaceState;
import parse.tokens.Word;
import parse.tokens.WordState;

public class CobolParser {
	/**
	 * Return a parser that will recognize the selected COBOL source code constructs:
	 * 
	 * 
	 * This parser creates a <code>COBOL</code> object
	 * as an assembly's target.
	 *
	 * @return a parser that will recognize and build a 
	 *         <object>COBOL</object> from a source code file.
	 */
	public Parser cobol() {
		Alternation a = new Alternation();
		
		Symbol fullstop = new Symbol('.');
		fullstop.discard();
		
		a.add( ProgramID() );
		
		a.add( DivisionName() );
		
		a.add( SectionName() );
		
		a.add( DateWritten() );
		
		a.add(ConstantValue());
		
		a.add(Remarks());
		
		a.add(Display());
		
		a.add(Accept());
		
		a.add(Perform());
		
		a.add( Divide());
		
		a.add(Remainder());
		
		a.add(CommnetLine());
		
		a.add(Variable());
		
		a.add(Function());
 
		a.add(new Empty());
		
		return a;
	}
	

	
	protected Parser Function() {
//		System.out.println("from parser function");
		Sequence s = new Sequence ();
		s.add(new CaselessLiteral("decimal-to-base")); 
//		s.add(new Repetition(new Word()));
//		s.add(new Word());
//		s.add(new Symbol(".").discard());

		
//		s.add(new Symbol("-"));
//		s.add(new Word());
		
		s.setAssembler(new FunctionAssembler());
		
		return s;
	}
	protected Parser Variable() {
		Sequence s = new Sequence ();
		s.add(new Num());
		s.add(new Word());
		s.add(new CaselessLiteral("pic")); 
 		s.setAssembler(new VariableAssembler());
		return s;
	}
	
	protected Parser CommnetLine() {
		Sequence s = new Sequence ();
		s.add(new Symbol("*"));
		s.add(new Symbol("*"));
		s.add(new Symbol("*"));
		s.add(new Symbol("-"));
		s.add(new Symbol("-"));
		s.add(new Symbol("-"));
		s.add(new Word().setAssembler(new CommentLineAssembler()) );		
		
		return s;
	}
	
	
	protected Parser Remainder() {
		Sequence s = new Sequence();
   		s.add(new CaselessLiteral("remainder"));
   		s.add(new Word().setAssembler(new RemainderAssembler()));
		s.add(new Repetition(new Word()));
		return s ; 
	}

	
	protected Parser Divide() {
		Sequence s = new Sequence();
		Sequence e = new Sequence();
   		s.add(new CaselessLiteral("divide"));
//   		s.add(new CaselessLiteral("into"));
 
		s.add(new Repetition(new Word()));
   		s.add(new Word().setAssembler(new DivideAssembler()));
//   		s.add(new Word());
// 		s.add(new Word());


//		s.add(new Word()); //represents  word "giving"
//		s.add(new Word()); //represents item in which results of division will be stored
//		s.add(new Word()); //represents item in which results of division will be stored
//		s.add(new Symbol((char)10)); //move to new line
//		s.add(new CaselessLiteral("remainder")); //represents  word "remainder"
//		s.add(new Symbol(' ').discard()); //represents item in which results of division will be stored
//		s.add(new Word()); //represents  word "giving"
		
//  		a.add(s);

 		return s;
	}
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *   <Perform> perform 
	 *
	 */
	protected Parser Perform() {
		Sequence s = new Sequence();
//		s.add(new Repetition(new Word()));
		s.add(new CaselessLiteral("perform"));
//		s.add(new Repetition(new Word()));
		s.setAssembler(new PerformAssembler());
//		s.add(new Word().setAssembler(new PerformAssembler()));
		return s; 
	}
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *   <Accept> accept 
	 *
	 */
	protected Parser Accept() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("accept"));
		s.add(new Word().setAssembler(new AcceptAssembler()));
		return s; 
	}
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *   <Display> display 
	 *
	 */
	protected Parser Display() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("display"));
		s.add(new QuotedString());
		s.setAssembler(new DisplayAssembler());
		return s; 
	}
	
	/**
	 * Come back to this later
	 * @return
	 */
	protected Parser Remarks() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("remarks"));
		s.add(new Repetition(new Word().setAssembler(new RemarksAssembler())));
		s.add(new Symbol('.').discard());
//		s.setAssembler(new RemarksAssembler());
		return s; 
	}
	
	
	
	/** Return a parser that will recognizethe grammar:* *   
	 *  <line number> <contstant name> "value" <constant value>.
	 *  **/
	protected Parser ConstantValue() {
		Sequence s = new Sequence();
		s.add(new Num() );
		s.add(new Word() );
		s.add(new CaselessLiteral("value") );
		s.add(new Num() );
		s.setAssembler(new ConstantValueAssembler());
		return s;
	}
	
	
	
	/*
	 * Return a parser that will recognize the grammar:
	 * 
	 *    Program Identifier = Word;
	 *
	 */
	protected Parser ProgramID() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("program-id") );
		s.add(new Symbol('.').discard());	
		s.add(new Word().setAssembler(new Program_idAssembler()));
		return s;
	}



	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    <divisionName> division;
	 *
	 */
	protected Parser DivisionName() {
		Sequence s = new Sequence();
		s.add(new Word().setAssembler(new DivisionAssembler()));
		s.add(new CaselessLiteral("division") );
		s.add(new Symbol('.').discard());
		return s;
	}
	
	/*
	 * Return a parser that will recognize the grammar:
	 * 
	 *    Program Identifier = Word;
	 *
	 */
	protected Parser SectionName() {
		Sequence s = new Sequence();
		s.add(new Word().setAssembler(new SectionNameAssembler()));
		s.add(new CaselessLiteral("section") );
		s.add(new Symbol('.').discard());	

		return s;
	}
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    working-storage section;
	 *
	 */
	protected Parser DateWritten() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("date-written") );
		s.add(new Symbol('.').discard());
		s.add(new Num());
		s.add(new Symbol('-').discard());

		//This next Word actually contains month and year (which are extracted in DateAssembler
		s.add(new Word());
		s.add(new Symbol('-').discard());
		s.add(new Word().discard());
		s.add(new Symbol('.').discard());
		s.setAssembler(new DateAssembler());
		return s;
	}


	/**
	 * Return the primary parser for this class -- cobol().
	 *
	 * @return the primary parser for this class -- cobol()
	 */
	public static Parser start() {
		return new CobolParser().cobol();
	}

	/**
	 * Returns a tokenizer that does not allow spaces to appear inside
	 * the "words" that identify cobol's grammar.
	 *
	 * @return a tokenizer that does not allow spaces to appear inside
	 * the "words" that identify cobol grammar.
	 */
	public static Tokenizer tokenizer() {
		Tokenizer t = new Tokenizer();
		t.wordState().setWordChars(' ', ' ', false);
		return t;
	}

}
