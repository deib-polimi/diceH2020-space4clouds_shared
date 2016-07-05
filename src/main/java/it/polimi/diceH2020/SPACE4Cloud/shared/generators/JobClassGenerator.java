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

import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.JobClass;

public class JobClassGenerator {
	public static JobClass build(int num) {
		JobClass job = new JobClass();
		switch (num) {
		case 1:
			job.setId("1");
			job.setD(180.0);
			job.setHlow(5);
			job.setHup(10);
			job.setThink(10.0);
			job.setJob_penalty(25.0);
			break;
		case 2:
			job.setId("2");
			job.setD(150.0);
			job.setHlow(5);
			job.setHup(10);
			job.setThink(5.0);
			job.setJob_penalty(14.99);
		default:
			break;
		}
		return job;
	}
}
