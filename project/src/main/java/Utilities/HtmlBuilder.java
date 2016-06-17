package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The Class HtmlBuilder. This class controls the creation of an html quiz file.
 */
public class HtmlBuilder {

	/** The document. */
	private Document document;

	/** The parser. */
	private HtmlParser parser;

	/** The body element. */
	private Element bodyElement;

	/** The main div element. */
	private Element mainDivElement;

	/** The score script. */
	private Element scoreScript;

	/** The questions. */
	private ArrayList<Element> questions;

	/** The question text. */
	private String questionText;

	/**
	 * Instantiates a new html builder.
	 *
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public HtmlBuilder() throws ParserConfigurationException, FileNotFoundException {
		questions = new ArrayList<Element>();

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		document = builder.newDocument();

		InputStream in = getClass().getResourceAsStream("/HtmlHead.html");

		parser = new HtmlParser(in);

	}

	/**
	 * Initiate html.
	 */
	public void initiateHtml() {
		
		//------------------HTML base tags creation ---------------
		Element htmlElement = document.createElement("html");
		document.appendChild(htmlElement);

		Node headElement = document.importNode(parser.document.getFirstChild(), true);

		htmlElement.appendChild(headElement);

		bodyElement = document.createElement("body");
		htmlElement.appendChild(bodyElement);

		mainDivElement = document.createElement("div");
		mainDivElement.setAttribute("class", "container-fluid");
		bodyElement.appendChild(mainDivElement);

		scoreScript = document.createElement("script");
		scoreScript.setAttribute("id", "scoreScript");
		bodyElement.appendChild(scoreScript);
	}

	/**
	 * Adds the title info.
	 *
	 * @param quizTitle
	 *            the quiz title
	 */
	public void addTitleInfo(String quizTitle) {
		//------------------Add quiz title -------------------
		Element qTitle = document.createElement("QTitle");
		mainDivElement.appendChild(qTitle);
		Element div = document.createElement("div");
		div.setAttribute("class", "jumbotron");
		qTitle.appendChild(div);
		Element header = document.createElement("h1");
		header.setAttribute("id", "title");
		header.setAttribute("align", "center");
		header.appendChild(document.createTextNode(quizTitle));
		div.appendChild(header);
		//------------------Add final score label to the title section -------------------
		Element finalScore = document.createElement("label");
		finalScore.appendChild(document.createTextNode("Final Score:"));
		Element scoreText = document.createElement("u");
		scoreText.setAttribute("id", "finalScore");
		scoreText.appendChild(document.createTextNode("   "));
		finalScore.appendChild(scoreText);
		div.appendChild(finalScore);
	}

	/**
	 * Adds a new question to the quiz.
	 * a question is built in the following structure:
	 * <Q1>
	 * 	<div>(Primary section)
	 * 
	 * 		<div>(Question Head)
	 * 			includes the question number and the score(Score textField,total score for the question)
	 * 		</div>
	 * 
	 * 		<div>(Question Body)
	 * 			<qText>
	 * 				the question text
	 * 			</qText>		
	 * 			<qImage>
	 * 				holds a picture for the question(optional)
	 * 			</qImage>
	 * 			<qAnswer>
	 * 				answer part, will be updated when a student answer the question.
	 * 			</qAnswer>
	 * 		</div>
	 * 
	 * 		<div>(Question Footer)
	 * 			holds "Listen" button for listening questions(optional)
	 * 		</div>
	 * 
	 * 	</div>
	 * </Q1>
	 *
	 * @param qNumber
	 *            the question number
	 * @param type
	 *            the question type
	 * @param score
	 *            the question total score
	 */
	public void addQuestion(int qNumber, String type, String score) {
		
		//------------------Add new question to the quiz -------------------
		Element questionElement = document.createElement("Q" + qNumber);
		questionElement.setAttribute("type", type);

		Element divPrim = document.createElement("div");
		divPrim.setAttribute("class", "panel panel-primary");
		questionElement.appendChild(divPrim);

		Element divHead = document.createElement("div");
		divHead.setAttribute("class", "panel-heading");
		divPrim.appendChild(divHead);

		Element h1 = document.createElement("h1");
		divHead.appendChild(h1);

		Element div = document.createElement("div");
		div.setAttribute("align", "right");
		
		//------------------Add the score input box to the question-------------------
		Element input = document.createElement("input");
		input.setAttribute("id", "score");
		input.setAttribute("name", "ScoreQ" + qNumber);
		input.setAttribute("type", "hidden");
		input.setAttribute("maxlength", "2");
		input.setAttribute("oninput", "Desktop.receiveInput(this.value," + qNumber + ");updateFinalScore();");
		input.appendChild(document.createTextNode(" "));
		div.appendChild(input);
		div.appendChild(document.createTextNode("/"));
		Element qScore = document.createElement("qScore");
		qScore.setAttribute("id", "qScoreQ" + qNumber);
		qScore.appendChild(document.createTextNode(score));
		div.appendChild(qScore);
		h1.appendChild(div);
		//------------------Add question number-------------------
		h1.appendChild(document.createTextNode("Question " + qNumber));

		questions.add(questionElement);
		mainDivElement.appendChild(questionElement);
	}

