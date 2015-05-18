package com.dr.nlp.sl.config.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.charset.Charset;

import org.junit.Test;

import com.dr.nlp.sl.config.CallableConfig;

public class CallableConfigTest {

	@Test
	public void testFeatureThreeConfig() {
		CallableConfig config = new CallableConfig();
		config.setInputFile(new File("input1.txt"));
		assertEquals("input1.txt", config.getInputFile().getName());
		assertEquals(null, new CallableConfig().getOutputFile());
		assertEquals(Charset.forName("UTF-8"), new CallableConfig().getCharset());
	}
}
