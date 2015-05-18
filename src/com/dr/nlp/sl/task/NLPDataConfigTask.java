package com.dr.nlp.sl.task;

import java.util.ArrayList;

import com.dr.nlp.sl.config.CallableConfig;
import com.dr.nlp.sl.config.Config;
import com.dr.nlp.sl.config.FeatureThreeConfig;
import com.dr.nlp.sl.executor.Executor;
import com.dr.nlp.sl.executor.strategy.DirToArrayListStrategy;

/**
 * Read file names inside nlp_data directory
 * into an ArrayList<CallableConfig>. Each
 * file name is stores as inputFile inside
 * CallableConfig object
 * 
 * @author Stan Livshin
 */
public class NLPDataConfigTask implements Task<ArrayList<CallableConfig>> {

	private ArrayList<CallableConfig> arrayList; //stores CallableConfig::inputFile

	/**
	 * Run task reading file names in a directory and storing them
	 * as CallableConfig::inputName objects
	 */
	@Override
	public void runTask() {
		Config config = new FeatureThreeConfig();
		DirToArrayListStrategy dirToArrayListStrategy = new DirToArrayListStrategy(config);
		Executor<ArrayList<CallableConfig>> dirToArrayListExecutor = new Executor<ArrayList<CallableConfig>>(dirToArrayListStrategy);
		arrayList = dirToArrayListExecutor.execute();		
	}

	/**
	 * @return ArrayList<CallableConfig>
	 */
	@Override
	public ArrayList<CallableConfig> getResult() {
		return arrayList;
	}

}
