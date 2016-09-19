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
package it.polimi.diceH2020.SPACE4Cloud.shared.generatorsDataMultiProvider;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.JobProfile;

public class JobProfileGenerator {

	public static JobProfile build(int num) {
		JobProfile p = new JobProfile();
		switch (num) {
		case 1:
			p.put("Mavg",4.103);
			p.put("Mmax",9.128);
			p.put("Nm",65);
			p.put("Nr",15);
			p.put("Ravg",0.327);
			p.put("Rmax",0.592);
			p.put("Sh1max",0);
			p.put("Shtypmax",7.942);
			p.put("Shtypavg",4.831);
			break;

		case 2:
			p.put("Mavg",8.235);
			p.put("Mmax",17.541);
			p.put("Nm",65);
			p.put("Nr",5);
			p.put("Ravg",0.297);
			p.put("Rmax",0.499);
			p.put("Sh1max",0);
			p.put("Shtypmax",20.141);
			p.put("Shtypavg",14.721);
			break;
		default:
			break;
		}
		return p;
	};
}
