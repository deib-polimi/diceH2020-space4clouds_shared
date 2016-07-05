package it.polimi.diceH2020.SPACE4Cloud.shared.generatorsDataMultiProvider;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.PrivateCloudParameters;

public class PrivateCloudParametersGenerator {
	public static PrivateCloudParameters build(){
			PrivateCloudParameters t = new PrivateCloudParameters();
			t.setM(1);
			t.setN(4);
			t.setV(7);
			t.setE(1.789);
		return t;
	}
}
