package com.dr.nlp.sl.config;

import java.io.File;

/**
 * FeatureThreeConfig extends abstract Config class
 * overriding getInputFile() and getOutputFile()
 * methods.
 * 
 * This config class is used for feature three of the
 * coding challenge. 
 * 
 * @see Config
 * @author Stan Livshin
 */
public class FeatureThreeConfig extends Config {

	/**
	 * @return returns input File/Dir
	 * @see File
	 */
	@Override
	public File getInputFile() {
		return new File("nlp_data");
	}

	/**
	 * @return returns output File
	 * @see File
	 */
	@Override
	public File getOutputFile() {
		return new File("aggregateOutput.xml");
	}

}
