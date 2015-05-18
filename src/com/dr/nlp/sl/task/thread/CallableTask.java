package com.dr.nlp.sl.task.thread;

import java.util.concurrent.Callable;

import com.dr.nlp.sl.task.Task;


/**
 * Wrapps Task into a Callable interface
 * object to be used for multi-threading
 * 
 * @see Task
 * @see Callable
 * @author Stan Livshin
 *
 * @param <R> - generic future returnable type
 */
public class CallableTask<R> implements Callable<R> {
	
	private Task<R> task; //Task to wrap
	
	/**
	 * @param task Task to wrap into Callable interface
	 */
	public CallableTask(Task<R> task) {
		this.task = task;
	}

	/**
	 * Callable call() method
	 * 
	 * @return R genric future returnable type
	 */
	@Override
	public R call() throws Exception {
		task.runTask();
		return task.getResult();
	}
}
