package com.dr.nlp.sl.task.thread;

import com.dr.nlp.sl.config.CallableConfig;
import com.dr.nlp.sl.config.Config;
import com.dr.nlp.sl.datastructure.TextFile;
import com.dr.nlp.sl.executor.Executor;
import com.dr.nlp.sl.executor.strategy.FileToStringStrategy;
import com.dr.nlp.sl.executor.strategy.StringToObjectStrategy;
import com.dr.nlp.sl.task.Task;


/**
 * This Task will be wrapped into CallableTask
 * object to be processed by many threads
 * 
 * @see TextFile
 * @see CallableConfig
 * @see CallableTask
 * @author Stan Livshin
 */
public class ParallelProcessFileTask implements Task<TextFile> {

	private Config config; //holds CallableConfig
	private TextFile textFile; //TextFile to be returned
	
	public ParallelProcessFileTask(Config config) {
		this.config = config;
	}
	
	/**
	 * Callable call() method will trigger this runTask()
	 * method
	 */
	@Override
	public void runTask() {
		
		//read file into String
		FileToStringStrategy fileToStringStrategy = new FileToStringStrategy(config);
		Executor<String> fileToStringExecutor = new Executor<>(fileToStringStrategy);
		String string = fileToStringExecutor.execute();
		
		//convert String into com.dr.nlp.sl.datastructure.* Objects
		StringToObjectStrategy stringToObjectStrategy = new StringToObjectStrategy(config, string);
		Executor<TextFile> stringToObjectExecutor = new Executor<>(stringToObjectStrategy);
		textFile = stringToObjectExecutor.execute();
	}

	/**
	 * @return TextFile
	 */
	@Override
	public TextFile getResult() {
		return textFile;
	}
}
