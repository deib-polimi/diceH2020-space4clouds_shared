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
			p.put("mavg",4.103);
			p.put("mmax",9.128);
			p.put("mm",65);
			p.put("nr",15);
			p.put("ravg",0.327);
			p.put("rmax",0.592);
			p.put("sh1max",0);
			p.put("shtypmax",7.942);
			p.put("shtypavg",4.831);
			break;

		case 2:
			p.put("mavg",8.235);
			p.put("mmax",17.541);
			p.put("nm",65);
			p.put("nr",5);
			p.put("ravg",0.297);
			p.put("rmax",0.499);
			p.put("sh1max",0);
			p.put("shtypmax",20.141);
			p.put("shtypavg",14.721);
			break;
		default:
			break;
		}
		return p;
	};
}
