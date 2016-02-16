/**
 * Copyright 2015 deib-polimi
 * Contact: deib-polimi <michele.ciavotta@polimi.it>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
