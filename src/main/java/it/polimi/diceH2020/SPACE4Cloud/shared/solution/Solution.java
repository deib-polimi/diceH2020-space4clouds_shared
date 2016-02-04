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
import java.util.function.Function;
import static java.util.stream.Collectors.toList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.Profile;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.TypeVM;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.TypeVMJobClassKey;
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

	@Getter
	@Setter(AccessLevel.NONE)
	private Double cost;

	@JsonIgnore
	private IEvaluator evaluator;

	private Boolean evaluated = new Boolean(false);

	public double evaluate() {
		if (!evaluated && evaluator != null) {
			this.cost = lstSolutions.parallelStream().mapToDouble(s -> evaluator.calculateCostPerJob(s)).sum();
			evaluated = true;
			return cost;
		}
		return Double.MIN_VALUE;

	}
	@JsonIgnore
	public Boolean isFeasible() {
		return lstSolutions.stream().allMatch(s -> s.getFeasible());
	}

	public SolutionPerJob getSolutionPerJob(int pos) {
		return lstSolutions.get(pos);
	}

	public void setSolutionPerJob(SolutionPerJob solPerJob) {
		solPerJob.setPos(lstSolutions.size());
		lstSolutions.add(solPerJob);
	}

	@JsonIgnore
	public List<TypeVM> getTypeVMSelected() {
		return lstSolutions.stream().map(SolutionPerJob::getTypeVMselected).collect(toList());
	}

	@JsonIgnore
	public List<TypeVMJobClassKey> getPairsTypeVMJobClass() {
		return lstSolutions.stream()
				.map(sol -> new TypeVMJobClassKey(sol.getJob().getId(), sol.getTypeVMselected().getId()))
				.collect(toList());
	}

	@JsonIgnore
	public List<Integer> getLstNumberCores() {
		return getByFunctional(sol -> sol.getNumCores());
	}

	@JsonIgnore
	public List<Profile> getLstProfiles() {
		return getByFunctional(sol -> sol.getProfile());
	}

	@JsonIgnore
	public List<Integer> getListCM() {
		return getByFunctional(sol -> sol.getProfile().getCM());
	}

	@JsonIgnore
	public List<Integer> getListCR() {
		return getByFunctional(sol -> sol.getProfile().getCR());
	}

	@JsonIgnore
	public List<Double> getListDeltabar() {
		return getByFunctional(SolutionPerJob::getDeltaBar);
	}

	@JsonIgnore
	public List<Double> getListRhobar() {
		return lstSolutions.stream().map(SolutionPerJob::getRhoBar).collect(toList());
	}

	@JsonIgnore
	public List<Double> getListSigmaBar() {
		return getByFunctional(SolutionPerJob::getSigmaBar);
	}

	@JsonIgnore
	private <R> List<R> getByFunctional(Function<SolutionPerJob, R> mapper) {
		return lstSolutions.stream().map(mapper).collect(toList());
	}

}