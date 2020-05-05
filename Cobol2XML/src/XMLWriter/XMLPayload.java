/*
 * @(#)XMLPayload.java	 0.1.0
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
 */package XMLWriter;

 import cobol.*;
 import javax.xml.parsers.DocumentBuilderFactory;
 import java.io.File;
 import javax.xml.parsers.DocumentBuilder;
 import javax.xml.transform.OutputKeys;
 import javax.xml.transform.Transformer;
 import javax.xml.transform.TransformerFactory;
 import javax.xml.transform.dom.DOMSource;
 import javax.xml.transform.stream.StreamResult;

 import org.w3c.dom.Attr;
 //import org.w3c.dom.Attr;
 import org.w3c.dom.Document;
 import org.w3c.dom.Element;

 public class XMLPayload {
	 Document doc;
	 Element rootElement;
	 Cobol c ;
	 public XMLPayload() {
		 try {
			 DocumentBuilderFactory dbFactory =
					 DocumentBuilderFactory.newInstance();
			 DocumentBuilder dBuilder = 
					 dbFactory.newDocumentBuilder();
			 doc = dBuilder.newDocument();

			 // root element
			 rootElement = doc.createElement("cobol");
			 doc.appendChild(rootElement);

		 } catch (Exception e) {
			 e.printStackTrace();
		 }

	 }


	 public void addElements(Cobol c) {
		 this.c = c;

		 /*
			 * Add move element
			 */
			String move = c.getMove();
			if(move != null){
				this.addMove(move);
			}else {}
		 
		 /*
		  * Add divide element
		  */
		 String divide= c.getDivide();
		 if(divide != null) {
 			 this.addDivide(divide);
		 }else {

		 }
		 
		 /*
		  * Add variable element
		  */
		 String variableName= c.getVariableName();
		 if(variableName != null) {
 			 this.addVariable(variableName, c.getIdentifier() , c.getPictureClause() , c.getVariableValue());
		 }else {

		 }
		 
		
		 /*
		  * Add comment element
		  */
		 String commentLine = c.getCommentLine();
		 if(commentLine != null ) {
			 this.addCommentLineElement(commentLine);
		 }else {

		 }
		 /*
		  * Add perform element
		  */
		 String perform = c.getPerform();
		 if(perform != null){
			 this.addPerform(perform);
		 }else {}


		 /*
		  * Add accept element
		  */
		 String accept = c.getAccept();
		 if(accept != null){
			 this.addAccept(accept);
		 }else {

		 }
		 /* 
		  * Add display element
		  */
		 String display = c.getDisplay();
		 if(display != null ) {
			 this.addDisplay(display);
		 }else {
		 }
		 
		 /* 
		  * Add display element
		  */
		 String remarks = c.getRemarks();
		 if(display != null ) {
			 this.addRemarks(remarks);
		 }else {
		 }



		 /*
		  * Add constant element
		  */

		 String constantName = c.getConstantName();
		 if(constantName != null)	{
			 this.addConstantValueElement(constantName,c.getConstantValue(),c.getLineNumber());
		 }
		 else {

		 }

		 /*
		  *  add sectionName element
		  */		
		 String sectionName = c.getSectionName();
		 if (sectionName != null) {
			 this.addSectionElement( sectionName );
			 //System.out.println("Got Section");
			 // Add contents of procedure division
		 } else {
			 //System.out.println("Section Name null");
		 }

		 /*
		  *  add divisionName element
		  */		
		 String divisionName = c.getDivisionName();
		 if (divisionName != null) {
			 this.addDivisionElement( divisionName );
			 //System.out.println("Got Section");
			 // Add contents of procedure division
		 } else {
			 //System.out.println("Division Name null");
		 }

		 /*
		  *  add ProgramID element
		  */		
		 String programIDName = c.getProgram_ID();
		 if (programIDName != null) {
			 this.addProgram_IDElement( programIDName );
			 //System.out.println("Got Section");
			 // Add contents of procedure division
		 } else {
			 //System.out.println("Section Name null");
		 }

		 /*
		  *  add DateWritten element
		  */	
		 // DayDateWritten
		 int dayDateWritten = c.getDayDateWritten();
		 if(dayDateWritten != 0) {
			 this.addDayDateWrittenElement( dayDateWritten );
		 }

		 //MonthDateWritten
		 String monthDateWritten = c.getMonthDateWritten();
		 if (monthDateWritten != null) {
			 this.addMonthDateWrittenElement( monthDateWritten );
			 //System.out.println("Got Section");
			 // Add contents of procedure division
		 } else {
			 //System.out.println("Section Name null");
		 }

		 // YearDateWritten
		 int yearDateWritten = c.getYearDateWritten();
		 if(yearDateWritten != 0) {
			 this.addYearDateWrittenElement( yearDateWritten );
		 }

	 }


	 
		void addMove(String move) {
			Element cobolName = doc.createElement("move");
			cobolName.appendChild(doc.createTextNode(move));
			rootElement.appendChild(cobolName);	
		}
	 
	 void addFunction(String functionName) {
		 
		  
		 //outer
		 Element cobolName = doc.createElement("Function");
		//Insert name of variable into XML file
		 
		 //create attribute
		 Attr attrType2 = doc.createAttribute("Name");
		 attrType2.setValue(functionName); //assign value
		 
		 //inner element 
		 Element functionName1 = doc.createElement("Function");
		 functionName1.setAttributeNode(attrType2);
		 cobolName.appendChild(functionName1);
		 rootElement.appendChild(cobolName);
	 }
	 
	 void addVariable(String variable , int identifier , String pictureClause , String variableValue) {
		 Element cobolName = doc.createElement("Variable");

		 //Insert name of variable into XML file
		 Element variableName = doc.createElement("Variable");
		 Attr attrType2 = doc.createAttribute("Name");
		 attrType2.setValue(variable);
		 variableName.setAttributeNode(attrType2);
		 cobolName.appendChild(variableName);



		 //Insert line number of constant into XML file
		 Element Identifier = doc.createElement(variable);
		 Attr attrType = doc.createAttribute("Identifier");
		 attrType.setValue(Integer.toString(identifier));
		 Identifier.setAttributeNode(attrType);
		 cobolName.appendChild(Identifier);

		 Element pictureClause1 = doc.createElement(variable);
		 Attr attrType1 = doc.createAttribute("pictureClause");
		 attrType1.setValue(pictureClause);
		 pictureClause1.setAttributeNode(attrType1);
		 cobolName.appendChild(pictureClause1);
		 
		 
		 Element value = doc.createElement(variable);
		 Attr attrType3 = doc.createAttribute("Value");
		 attrType3.setValue(variableValue);
		 value.setAttributeNode(attrType3);
		 cobolName.appendChild(value);
		 
		 rootElement.appendChild(cobolName);

	 }

	 
	 void addDivide(String divide) {
		 
		 Element cobolName = doc.createElement("Divide");
		 cobolName.appendChild(doc.createTextNode(divide));
		 rootElement.appendChild(cobolName); 
		 
	 }
	 

	 void addPerform(String perform) {
		 Element cobolName = doc.createElement("Perform");
		 cobolName.appendChild(doc.createTextNode(perform));
		 rootElement.appendChild(cobolName);	
	 }

	 void addAccept(String accept) {
		 System.out.println(accept);
		 Element cobolName = doc.createElement("Accept");
		 cobolName.appendChild(doc.createTextNode(accept));
		 rootElement.appendChild(cobolName);
	 }

	 void addRemarks(String remarks) {
		 Element cobolName = doc.createElement("Remarks");
		 cobolName.appendChild(doc.createTextNode(remarks));
		 rootElement.appendChild(cobolName); 
	 }


	 void addDisplay(String display) {
		 Element cobolName = doc.createElement("Display");
		 cobolName.appendChild(doc.createTextNode(display));
		 rootElement.appendChild(cobolName);
	 }

	 void addConstantValueElement(String constantName, double constantValue , int lineNumber) {
		 Element cobolName = doc.createElement("Constant");

		 //Insert name of constant into XML file
		 Element constID = doc.createElement("Constant");
		 Attr attrType2 = doc.createAttribute("Name");
		 attrType2.setValue(constantName);
		 constID.setAttributeNode(attrType2);
		 cobolName.appendChild(constID);

		 //Insert line number of constant into XML file
		 Element lineID = doc.createElement(constantName);
		 Attr attrType = doc.createAttribute("Line_Number");
		 attrType.setValue(Integer.toString(lineNumber));
		 lineID.setAttributeNode(attrType);
		 cobolName.appendChild(lineID);

		 Element constantID = doc.createElement(constantName);
		 Attr attrType1 = doc.createAttribute("Value");
		 attrType1.setValue(Double.toString(constantValue));
		 constantID.setAttributeNode(attrType1);
		 cobolName.appendChild(constantID);

		 rootElement.appendChild(cobolName);
	 }


	 void addProgram_IDElement(String stringElement) {
		 //  Program ID element

		 if(stringElement != null) {
			 Element cobolname = doc.createElement("Program-ID");
			 cobolname.appendChild(doc.createTextNode(stringElement));
			 rootElement.appendChild(cobolname);
		 }
	 }

	 void addCommentLineElement(String stringElement) {
		 //  Comment Line element

		 if(stringElement != null) {
			 Element cobolname = doc.createElement("commentLine");
			 cobolname.appendChild(doc.createTextNode(stringElement));
			 rootElement.appendChild(cobolname);
		 }
	 }



	 void addSectionElement(String stringElement) {
		 //  Section element

		 if(stringElement != null) {
			 Element cobolname = doc.createElement("section");
			 cobolname.appendChild(doc.createTextNode(stringElement));
			 rootElement.appendChild(cobolname);
		 }
	 }

	 void addDivisionElement(String stringElement) {
		 //  Division element
		 if(stringElement != null) {
			 Element cobolname = doc.createElement("division");
			 cobolname.appendChild(doc.createTextNode(stringElement));
			 rootElement.appendChild(cobolname);
		 }
	 }

	 void addDayDateWrittenElement(int intElement) {
		 //  DayDateWritten element

		 if(intElement != 0) {
			 Element cobolname = doc.createElement("day-date-written");
			 String s = "" + intElement;
			 cobolname.appendChild(doc.createTextNode(s));
			 rootElement.appendChild(cobolname);
		 }
	 }

	 void addMonthDateWrittenElement(String stringElement) {
		 //  MonthWritten element

		 if(stringElement != null) {
			 Element cobolname = doc.createElement("month-date-written");
			 cobolname.appendChild(doc.createTextNode(stringElement));
			 rootElement.appendChild(cobolname);
		 }
	 }

	 void addYearDateWrittenElement(int intElement) {
		 //  YearDateWritten element

		 if(intElement != 0) {
			 Element cobolname = doc.createElement("year-date-written");
			 String s = "" + intElement;
			 cobolname.appendChild(doc.createTextNode(s));
			 rootElement.appendChild(cobolname);
		 }
	 }

	 public void writeFile(String filename) {
		 try {
			 // write the content into xml file
			 // System.out.println("WriteFile Filename: " + filename);
			 TransformerFactory transformerFactory =
					 TransformerFactory.newInstance();
			 Transformer transformer =
					 transformerFactory.newTransformer();
			 transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			 transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			 DOMSource source = new DOMSource(doc);

			 StreamResult result =
					 new StreamResult(new File(filename));
			 transformer.transform(source, result);

			 // Output to console for testing
			 StreamResult consoleResult = new StreamResult(System.out);
			 transformer.transform(source, consoleResult);

		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	 }

 }