	/**
	 * Adds the question data(the question text and the question image(if there is one).
	 * 
	 *
	 * @param questionNumber
	 *            the question number
	 * @param questionText
	 *            the question text
	 * @param questionImageName
	 *            the question image name
	 */
	public void addQuestionData(int questionNumber, String questionText, String questionImageName) {
		int qNumber = questionNumber - 1;
		this.questionText = questionText;
		Element divBody = document.createElement("div");
		divBody.setAttribute("class", "panel-body");
		//------------------Add question text-------------------
		Element qText = document.createElement("qText");
		Element questionTextPara = document.createElement("pre");
		qText.appendChild(questionTextPara);
		if(questionText.length()>0)
		questionTextPara.appendChild(document.createTextNode(questionText));
		else
			questionTextPara.appendChild(document.createTextNode(" "));
		divBody.appendChild(qText);
		if (!questionImageName.equals("")) {
			//------------------Add question image-------------------
			Element qImage = document.createElement("qImage");
			Element img = document.createElement("img");
			img.setAttribute("src", questionImageName);
			qImage.appendChild(img);
			divBody.appendChild(qImage);
		}
		questions.get(qNumber).getFirstChild().appendChild(divBody);

	}

	/**
	 * Adds the answers data(adds the answer section to the question depending on the question's type).
	 * This function is meant for choices questions(Single or multiple)
	 * @param questionNumber
	 *            the question number
	 * @param type
	 *            the type
	 * @param choices
	 *            the choices
	 * @param enableListening
	 *            the enable listening
	 */
	public void addAnswersData(int questionNumber, String type, ArrayList<String> choices, boolean enableListening) {
		int qNumber = questionNumber - 1;
		String choicesText = "";
		if (type.equals("Single Choice"))
			questions.get(qNumber).setAttribute("type", "Single Choice");
		Element qAnswers = document.createElement("qAnswers");
		qAnswers.appendChild(document.createTextNode("Answer:"));
		questions.get(qNumber).getFirstChild().getChildNodes().item(1).appendChild(qAnswers);

		Element form = document.createElement("form");
		form.setAttribute("name", "Q" + questionNumber);
		qAnswers.appendChild(form);

		Element divListGroup = document.createElement("div");
		divListGroup.setAttribute("class", "list-group");
		form.appendChild(divListGroup);
		for (int i = 0; i < choices.size(); i++) {
			Element divListItem = document.createElement("div");

			Element label = document.createElement("label");

			Element choice = document.createElement("input");
			// Adding the next possible answer depending on the choices type
			if (type.equals("Multiple Choice")) {
				divListItem.setAttribute("class", "list-group-item checkbox");
				choice.setAttribute("type", "checkbox");
				choice.setAttribute("name", "a" + (i + 1));
			} else {
				divListItem.setAttribute("class", "list-group-item radio");
				choice.setAttribute("type", "radio");
				choice.setAttribute("name", "a");
			}
			choice.setAttribute("onClick", "sendAnswer(this.form,'" + type + "')");
			choice.appendChild(document.createTextNode(choices.get(i)));
			label.appendChild(choice);
			divListItem.appendChild(document.createTextNode((i + 1) + "."));
			divListItem.appendChild(label);
			divListGroup.appendChild(divListItem);

			// Adding the option to the listening
			if (enableListening) {
				String currentOption = (i + 1) + ". " + choices.get(i);
				currentOption = currentOption.trim();
				if (!choices.get(i).endsWith("."))
					currentOption = currentOption + ".";
				choicesText += currentOption + " ";
			}

		}
		if (enableListening) {
			String[] fixedQuestionText = questionText.split("\\n");
			String questionTextToSpeak = "";
			for (int i = 0; i < fixedQuestionText.length; i++)
				questionTextToSpeak += fixedQuestionText[i];
			String textToSpeechText = "Question number " + questionNumber + ", " + questionTextToSpeak
					+ " Possible answer choices are:" + choicesText;
			addSpeakerBtn(qNumber, textToSpeechText);
		}
	}

