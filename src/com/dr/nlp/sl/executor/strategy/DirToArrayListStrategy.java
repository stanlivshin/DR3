package com.dr.nlp.sl.executor.strategy;

import java.io.File;
import java.util.ArrayList;

import com.dr.nlp.sl.config.CallableConfig;
import com.dr.nlp.sl.config.Config;

/**
 * This strategy is used to read contents of
 * a directory into an ArrayList<CallableConfig>
 * 
 * @author Stan Livshin
 */
public class DirToArrayListStrategy implements ExecutorStrategy<ArrayList<CallableConfig>> {

	private Config config; //holds input dir
	private ArrayList<CallableConfig> arrayList; //holds each file in the input dir in a separate CallableConfig::inputFile
	
	/**
	 * @param config holds input dir
	 */
	public DirToArrayListStrategy(Config config) {
		this.config = config;
		this.arrayList = new ArrayList<>();
	}
	
	/**
	 * Empty
	 */
	@Override
	public void beforeExecute() {}

	/**
	 * read in file names of a directory and store
	 * each file name into a arraylist of
	 * CallableConfig objects where file name is the
	 * inputFile variable
	 */
	@Override
	public void execute() {
		if (config.getInputFile().isDirectory()) {
			for (File file : config.getInputFile().listFiles()) {
				CallableConfig config = new CallableConfig();
				config.setInputFile(file);
				arrayList.add(config);
			}
		}
	}

	/**
	 * Empty
	 */
	@Override
	public void afterExecute() {}

	/**
	 * @return ArrayList<CallableConfig>
	 */
	@Override
	public ArrayList<CallableConfig> getResult() {
		return arrayList;
	}
}
