package com.dr.nlp.sl.task.thread;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.dr.nlp.sl.config.CallableConfig;
import com.dr.nlp.sl.datastructure.TextFile;
import com.dr.nlp.sl.task.Task;

/**
 * This class creates a pool of threads.
 * One thread per CallableConfig file.
 * Each thread executes a CallableTask which
 * wrappes ParallelProcessFileTask. Results
 * are stored into ArraList<Future<TextFile>>.
 * Then when threads are done with processing
 * Future objects are converted into ArrayList<TextFile>
 * and return to the caller.
 * 
 * @see CallableConfig
 * @see CallableTask
 * @see ParallelProcessFileTask
 * @see TextFile
 * @see Callable
 * @see Future
 * @author Stan Livshin
 */
public class ThreadPoolTask implements Task<ArrayList<TextFile>> {
	
	private ArrayList<CallableConfig> callableConfigList;
	private ArrayList<TextFile> textFileList;
	
	/**
	 * Constructor
	 * @param callableConfigList - list of CallableConfig
	 */
	public ThreadPoolTask(ArrayList<CallableConfig> callableConfigList) {
		this.callableConfigList = callableConfigList;
		this.textFileList = new ArrayList<>();
	}

	/**
	 * Run thread pool and collect future outputs
	 */
	@Override
	public void runTask() {
		ArrayList<Future<TextFile>> futureTextFileList = new ArrayList<>();
		
		ExecutorService executor = Executors.newFixedThreadPool(callableConfigList.size());//creates a pool size of the nlp_data dir contents
		
		for (CallableConfig config : callableConfigList) { //submit Callable threads using CallableConfig and ParallelProcessFileTask
			Future<TextFile> textFile = executor.submit(new CallableTask<TextFile>(new ParallelProcessFileTask(config)));
			futureTextFileList.add(textFile);
		}
		
		try { //fill up textFileList from Future objects
			for (Future<TextFile> futureTextFile : futureTextFileList) {
				textFileList.add(futureTextFile.get());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * @return ArrayList<TextFile>
	 */
	@Override
	public ArrayList<TextFile> getResult() {
		return textFileList;
	}

}