	/**
	 * Adds the answers data(adds the answer section to the question depending on the question's type).
	 * This function is meant for questions with the type of free text or free draw.
	 * @param questionNumber
	 *            the question number
	 * @param type
	 *            the type
	 * @param enableListening
	 *            the enable listening
	 */
	public void addAnswersData(int questionNumber, String type, boolean enableListening) {
		int qNumber = questionNumber - 1;
		Element qAnswers = document.createElement("qAnswers");
		qAnswers.appendChild(document.createTextNode("Answer:"));
		questions.get(qNumber).getFirstChild().getChildNodes().item(1).appendChild(qAnswers);

		Element form = document.createElement("form");
		form.setAttribute("name", "Q" + questionNumber);
		form.setAttribute("type", type);
		qAnswers.appendChild(form);
		// Adds a text field for entering an answer for an open question.
		if (type.equals("Free Text")) {
			Element textarea = document.createElement("textarea");
			textarea.setAttribute("class", "form-control");
			textarea.setAttribute("rows", "4");
			textarea.setAttribute("cols", "50");
			textarea.setAttribute("oninput", "sendAnswer(this.form,'" + type + "')");
			textarea.appendChild(document.createTextNode(" "));
			form.appendChild(textarea);
		}
		// Adds a button for free drawing an answer to the question.
		if (type.equals("Free Draw")) {
			Element studentDrawing = document.createElement("studentdrawingQ" + questionNumber);
			studentDrawing.appendChild(document.createTextNode(" "));
			form.appendChild(studentDrawing);
			Element input = document.createElement("input");
			input.setAttribute("class", "btn btn-primary");
			input.setAttribute("type", "button");
			input.setAttribute("onclick", "openDrawingBoard(this.form)");
			input.setAttribute("value", "Draw An Answer");
			input.appendChild(document.createTextNode(" "));
			form.appendChild(input);

		}
		if (enableListening) {
			String textToSpeechText = "Question number " + questionNumber + ", " + questionText;
			addSpeakerBtn(qNumber, textToSpeechText);
		}
	}

