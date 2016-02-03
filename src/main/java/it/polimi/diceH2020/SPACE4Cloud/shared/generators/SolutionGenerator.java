package it.polimi.diceH2020.SPACE4Cloud.shared.generators;

import it.polimi.diceH2020.SPACE4Cloud.shared.solution.Solution;
import it.polimi.diceH2020.SPACE4Cloud.shared.solution.SolutionPerJob;

public class SolutionGenerator {

	public static Solution build() {
		Solution sol = new Solution();
		SolutionPerJob sol1 = SolutionPerJobGenerator.build(1);
		sol.getLstSolutions().add(sol1);

		SolutionPerJob sol2 = SolutionPerJobGenerator.build(2);

		sol.getLstSolutions().add(sol2);
		sol.setEvaluated(true);
		return sol;
	}
}
