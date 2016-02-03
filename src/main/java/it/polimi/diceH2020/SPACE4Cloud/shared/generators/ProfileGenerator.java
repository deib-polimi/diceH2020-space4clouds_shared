package it.polimi.diceH2020.SPACE4Cloud.shared.generators;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.Profile;

public class ProfileGenerator {

	public static Profile build(int num) {
		Profile p = new Profile();
		switch (num) {
		case 1:
			p.setCM(4);
			p.setCR(4);
			p.setMavg(4.103);
			p.setMmax(9.128);
			p.setNM(65);
			p.setNR(15);
			p.setRavg(0.327);
			p.setRmax(0.592);
			p.setSH1max(0);
			p.setSHtypmax(7.942);
			p.setSHtypavg(4.831);
			break;

		case 2:
			p.setCM(8);
			p.setCR(8);
			p.setMavg(8.235);
			p.setMmax(17.541);
			p.setNM(65);
			p.setNR(5);
			p.setRavg(0.297);
			p.setRmax(0.499);
			p.setSH1max(0);
			p.setSHtypmax(20.141);
			p.setSHtypavg(14.721);
			break;
		default:
			break;
		}
		return p;
	};
}
