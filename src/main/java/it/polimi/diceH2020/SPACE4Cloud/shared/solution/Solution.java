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
package it.polimi.diceH2020.SPACE4Cloud.shared.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.TypeVM;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ciavotta
 *
 */
@Data
public class Solution {

	private List<SolutionPerJob> lstSolutions = new ArrayList<>();

	@Getter @Setter(AccessLevel.NONE)  private double cost;

	private IEvaluator evaluator;

	private boolean evaluated;

	public double evaluate() {
		if (!evaluated && evaluator != null) {
			this.cost = lstSolutions
					.parallelStream()
					.mapToDouble(s -> evaluator.calculateCostPerJob(s))
					.sum();
			evaluated = true;
			return cost;
		}
		return Double.MIN_VALUE;

	}


	public boolean isFeasible() {
		return lstSolutions.stream().allMatch(s -> s.isFeasible());
	}

	public SolutionPerJob getSolutionPerJob(int pos) {
		return lstSolutions.get(pos);
	}

	public void setSolutionPerJob(SolutionPerJob solPerJob) {
		solPerJob.setPos(lstSolutions.size());
		lstSolutions.add(solPerJob);
	}

	public List<TypeVM> getTypeVMSelected() {
		return lstSolutions.stream().map(SolutionPerJob::getTypeVMselected).collect(Collectors.toList());
	}


}