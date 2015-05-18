package com.dr.nlp.sl.executor.strategy.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dr.nlp.sl.config.Config;
import com.dr.nlp.sl.config.FeatureThreeConfig;
import com.dr.nlp.sl.executor.strategy.DirToArrayListStrategy;

public class DirToArrayListStrategyTest {

	@Test
	public void testDirToArrayListStrategy() {
		Config config = new FeatureThreeConfig();
		DirToArrayListStrategy dirToArrayListStrategy = new DirToArrayListStrategy(config);
		assertEquals(0, dirToArrayListStrategy.getResult().size());
		dirToArrayListStrategy.execute();
		assertNotEquals(0, dirToArrayListStrategy.getResult().size());
		assertEquals(10, dirToArrayListStrategy.getResult().size());
	}
}
