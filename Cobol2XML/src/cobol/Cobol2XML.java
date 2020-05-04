/*
 * @(#)Cobol2XML.java	 0.1.0
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

import XMLWriter.*;
import java.io.*;
import java.util.ArrayList;

import parse.*;
import parse.tokens.*;

public class Cobol2XML {
	/**
	 * Recognise some basic constructs in a COBOL source code file.
	 * And then produce a well-formed XML file with the data identified
	 * 
	 * First command line parameter must be path to cobol source file, such as
	 * "C:\\Users\\<your user name>\\git\\cobol2xml\\base.cbl"
	 * 
	 * Or, when you know exactly where the repository is located and have the file in the right place, simply
	 * "base.cbl"
	 * 
	 * The quotation marks are required
	 */
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Cobol2XML V0.1.0");
		XMLPayload xmlp = new XMLPayload();
		ArrayList<String> functions= new ArrayList<String>();
		/* The first command line paprameter is used to get the cobol source file namee
		 * In case you are not sure if you are pointing toward the right file, print out the filename
		 * like this...
		 *
		 * System.out.println("arg[0]" + args[0]);
		 */

		/*
		 * A rather crude approach is to hard code the filename for the cobol source file, like this
		 * InputStream is = new FileInputStream("C:\\Users\\sgs442\\eclipse-workspace\\CobolParser1\\base.cbl");
		 */
		InputStream is = new FileInputStream(args[0]);
		BufferedReader r = 	new BufferedReader(new InputStreamReader(is));

		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		String newLine="";
		// Look through source code file line by line
		while (true) {
			// throws IOException
			String s = r.readLine();
			if (s == null) {
				break;
			}

			/**
			 * if read line is not a comment nor a closing element , then carry on reading following lines
			 * Uncomment to be able to read multiple lines 
			 */

			//			
			// 			if(!s.trim().contains(".") && !s.trim().contains("***") && !s.isEmpty() ) {
			// 				while((newLine = r.readLine()) != null) {
			//  					s+=" "+newLine.trim()+"\n";
			//  					System.out.println(s);
			//					if(newLine.contains("."))
			//						break;
			//				}
			//			}
//			if(!s.trim().contains(".") && !s.trim().contains("***") && !s.isEmpty() ) {
//
//				while((newLine = r.readLine()) != null) {
//					System.out.println("new Function");
//					System.out.println("from inner loop");
//					s= newLine;
//					functions.add(s);
//					System.out.println(s);
//					if(newLine.contains(".")) {
//						if(!functions.isEmpty()) {
//							for(String function : functions) {
//								t.setString(function);
//								Assembly in = new TokenAssembly(t);
//								Assembly out = p.bestMatch(in);
//								Cobol c = new Cobol();
//								c = (Cobol) out.getTarget();
//								if(c != null)
//									xmlp.addElements(c);
//							}
//						}
//						
//						if(!functions.isEmpty())
//							functions=new ArrayList<String>();
//						
//						s= " ";
//						break;
//					}
//				}
//
//			}
			// 			
			if(s !=" ") {
				t.setString(s);

				Assembly in = new TokenAssembly(t);
				Assembly out = p.bestMatch(in);
				Cobol c = new Cobol();
				c = (Cobol) out.getTarget();
				if(c != null)
					xmlp.addElements(c); 
			}


		}
		xmlp.writeFile(args[1]);
		r.close();
	}

}
