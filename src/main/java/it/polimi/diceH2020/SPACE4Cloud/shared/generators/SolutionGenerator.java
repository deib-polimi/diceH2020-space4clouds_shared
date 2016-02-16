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

import java.time.Duration;
import java.time.Instant;

import it.polimi.diceH2020.SPACE4Cloud.shared.solution.Phase;
import it.polimi.diceH2020.SPACE4Cloud.shared.solution.PhaseID;
import it.polimi.diceH2020.SPACE4Cloud.shared.solution.Solution;
import it.polimi.diceH2020.SPACE4Cloud.shared.solution.SolutionPerJob;

public class SolutionGenerator {

	public static Solution build() {
		Solution sol = new Solution();
		sol.setGamma(240);
		SolutionPerJob sol1 = SolutionPerJobGenerator.build(1);
		sol.getLstSolutions().add(sol1);

		SolutionPerJob sol2 = SolutionPerJobGenerator.build(2);

		sol.getLstSolutions().add(sol2);
		sol.setEvaluated(true);
		Instant first = Instant.now();
		Phase p1 = new Phase();
		p1.setId(PhaseID.INIT_SOLUTION);
		Instant second = Instant.now();
		p1.setDuration(Duration.between(first, second).toMillis());
		sol.getLstPhases().add(p1);
		Phase p2 = new Phase();
		p2.setId(PhaseID.OPTIMIZATION);		
		Instant third = Instant.now();
		p2.setDuration(Duration.between(first, third).toMillis());
		sol.getLstPhases().add(p2);
		return sol;
	}
}
