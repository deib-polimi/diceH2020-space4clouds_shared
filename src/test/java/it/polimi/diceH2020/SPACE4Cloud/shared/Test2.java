package it.polimi.diceH2020.SPACE4Cloud.shared;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test2 {

	@Test
	public void test() {
		InstanceData data = InstanceDataGenerator.build();
		System.out.println(data.toString());
		assertTrue(data.getGamma() ==240);
	}

}
