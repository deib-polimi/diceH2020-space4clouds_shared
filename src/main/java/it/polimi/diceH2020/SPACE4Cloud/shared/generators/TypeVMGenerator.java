package it.polimi.diceH2020.SPACE4Cloud.shared.generators;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.TypeVM;

public class TypeVMGenerator {
public static TypeVM build(int num){
	TypeVM t = new TypeVM();
	switch (num) {
	case 1:
		t.setId("T1");
		t.setEta(0.1);
		t.setR(30);
		break;
	case 2:
		t.setId("T2");
		t.setEta(0.3);
		t.setR(25);
		break;
	default:
		break;
	}
	return t;
}
}
