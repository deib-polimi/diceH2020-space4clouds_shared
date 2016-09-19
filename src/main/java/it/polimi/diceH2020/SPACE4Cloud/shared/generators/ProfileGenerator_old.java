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

import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.old.Profile_old;

public class ProfileGenerator_old {

	public static Profile_old build(int num) {
		Profile_old p = new Profile_old();
		switch (num) {
		case 1:
			p.setCm(4);
			p.setCr(4);
			p.setMavg(4.103);
			p.setMmax(9.128);
			p.setNm(65);
			p.setNr(15);
			p.setRavg(0.327);
			p.setRmax(0.592);
			p.setSh1max(0);
			p.setShtypmax(7.942);
			p.setShtypavg(4.831);
			break;

		case 2:
			p.setCm(8);
			p.setCr(8);
			p.setMavg(8.235);
			p.setMmax(17.541);
			p.setNm(65);
			p.setNr(5);
			p.setRavg(0.297);
			p.setRmax(0.499);
			p.setSh1max(0);
			p.setShtypmax(20.141);
			p.setShtypavg(14.721);
			break;
		default:
			break;
		}
		return p;
	};
}
