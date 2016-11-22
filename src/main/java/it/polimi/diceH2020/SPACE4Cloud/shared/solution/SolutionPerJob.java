/*
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.ClassParameters;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.JobProfile;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.TypeVM;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map.Entry;

@Data
public class SolutionPerJob {
	/*
	 * Edit clone() if you add new parameters.
	 * 
	 * Maybe you should also edit toString() 
	 * (used for cache in order to avoid sending solvers already calculated simulations. 
	 * If the parameter you want to add is used by some simulator, you have too add it in the toString() method)
	 */
	private String parentID;
	private Boolean changed = false; // TODO
	private Double cost = Double.MAX_VALUE;
	private Double deltaBar = 0.0;
	private Double duration = 0.0;
	private Double throughput = 0.0;
	private Boolean feasible = false;
	private String id;
	private ClassParameters job;
	private Integer numberContainers = 0;
	private Integer numberUsers = 0;
	private Integer numberVM = 0;
	private Double numCores = 0.0;
	private Integer numOnDemandVM = 0;
	private Integer numReservedVM = 0;
	private Integer numSpotVM = 0;
	private JobProfile profile;
	private Double rhoBar = 0.0;
	private Double sigmaBar = 0.0;
	private TypeVM typeVMselected;
	private Boolean error = false;
	@JsonIgnore
	private Double xi = 0.0;

	public SolutionPerJob updateNumberVM(int newNumberVM) {
		if (numberVM == null || numberVM != newNumberVM) {
			changed = true;
		}
		numberVM = newNumberVM;
		if (typeVMselected != null && typeVMselected.getEta() >= 0) {
			numSpotVM = (int) Math.floor(typeVMselected.getEta() * numberVM);
			numReservedVM = Math.min(typeVMselected.getR(), (numberVM - numSpotVM));
			numOnDemandVM = Math.max(0, numberVM - numSpotVM - numReservedVM);
		}
		// update num of containers
		if (numCores != null) {
			if (xi > 0.0) {
				numberContainers = (int) Math.floor((double) newNumberVM * xi);
			} else {
				numberContainers = (int) (newNumberVM * numCores);
			}
		}
		return this;
	}

	public void updateNumberContainers(int newNumberContainers) {
		if (numberContainers == null || numberContainers != newNumberContainers) {
			changed = true;
		}
		numberContainers = newNumberContainers;
		// update num of vm
		if (xi != null) {
			numberVM = (int) Math.ceil((double) numberContainers / xi);
			numberContainers = (int) Math.floor((double) numberVM * xi);
		} else {
			return;
		}
		if (typeVMselected != null && typeVMselected.getEta() >= 0) {
			numSpotVM = (int) Math.floor(typeVMselected.getEta() * numberVM);
			numReservedVM = Math.min(typeVMselected.getR(), (numberVM - numSpotVM));
			numOnDemandVM = Math.max(0, numberVM - numSpotVM - numReservedVM);
		}
	}

	void updateCost() {
		double cost = deltaBar * numOnDemandVM + rhoBar * numReservedVM + sigmaBar * numSpotVM
				+ job.getPenalty() * (job.getHup() - numberUsers);
		BigDecimal c = BigDecimal.valueOf(cost).setScale(4, RoundingMode.HALF_EVEN);
		this.cost = c.doubleValue();
	}

	public boolean validate() {
		return (job.validate() && typeVMselected.validate() && profile.validate());
	}

	@JsonIgnore
	public double getXi() {
		return xi;
	}

	@JsonIgnore
	public void setXi(double xi) {
		this.xi = xi;
	}

	public SolutionPerJob clone() {
		SolutionPerJob newSpj = new SolutionPerJob();
		newSpj.setId(id);
		newSpj.setChanged(changed);
		newSpj.setCost(cost);
		newSpj.setDeltaBar(deltaBar);
		newSpj.setDuration(duration);
		newSpj.setThroughput(throughput);
		newSpj.setError(error);
		newSpj.setFeasible(feasible);
		newSpj.setJob(job);
		newSpj.setNumberContainers(numberContainers);
		newSpj.setNumberUsers(numberUsers);
		newSpj.setNumberVM(numberVM);
		newSpj.setNumCores(numCores);
		newSpj.setNumOnDemandVM(numOnDemandVM);
		newSpj.setNumReservedVM(numReservedVM);
		newSpj.setNumSpotVM(numSpotVM);
		newSpj.setParentID(parentID);
		newSpj.setProfile(profile);
		newSpj.setRhoBar(rhoBar);
		newSpj.setSigmaBar(sigmaBar);
		newSpj.setTypeVMselected(typeVMselected);
		newSpj.setXi(xi);
		return newSpj;
	}

	public String toString() {
		//Parameters RequiredBySimulators
		StringBuilder builder = new StringBuilder();
		builder.append(parentID).append("_").append(id).append(numberContainers).append(numberVM)
				.append(numberUsers).append(job.getD()).append(job.getM()).append(job.getV()).append(job.getThink());
		for (Entry<String, Double> entry : profile.getProfileMap().entrySet()) {
			builder.append(entry.getKey());
			builder.append(entry.getValue());
		}
		builder.append(job.getPenalty()).append(typeVMselected.getId());
		return builder.toString();
	}
}
