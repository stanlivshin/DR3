package com.dr.nlp.sl.config.test;

import static org.junit.Assert.assertEquals;

import java.nio.charset.Charset;

import org.junit.Test;

import com.dr.nlp.sl.config.FeatureThreeConfig;

public class FeatureThreeConfigTest {

	@Test
	public void testFeatureThreeConfig() {
		assertEquals("nlp_data", new FeatureThreeConfig().getInputFile().getName());
		assertEquals("aggregateOutput.xml", new FeatureThreeConfig().getOutputFile().getName());
		assertEquals(Charset.forName("UTF-8"), new FeatureThreeConfig().getCharset());
	}
}