	/**
	 * Adds the lecturer answers(will be shown only in the graded quiz file and when previewing a quiz in the creation phase).
	 * This function is meant for choices questions(Single or multiple)
	 * @param questionNumber
	 *            the question number
	 * @param type
	 *            the question type
	 * @param choices
	 *            the choices
	 * @param answer
	 *            the answer
	 */
	public void addLecturerAnswers(int questionNumber, String type, ArrayList<String> choices, String answer) {
		int qNumber = questionNumber - 1;
		Element answers = document.createElement("answer");
		answers.setAttribute("value", answer);

		questions.get(qNumber).appendChild(answers);

		scoreScript.appendChild(document.createTextNode(
				"myFunction('Q" + questionNumber + "',document.getElementsByName(\"ScoreQ" + questionNumber + "\","+questionNumber+"));"));

		Element divAnswer = document.createElement("div");
		divAnswer.setAttribute("class", "panel panel-success");
		answers.appendChild(divAnswer);

		Element divHead = document.createElement("div");
		divHead.setAttribute("class", "panel-heading");
		divAnswer.appendChild(divHead);

		Element h1 = document.createElement("h1");
		h1.appendChild(document.createTextNode("Question " + questionNumber + " - Correct Answer"));
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
		for (int i = 0; i < choices.size(); i++) {
			Element divListItem = document.createElement("div");
			divListItem.setAttribute("class", "list-group-item ");
			divListItem.setAttribute("name", "answerInput");
			// check if this is a correct answer
			for (int j = 0; j < splited.length; j++) {
				if (splited[j].equals(String.valueOf(i + 1))) {
					divListItem.setAttribute("class", "list-group-item list-group-item-success");
				}

			}
			divListItem.appendChild(document.createTextNode((i + 1) + ". " + choices.get(i)));
			divListGroup.appendChild(divListItem);
		}

		Element divFooter = document.createElement("div");
		divFooter.setAttribute("class", "panel-footer");
		String text = "";
		if (splited.length > 1) {
			text = "The correct answers are: ";
			for (int i = 0; i < splited.length - 1; i++)
				text += splited[i] + ",";
			text += splited[splited.length - 1];
		} else
			text = "The correct answer is: " + splited[0];
		divFooter.appendChild(document.createTextNode(text));
		divAnswer.appendChild(divFooter);
	}

	/**
	 * Adds the lecturer answers(will be shown only in the graded quiz file and when previewing a quiz in the creation phase).
	 * This function is meant for questions with the type of free text or free draw.
	 * @param questionNumber
	 *            the question number
	 * @param type
	 *            the question type
	 * @param answer
	 *            the answer
	 */
	public void addLecturerAnswers(int questionNumber, String type, String answer) {
		int qNumber = questionNumber - 1;
		Element answers = document.createElement("answer");
		answers.setAttribute("value", answer);

		questions.get(qNumber).appendChild(answers);

		Element divAnswer = document.createElement("div");
		divAnswer.setAttribute("class", "panel panel-success");
		answers.appendChild(divAnswer);

		Element divHead = document.createElement("div");
		divHead.setAttribute("class", "panel-heading");
		divAnswer.appendChild(divHead);

		Element h1 = document.createElement("h1");
		h1.appendChild(document.createTextNode("Question " + questionNumber + " - Correct Answer"));
		divHead.appendChild(h1);

		Element divBody = document.createElement("div");
		divBody.setAttribute("class", "panel-body");
		divAnswer.appendChild(divBody);

		Element form = document.createElement("form");
		divBody.appendChild(form);

		if (type.equals("Free Text")) {
			Element questionTextPara = document.createElement("pre");
			if(answer.length()>0)
				questionTextPara.appendChild(document.createTextNode(answer));
			else
				questionTextPara.appendChild(document.createTextNode(" "));
			form.appendChild(questionTextPara);

		}
		if (type.equals("Free Draw")) {
			if (!answer.equals("")) {
				Element aImage = document.createElement("aImage");
				Element img = document.createElement("img");
				img.setAttribute("src", answer);
				aImage.appendChild(img);
				form.appendChild(aImage);
			}
		}
	}

	/**
	 * Removes the lecturer answers.
	 *
	 * @param questionsToHide
	 *            the questions to hide
	 */
	public void removeLecturerAnswers(ArrayList<Boolean> questionsToHide) {
		hideSelectedQuestions(questionsToHide);
		NodeList answers = document.getElementsByTagName("answer");

		for (int i = answers.getLength() - 1; i >= 0; i--) {

			answers.item(i).getParentNode().removeChild(answers.item(i));
		}
		// delete script for auto scoring
		scoreScript.getParentNode().removeChild(scoreScript);
	}

	/**
	 * Hide selected questions.
	 *
	 * @param questionsToHide
	 *            the questions to hide
	 */
	private void hideSelectedQuestions(ArrayList<Boolean> questionsToHide) {
		NodeList qTexts = document.getElementsByTagName("qText");
		for (int i = 0; i < qTexts.getLength(); i++) {
			Element questionTextPara = (Element) ((Element) qTexts.item(i)).getElementsByTagName("pre").item(0);
			if (questionsToHide.get(i)) {
				questionTextPara.setAttribute("hidden", "hidden");
				Element instructionText = document.createElement("p");
				instructionText.appendChild(
						document.createTextNode("Click on the \"Listen\" button in order to listen to the question."));
				qTexts.item(i).appendChild(instructionText);
			}

		}
	}

