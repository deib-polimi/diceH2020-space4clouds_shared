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
package it.polimi.diceH2020.SPACE4Cloud.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ciavotta
 *
 */
public class Solution {

	private List<SolutionPerJob> lstSolutions = new ArrayList<>();
	
	private double cost;	

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public List<SolutionPerJob> getLstSolutions() {
		return lstSolutions;
	}

	public void setLstSolutions(List<SolutionPerJob> lstSolutions) {
		this.lstSolutions = lstSolutions;
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

	public List<String> getTypeVMSelected() {
		return lstSolutions.stream().map(SolutionPerJob::getTypeVMselected).collect(Collectors.toList());
	}

	public List<Integer> getIdxVmTypeSelected() {
		return lstSolutions.stream().map(SolutionPerJob::getIdxVmTypeSelected).collect(Collectors.toList());
	}

}