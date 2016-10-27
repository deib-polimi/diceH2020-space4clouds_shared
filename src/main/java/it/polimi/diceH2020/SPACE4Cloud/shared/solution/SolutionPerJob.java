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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.ClassParameters;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.JobProfile;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.TypeVM;
import lombok.Data;

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

	public SolutionPerJob updateNumberVM(int numberVM) {
		if (this.numberVM == null || this.numberVM.intValue() != numberVM) {
			changed = true;
		}
		this.numberVM = numberVM;
		if (typeVMselected != null && typeVMselected.getEta() >= 0) {
			this.numSpotVM = (int) Math.floor(typeVMselected.getEta() * this.numberVM);
			this.numReservedVM = (int) Math.min(typeVMselected.getR(), (this.numberVM - numSpotVM));
			this.numOnDemandVM = Math.max(0, this.numberVM - numSpotVM - numReservedVM);
		}
		// update num of containers
		if (this.numCores != null) {
			if (xi > 0.0) {
				this.numberContainers = (int) Math.floor((double) numberVM * xi);
			} else {
				this.numberContainers = (int) (numberVM * numCores);
			}
		}
		return this;
	}
	
	public void updateNumberContainers(int numberContainers) {
		if (this.numberContainers == null || this.numberContainers.intValue() != numberContainers) {
			changed = true;
		}
		this.numberContainers = numberContainers;
		// update num of vm
		if (xi != null) {
			this.numberVM = (int) Math.ceil((double) this.numberContainers / xi);
			this.numberContainers = (int) Math.floor((double) numberVM * xi);
		} else {
			return;
		}
		if (typeVMselected != null && typeVMselected.getEta() >= 0) {
			this.numSpotVM = (int) Math.floor(typeVMselected.getEta() * this.numberVM);
			this.numReservedVM = (int) Math.min(typeVMselected.getR(), (this.numberVM - numSpotVM));
			this.numOnDemandVM = Math.max(0, this.numberVM - numSpotVM - numReservedVM);
		}
	}

	public void setCost() {
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
		newSpj.setId(this.getId());
		newSpj.setChanged(this.getChanged());
		newSpj.setCost(this.getCost());
		newSpj.setDeltaBar(this.getDeltaBar());
		newSpj.setDuration(this.getDuration());
		newSpj.setError(this.getError());
		newSpj.setFeasible(this.getFeasible());
		newSpj.setJob(this.getJob());
		newSpj.setNumberContainers(this.getNumberContainers());
		newSpj.setNumberUsers(this.getNumberUsers());
		newSpj.setNumberVM(this.getNumberVM());
		newSpj.setNumCores(this.getNumCores());
		newSpj.setNumOnDemandVM(this.getNumOnDemandVM());
		newSpj.setNumReservedVM(this.getNumReservedVM());
		newSpj.setNumSpotVM(this.getNumSpotVM());
		newSpj.setParentID(this.getParentID());
		newSpj.setProfile(this.getProfile());
		newSpj.setRhoBar(this.getRhoBar());
		newSpj.setSigmaBar(this.getSigmaBar());
		newSpj.setTypeVMselected(this.getTypeVMselected());
		newSpj.setXi(this.getXi());
		return newSpj;
	}
	
	public String toString(){
		String profileInfo= new String(); //Parameters RequiredBySimulators
		for(Entry<String, Double> entry : profile.getProfileMap().entrySet()){
			profileInfo += entry.getKey()+entry.getValue();
		}
		return parentID+"_"+id+numberContainers+numberVM+numberUsers+job.getD()+job.getM()+job.getV()+job.getThink()+profileInfo+job.getPenalty()+typeVMselected.getId();
	}
}