	/**
	 * Prepare quiz for grading.
	 *
	 * @param studentQuizPath
	 *            the student quiz path
	 * @param originalQuizFormPath
	 *            the original quiz form path
	 */
	public void prepareQuizForGrading(String studentQuizPath, String originalQuizFormPath) {

		InputStream in, in2;
		try {
			in = new FileInputStream(new File(originalQuizFormPath));
			HtmlParser original = new HtmlParser(in);
			in2 = new FileInputStream(new File(studentQuizPath));
			HtmlParser prepared = new HtmlParser(in2);

			// ----------Unhide hidden questions----------//
			NodeList qTexts = prepared.document.getElementsByTagName("qText");
			for (int i = 0; i < qTexts.getLength(); i++) {
				Element questionTextPara = (Element) ((Element) qTexts.item(i)).getElementsByTagName("pre").item(0);
				if (questionTextPara != null)
					questionTextPara.removeAttribute("hidden");
			}
			// ----------Copy Lecturer Answers To The Quiz and Auto check multiple choice questions----------//

			NodeList answerElements = original.document.getElementsByTagName("answer");
			for (int i = 0; i < answerElements.getLength(); i++) {
				NodeList questionElement = prepared.document.getElementsByTagName("Q" + (i + 1));

				Node question = questionElement.item(0);
				Node answer = prepared.document.importNode(answerElements.item(i), true);
				question.appendChild(answer);
			}
			NodeList scripts = original.document.getElementsByTagName("script");
			Node script;
			for (int i = 0; i < scripts.getLength(); i++)
				if (((Element) scripts.item(i)).getAttribute("id").equals("scoreScript")) {
					script = prepared.document.importNode(scripts.item(i), true);
					prepared.document.getElementsByTagName("body").item(0).appendChild(script);
				}

			// ----------Disable All Inputs, Delete drawing buttons and Reveal Score Text Boxes--------------------------------//
			NodeList inputs = prepared.document.getElementsByTagName("input");
			for (int i = 0; i < inputs.getLength(); i++) {
				Element input = (Element) inputs.item(i);
					if (!input.getAttribute("id").equals("score")) {
						input.removeAttribute("onClick");
						input.setAttribute("disabled", "disabled");
					} else {
						input.setAttribute("type", "text");
					}
				
			}
			for(int i=0;i<inputs.getLength();i++)
			{
				Element input = (Element) inputs.item(i);
				if(input.getAttribute("type").equals("button"))
					input.getParentNode().removeChild(input);
			}
			NodeList textAreas = prepared.document.getElementsByTagName("textarea");
			for (int i = 0; i < textAreas.getLength(); i++) {
				Element textArea = (Element) textAreas.item(i);
				textArea.setAttribute("disabled", "disabled");
			}
		
			prepared.writeHtml(studentQuizPath);

		} catch (FileNotFoundException | TransformerException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Write html file to a specific path.
	 *
	 * @param path
	 *            the path
	 * @throws TransformerException
	 *             the transformer exception
	 */
	public void writeHtml(String path) throws TransformerException {
		// write the content into HTML file

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(path));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);
	}

	/**
	 * Adds the speaker button.
	 *
	 * @param qNumber
	 *            the q number
	 * @param textToSpeechText
	 *            the text to speech text
	 */
	private void addSpeakerBtn(int qNumber, String textToSpeechText) {
		Element divFooter = document.createElement("div");
		divFooter.setAttribute("class", "panel-footer");
		Element input = document.createElement("input");
		input.setAttribute("class", "btn btn-primary");
		input.setAttribute("type", "button");
		input.setAttribute("value", "Listen");
		input.setAttribute("texttospeech", textToSpeechText);
		input.setAttribute("onClick", "listen(this)");
		divFooter.appendChild(input);
		questions.get(qNumber).getFirstChild().appendChild(divFooter);
	}

	/**
	 * Gets the document.
	 *
	 * @return the document
	 */
	public Document getDocument() {
		return document;
	}
}
