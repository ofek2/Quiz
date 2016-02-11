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
	private final String emptySpace="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp";
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
		Element div = document.createElement("div");
		questionElement.appendChild(div);
		// add h1 element
		questions.add(questionElement);
		bodyElement.appendChild(questionElement);
	}
	public void addQuestionData(int qNumber,String questionText,String questionImgPath)
	{
		Element qText= document.createElement("qText");
		qText.appendChild(document.createTextNode(questionText+emptySpace));
		qText.appendChild(document.createTextNode("<input type=\"button\" src=\"g.gif\" onClick=\" Android.listen() \"/>"));
		questions.get(qNumber).getFirstChild().appendChild(qText);
		
		Element qImage= document.createElement("qImage");
		qImage.appendChild(document.createTextNode("<img src=\""+questionImgPath+"\">"));
		questions.get(qNumber).getFirstChild().appendChild(qImage);
	}
	public void addAnswersData(int qNumber,ArrayList<String> choices)
	{
		Element qAnswers = document.createElement("qAnswers");
		questions.get(qNumber).getFirstChild().appendChild(qAnswers);
		
		Element form = document.createElement("form");
		questions.get(qNumber).getFirstChild().appendChild(form);
		
		for(int i=0;i<choices.size();i++)
		{
			Element choice = document.createElement("input");
			if(questions.get(qNumber).getAttribute("type").equals("MultipleChoice"))
			{
				choice.setAttribute("type", "checkbox");
				choice.setAttribute("name", "a"+(i+1));
			}
			else
			{
				choice.setAttribute("type", "radio");
				choice.setAttribute("name", "a");
			}
			choice.setAttribute("onClick", "Android.mAns(this.form)");
			choice.appendChild(document.createTextNode(choices.get(i)));
			form.appendChild(choice);
			form.appendChild(document.createTextNode("<br>"));
		}
		
	}
	public void addAnswersData(int qNumber,String type)
	{
		Element qAnswers = document.createElement("qAnswers");
		questions.get(qNumber).getFirstChild().appendChild(qAnswers);
		
		Element form = document.createElement("form");
		questions.get(qNumber).getFirstChild().appendChild(form);
		if(type.equals("Free text"))
		{
			Element textarea = document.createElement("textarea");
			textarea.setAttribute("rows", "4");
			textarea.setAttribute("cols", "50");
			form.appendChild(textarea);
			form.appendChild(document.createTextNode("<br>"));
		}
		if(type.equals("Free drawing"))
		{
			Element canvas = document.createElement("canvas");
			canvas.setAttribute("id", "sketchpad");
			canvas.setAttribute("height", "300");
			canvas.setAttribute("width", "500");
			form.appendChild(canvas);
			form.appendChild(document.createTextNode("<br>"));
		}
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
