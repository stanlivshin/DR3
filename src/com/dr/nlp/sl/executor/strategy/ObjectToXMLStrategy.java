package com.dr.nlp.sl.executor.strategy;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.dr.nlp.sl.datastructure.NamedEntity;
import com.dr.nlp.sl.datastructure.Paragraph;
import com.dr.nlp.sl.datastructure.Punctuation;
import com.dr.nlp.sl.datastructure.Sentence;
import com.dr.nlp.sl.datastructure.SentenceItem;
import com.dr.nlp.sl.datastructure.TextFile;
import com.dr.nlp.sl.datastructure.Word;


/**
 * converts ArrayList of 
 * com.dr.nlp.sl.datastructure.TextFile or a single
 * TextFile into XML Document
 * 
 * @see TextFile
 * @author Stan Livshin
 */
public class ObjectToXMLStrategy implements ExecutorStrategy<Document> {
	
	private ArrayList<TextFile> textFileList; //holds one or many TextFile
	private Document document; //XML Document
	
	/**
	 * Constructor
	 * @param textFile - TextFile to be converted into XML Document
	 */
	public ObjectToXMLStrategy(TextFile textFile) {
		this.textFileList = new ArrayList<>();
		textFileList.add(textFile);
	}
	
	/**
	 * Constructor
	 * @param textFileList - ArrayList<TextFile> to be converted into Aggregate XML Document
	 */
	public ObjectToXMLStrategy(ArrayList<TextFile> textFileList) {
		this.textFileList = textFileList;
	}

	/**
	 * empty
	 */
	@Override
	public void beforeExecute() {}

	/**
	 * Convert TextFile into XML Document
	 * using dom libraries: org.w3c.dom.* 
	 */
	@Override
	public void execute() {
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			this.document = documentBuilder.newDocument();

			if (textFileList.size() > 1) {
				
				// AggregateResults root elements
				Element rootElement = document.createElement("AggregateResult");
				rootElement.setAttribute("resultSize", ((Integer)textFileList.size()).toString());
				document.appendChild(rootElement);
				
				for (TextFile textFile : textFileList) {
					//add top element as TextFile tag with fileName attribute
					Element topElement = document.createElement(textFile.getXMLTagName());
					topElement.setAttribute("fileName", textFile.getTextFileName());
					rootElement.appendChild(topElement);
					
					addTextFile(textFile, topElement); //add the rest of the TextFile to topElement
				}
				
			} else { //only one TextFile in the ArrayList
				
				TextFile textFile = textFileList.get(0);
				//add root element as TextFile tag with fileName attribute
				Element rootElement = document.createElement(textFile.getXMLTagName());
				rootElement.setAttribute("fileName", textFile.getTextFileName());
				document.appendChild(rootElement);
				
				addTextFile(textFile, rootElement); //add the rest of the TextFile to topElement
			}

			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	private void addTextFile(TextFile textFile, Element topElement) {
		
		for (Paragraph paragraph : textFile.getParagraphArray()) { //add each paragraph
			Element paragraphElement = document.createElement(paragraph.getXMLTagName());
			topElement.appendChild(paragraphElement);

			for (Sentence sentence : paragraph.getSentenceArray()) { //add each sentence
				Element sentenceElement = document.createElement(sentence.getXMLTagName());
				paragraphElement.appendChild(sentenceElement);
				
				for (SentenceItem sentenceItem : sentence.getSentenceItemArray()) { //loop through SentenceItems
					if (sentenceItem instanceof Word) { // add Words
						Word word = (Word)sentenceItem;
						Element wordElement = document.createElement(word.getXMLTagName());
						wordElement.appendChild(document.createTextNode(word.getWord()));
						sentenceElement.appendChild(wordElement);
					}
					else if (sentenceItem instanceof Punctuation) { // add Punctuations
						Punctuation punctuation = (Punctuation)sentenceItem;
						Element punctuationElement = document.createElement(punctuation.getXMLTagName());
						punctuationElement.appendChild(document.createTextNode(punctuation.getPunctuation()));
						sentenceElement.appendChild(punctuationElement);
					}
					else if (sentenceItem instanceof NamedEntity) { //add NamedEntities
						NamedEntity namedEntity = (NamedEntity)sentenceItem;
						Element namedEntityElement = document.createElement(namedEntity.getXMLTagName());
						namedEntityElement.appendChild(document.createTextNode(namedEntity.getNamedEntity()));
						sentenceElement.appendChild(namedEntityElement);
					}
				}
			}
		}
	}

	/**
	 * empty
	 */
	@Override
	public void afterExecute() {}

	/**
	 * @return - returns XML Document
	 */
	@Override
	public Document getResult() {
		return document;
	}
}
