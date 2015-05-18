package com.dr.nlp.sl.task;

import java.util.ArrayList;

import org.w3c.dom.Document;

import com.dr.nlp.sl.config.Config;
import com.dr.nlp.sl.config.FeatureThreeConfig;
import com.dr.nlp.sl.datastructure.TextFile;
import com.dr.nlp.sl.executor.Executor;
import com.dr.nlp.sl.executor.strategy.ObjectToXMLStrategy;
import com.dr.nlp.sl.executor.strategy.XMLToFileStrategy;

/**
 * This task converts ArrayList<TextFile> into
 * aggregate XML Document and then outputs the
 * contents of the XML Document to console and
 * saves it to the disk as aggregateOutput.xml 
 * 
 * @author Stan Livshin
 */
public class XMLAggregateResultTask implements Task<Void> {
	
	private ArrayList<TextFile> textFileList; //holds TextFiles to be aggregated
	
	/**
	 * @param textFileList - list of TextFiles to be aggregated
	 */
	public XMLAggregateResultTask(ArrayList<TextFile> textFileList) {
		this.textFileList = textFileList;
	}

	/**
	 * Aggregate TextFiles into XML Document then output to console
	 * and save to disk as aggregateOutput.xml
	 */
	@Override
	public void runTask() {
		//init config
		Config config = new FeatureThreeConfig();
		
		//convert com.dr.nlp.sl.datastructure.* Objects into XML Document
		ObjectToXMLStrategy objectToXMLStrategy = new ObjectToXMLStrategy(textFileList);
		Executor<Document> objectToXMLExecutor = new Executor<>(objectToXMLStrategy);
		Document document = objectToXMLExecutor.execute();
		
		//output XML Document to the file
		XMLToFileStrategy xmlToFileStrategy = new XMLToFileStrategy(config, document);
		Executor<Void> xmlToFileExecutor = new Executor<>(xmlToFileStrategy);
		xmlToFileExecutor.execute();
	}

	/**
	 * Empty
	 */
	@Override
	public Void getResult() {
		return null;
	}
}
