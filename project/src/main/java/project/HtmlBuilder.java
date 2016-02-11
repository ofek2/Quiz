package project;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.print.attribute.standard.DocumentName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class HtmlBuilder {

	private Document document;
	private HtmlParser parser;
	private Element bodyElement;
	private ArrayList<Element> questions;
	public HtmlBuilder() throws ParserConfigurationException, FileNotFoundException{
		questions=new ArrayList<Element>();
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		document = builder.newDocument();
		InputStream in = new FileInputStream(new File("CanvasScript.html"));
		parser = new HtmlParser(in);
	}
	
	public void initiateHtml(){
		Element htmlElement = document.createElement("html");
		document.appendChild(htmlElement);
		
		Element headElement = document.createElement("head");
		headElement.appendChild(document.createTextNode(parser.document.getFirstChild().getFirstChild().getTextContent()));
		htmlElement.appendChild(headElement);
		
		bodyElement = document.createElement("body");
		htmlElement.appendChild(bodyElement);	
	}
	
	public void addQuestion(int qNumber,String type,int score)
	{
		Element questionElement = document.createElement("Q"+qNumber);
		questionElement.setAttribute("type", type);
		// add h1 element
		questions.add(questionElement);
		bodyElement.appendChild(questionElement);
	}
	public void addQuestionData(int qNumber,String questionText,String questionImgPath)
	{
		Element qText= document.createElement("qText");
		qText.appendChild(document.createTextNode(questionText));
		questions.get(qNumber).appendChild(qText);
		
		Element qImage= document.createElement("qImage");
		qImage.appendChild(document.createTextNode("<img src=\""+questionImgPath+"\">"));
		questions.get(qNumber).appendChild(qImage);
	}
	public void addAnswersData(int qNumber)
	{
		Element qAnswers = document.createElement("qAnswers");
		questions.get(qNumber).appendChild(qAnswers);
	}
	public void writeHtml(String path) throws TransformerException{
		// write the content into HTML file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(path));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		//System.out.println("File saved!");
	}

}
