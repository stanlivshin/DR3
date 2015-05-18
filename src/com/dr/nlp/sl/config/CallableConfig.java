package com.dr.nlp.sl.config;

import java.io.File;

/**
 * This class is used to store nlp_data dir
 * contents into separate input files.
 * These input files then will be used for
 * multi-thread processing. Each thread will use
 * a separate instance of CallableConfig
 * 
 * @author usbzoso
 */
public class CallableConfig extends Config {
	
	private File inputFile;

	/**
	 * @return returns input File
	 * @see File
	 */
	@Override
	public File getInputFile() {
		return inputFile;
	}
	
	/**
	 * @param inputFile sets input file
	 */
	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}

	/**
	 * Empty this will not be used
	 */
	@Override
	public File getOutputFile() {
		return null;
	}
}
