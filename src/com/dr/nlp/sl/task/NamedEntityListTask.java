package com.dr.nlp.sl.task;

import java.util.ArrayList;

import com.dr.nlp.sl.config.Config;
import com.dr.nlp.sl.config.FeatureTwoConfig;
import com.dr.nlp.sl.datastructure.helper.NamedEntitiyList;
import com.dr.nlp.sl.executor.Executor;
import com.dr.nlp.sl.executor.strategy.FileToArrayListStrategy;

/**
 * Reads in entity list file stores it into singleton
 * Enum object as ArrayList<String> and prints out
 * the list to console
 * 
 * @author Stan Livshin
 */
public class NamedEntityListTask implements Task<Void> {

	/**
	 * run task and output named entity list
	 */
	@Override
	public void runTask() {
		//init config
		Config config = new FeatureTwoConfig();
		
		//read file into ArrayList<String> and store it in the singleton Enum object
		FileToArrayListStrategy fileToArrayListStrategy = new FileToArrayListStrategy(config);
		Executor<ArrayList<String>> fileToArrayListExecutor = new Executor<>(fileToArrayListStrategy);
		NamedEntitiyList.INSTANCE.init(fileToArrayListExecutor.execute());
		
		System.out.println("Named Entity List: ");
		for (String namedEntity : NamedEntitiyList.INSTANCE.getNamedEntityList()) {
			System.out.println(namedEntity);
		}
		System.out.println(""); //add empty line to separate outputs
	}

	@Override
	public Void getResult() {
		return null;
	}
}
