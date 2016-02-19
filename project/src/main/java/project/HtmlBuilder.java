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
	private Element mainDivElement;
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
		
		mainDivElement = document.createElement("div");
		mainDivElement.setAttribute("class", "container-fluid");
		bodyElement.appendChild(mainDivElement);
	}
	
	public void addQuestion(int qNumber,String type,String score)
	{
		Element questionElement = document.createElement("Q"+qNumber);
		questionElement.setAttribute("type", type);
		
		Element divPrim = document.createElement("div");
		divPrim.setAttribute("class", "panel panel-primary");
		questionElement.appendChild(divPrim);
		
		Element divHead = document.createElement("div");
		divHead.setAttribute("class", "panel-heading");
		divPrim.appendChild(divHead);
		
		Element h1 = document.createElement("h1");
		Element rowSpan = document.createElement("span");
		rowSpan.setAttribute("class", "row");
		
		Element titleSpan = document.createElement("span");
		titleSpan.appendChild(document.createTextNode("Question "+qNumber));
		titleSpan.setAttribute("class", "col-xs-8");
		rowSpan.appendChild(titleSpan);
		
		Element scoreSpan = document.createElement("span");
		scoreSpan.setAttribute("class", "col-lg-4");
		Element input = document.createElement("input");
		input.setAttribute("id", "score");
		input.setAttribute("type", "hidden");
		scoreSpan.appendChild(input);
		scoreSpan.appendChild(document.createTextNode("/"));
		
		Element qScore = document.createElement("qScore");
		qScore.appendChild(document.createTextNode(score));
		scoreSpan.appendChild(qScore);
		rowSpan.appendChild(scoreSpan);
		h1.appendChild(rowSpan);
	
		divHead.appendChild(h1);
		// add h1 element
		questions.add(questionElement);
		mainDivElement.appendChild(questionElement);
	}
	public void addQuestionData(int questionNumber,String questionText,String questionImageName)
	{
		int qNumber = questionNumber-1;
		
		Element divBody = document.createElement("div");
		divBody.setAttribute("class", "panel-body");
		Element qText= document.createElement("qText");
		Element questionTextPara = document.createElement("p");
		qText.appendChild(questionTextPara);
		questionTextPara.appendChild(document.createTextNode(questionText));
		
		divBody.appendChild(qText);
		/*
		Element speakerBtnSpan = document.createElement("span");
		speakerBtnSpan.setAttribute("class", "col-xs-8");
		Element input = document.createElement("input");
		input.setAttribute("type", "button");
		input.setAttribute("src", "g.gif");
		input.setAttribute("onClick", "Android.listen()");
		qText.appendChild(input);
		speakerBtnSpan.appendChild(input);
		rowSpan.appendChild(speakerBtnSpan);
		*/
		
		questions.get(qNumber).getFirstChild().appendChild(divBody);
		
		Element qImage= document.createElement("qImage");
		Element img = document.createElement("img");
		img.setAttribute("src", questionImageName);
		qImage.appendChild(img);
		questions.get(qNumber).getFirstChild().appendChild(qImage);
	}
	public void addAnswersData(int questionNumber,String type,ArrayList<String> choices)
	{
		int qNumber = questionNumber-1;
		
		Element qAnswers = document.createElement("qAnswers");
		questions.get(qNumber).getFirstChild().getChildNodes().item(1).appendChild(qAnswers);
		
		Element form = document.createElement("form");
		qAnswers.appendChild(form);
		
		Element divListGroup = document.createElement("div");
		divListGroup.setAttribute("class", "list-group");
		form.appendChild(divListGroup);
		for(int i=0;i<choices.size();i++)
		{
			Element divListItem = document.createElement("div");
			
			
			Element label = document.createElement("label");
			
			Element choice = document.createElement("input");
			if(type.equals("Multiple Choice"))
			{
				divListItem.setAttribute("class", "list-group-item checkbox");
				choice.setAttribute("type", "checkbox");
				choice.setAttribute("name", "a"+(i+1));
			}
			else
			{
				divListItem.setAttribute("class", "list-group-item radio");
				choice.setAttribute("type", "radio");
				choice.setAttribute("name", "a");
			}
			choice.setAttribute("onClick", "Android.mAns(this.form)");
			choice.appendChild(document.createTextNode(choices.get(i)));
			label.appendChild(choice);
			divListItem.appendChild(document.createTextNode((i+1)+"."));
			divListItem.appendChild(label);
			divListGroup.appendChild(divListItem);
			
			
		}
		
		addSpeakerBtn(qNumber);
	}
	public void addAnswersData(int questionNumber,String type)
	{
		int qNumber = questionNumber-1;
		Element qAnswers = document.createElement("qAnswers");
		questions.get(qNumber).getFirstChild().getChildNodes().item(1).appendChild(qAnswers);
		
		Element form = document.createElement("form");
		qAnswers.appendChild(form);
		if(type.equals("Free Text"))
		{
			Element textarea = document.createElement("textarea");
			textarea.setAttribute("class","form-control");
			textarea.setAttribute("rows", "4");
			textarea.setAttribute("cols", "50");
			form.appendChild(textarea);
	
		}
		if(type.equals("Free Draw"))
		{
			Element canvas = document.createElement("canvas");
			canvas.setAttribute("id", "sketchpad");
			form.appendChild(canvas);
			
		}
		addSpeakerBtn(qNumber);
	}
	public void addLecturerAnswers(int questionNumber,String type,ArrayList<String> choices,String answer)
	{
		int qNumber = questionNumber-1;
		Element answers= document.createElement("answer");
		answers.setAttribute("value",answer);
		
		questions.get(qNumber).appendChild(answers);
		
		Element divAnswer = document.createElement("div");
		divAnswer.setAttribute("class", "panel panel-default");
		answers.appendChild(divAnswer);
		
		Element divHead = document.createElement("div");
		divHead.setAttribute("class", "panel-heading");
		divAnswer.appendChild(divHead);
		
		Element h1 = document.createElement("h1");
		h1.appendChild(document.createTextNode("Question "+questionNumber));
		divHead.appendChild(h1);
		
		Element divBody = document.createElement("div");
		divBody.setAttribute("class", "panel-body");
		divAnswer.appendChild(divBody);
		
		
		
		Element form = document.createElement("form");
		divBody.appendChild(form);
		
		Element divListGroup = document.createElement("div");
		divListGroup.setAttribute("class", "list-group");
		form.appendChild(divListGroup);
		String[] splited = answer.split("\\s");
		for(int i=0;i<choices.size();i++)
		{
			Element divListItem = document.createElement("div");
			divListItem.setAttribute("class", "list-group-item ");
			//check if this is a correct answer
			for(int j=0;j<splited.length;j++)
			{
				if(splited[j].equals(String.valueOf(i+1)))
					divListItem.setAttribute("class", "list-group-item list-group-item-success");
					
			}
			divListItem.appendChild(document.createTextNode((i+1)+". "+choices.get(i)));
			divListGroup.appendChild(divListItem);
		}
		
		Element divFooter = document.createElement("div");
		divFooter.setAttribute("class", "panel-footer");
		String text="";
		if(splited.length>1)
		{
			text="The correct answers are: ";
			for(int i=0;i<splited.length-1;i++)
				text+=splited[i]+",";
			text+=splited[splited.length-1];
		}
		else
			text="The correct answer is: "+splited[0];
		divFooter.appendChild(document.createTextNode(text));
		divAnswer.appendChild(divFooter);
	}
	public void addLecturerAnswers(int questionNumber,String type,String answer)
	{
		int qNumber = questionNumber-1;
		Element answers= document.createElement("answer");
		answers.setAttribute("value",answer);
		
		questions.get(qNumber).appendChild(answers);
		
		Element divAnswer = document.createElement("div");
		divAnswer.setAttribute("class", "panel panel-default");
		answers.appendChild(divAnswer);
		
		Element divHead = document.createElement("div");
		divHead.setAttribute("class", "panel-heading");
		divAnswer.appendChild(divHead);
		
		Element h1 = document.createElement("h1");
		h1.appendChild(document.createTextNode("Question "+questionNumber));
		divHead.appendChild(h1);
		
		Element divBody = document.createElement("div");
		divBody.setAttribute("class", "panel-body");
		divAnswer.appendChild(divBody);
		
		Element form = document.createElement("form");
		divBody.appendChild(form);
		
		if(type.equals("Free Text"))
		{
			Element questionTextPara = document.createElement("p");
			questionTextPara.appendChild(document.createTextNode(answer));
			form.appendChild(questionTextPara);
	
		}
		if(type.equals("Free Draw"))
		{
			Element canvas = document.createElement("canvas");
			canvas.setAttribute("id", "sketchpad");
			form.appendChild(canvas);
	
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
	private void addSpeakerBtn(int qNumber)
	{
		Element divFooter = document.createElement("div");
		divFooter.setAttribute("class", "panel-footer");
		Element input = document.createElement("input");
		input.setAttribute("class", "btn btn-primary");
		input.setAttribute("type", "button");
		input.setAttribute("src", "g.gif");
		input.setAttribute("onClick", "Android.listen()");
		input.appendChild(document.createTextNode(" Click here to listen the question"));
		divFooter.appendChild(input);
	
		questions.get(qNumber).getFirstChild().appendChild(divFooter);
	}
}
