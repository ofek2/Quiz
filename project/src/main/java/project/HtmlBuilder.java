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
		
		Node headElement = document.importNode(parser.document.getFirstChild(), true);
		
		htmlElement.appendChild(headElement);
		
		bodyElement = document.createElement("body");
		htmlElement.appendChild(bodyElement);	
	}
	
	public void addQuestion(int qNumber,String type,int score)
	{
		Element questionElement = document.createElement("Q"+qNumber);
		questionElement.setAttribute("type", type);
		Element div = document.createElement("div");
		div.setAttribute("class", "container-fluid");
		questionElement.appendChild(div);
		Element h1 = document.createElement("h1");
		Element rowSpan = document.createElement("span");
		rowSpan.setAttribute("class", "row");
		Element titleSpan = document.createElement("span");
		titleSpan.appendChild(document.createTextNode("Question "+qNumber));
		titleSpan.setAttribute("class", "col-xs-4");
		rowSpan.appendChild(titleSpan);
		Element scoreSpan = document.createElement("span");
		scoreSpan.setAttribute("class", "col-xs-8");
		Element input = document.createElement("input");
		input.setAttribute("id", "score");
		input.setAttribute("type", "hidden");
		scoreSpan.appendChild(input);
		scoreSpan.appendChild(document.createTextNode("/"));
		Element qScore = document.createElement("qScore");
		qScore.appendChild(document.createTextNode(String.valueOf(score)));
		scoreSpan.appendChild(qScore);
		rowSpan.appendChild(scoreSpan);
		h1.appendChild(rowSpan);
	
		div.appendChild(h1);
		// add h1 element
		questions.add(questionElement);
		bodyElement.appendChild(questionElement);
	}
	public void addQuestionData(int questionNumber,String questionText,String questionImgPath)
	{
		int qNumber = questionNumber-1;
		
		Element rowSpan = document.createElement("span");
		rowSpan.setAttribute("class", "row");
		Element questionTextSpan = document.createElement("span");
		Element qText= document.createElement("qText");
		qText.appendChild(document.createTextNode(questionText));
		questionTextSpan.appendChild(qText);
		questionTextSpan.setAttribute("class", "col-xs-4");
		rowSpan.appendChild(questionTextSpan);
		
		Element speakerBtnSpan = document.createElement("span");
		speakerBtnSpan.setAttribute("class", "col-xs-8");
		Element input = document.createElement("input");
		input.setAttribute("type", "button");
		input.setAttribute("src", "g.gif");
		input.setAttribute("onClick", "Android.listen()");
		qText.appendChild(input);
		speakerBtnSpan.appendChild(input);
		rowSpan.appendChild(speakerBtnSpan);
		
		
		questions.get(qNumber).getFirstChild().appendChild(rowSpan);
		
		Element qImage= document.createElement("qImage");
		Element img = document.createElement("img");
		img.setAttribute("src", questionImgPath);
		qImage.appendChild(img);
		questions.get(qNumber).getFirstChild().appendChild(qImage);
	}
	public void addAnswersData(int questionNumber,ArrayList<String> choices)
	{
		int qNumber = questionNumber-1;
		
		Element qAnswers = document.createElement("qAnswers");
		questions.get(qNumber).getFirstChild().appendChild(qAnswers);
		
		Element form = document.createElement("form");
		qAnswers.appendChild(form);
	
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
			form.appendChild(document.createElement("br"));
			System.out.println("12");
		}
	
	}
	public void addAnswersData(int questionNumber,String type)
	{
		int qNumber = questionNumber-1;
		Element qAnswers = document.createElement("qAnswers");
		questions.get(qNumber).getFirstChild().appendChild(qAnswers);
		
		Element form = document.createElement("form");
		qAnswers.appendChild(form);
		if(type.equals("Free Text"))
		{
			Element textarea = document.createElement("textarea");
			textarea.setAttribute("rows", "4");
			textarea.setAttribute("cols", "50");
			form.appendChild(textarea);
			form.appendChild(document.createElement("br"));
		}
		if(type.equals("Free Draw"))
		{
			Element canvas = document.createElement("canvas");
			canvas.setAttribute("id", "sketchpad");
			canvas.setAttribute("height", "300");
			canvas.setAttribute("width", "500");
			form.appendChild(canvas);
			form.appendChild(document.createElement("br"));
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
		StreamResult result = new StreamResult(new File(path+".html"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		//System.out.println("File saved!");
	}

}
