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

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.TypeVM;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.ClassParameters;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.JobProfile;
import lombok.Data;

@Data
public class SolutionPerJob {

	private String parentID;
	private Boolean changed; //TODO
	private Double cost = Double.MAX_VALUE;
	private Double deltaBar = 0.0;
	private Double duration;
	private Boolean feasible;
	private String id;
	private ClassParameters job;
	private Integer numberContainers;
	private Integer numberUsers;
	private Integer numberVM;
	private Double numCores;
	private Integer numOnDemandVM;
	private Integer numReservedVM;
	private Integer numSpotVM;
	private JobProfile profile;
	private Double rhoBar = 0.0;
	private Double sigmaBar = 0.0;
	private TypeVM typeVMselected;
	private Boolean error = false;
	@JsonIgnore
	private Double xi = 0.0;

	public SolutionPerJob setNumberVM(int numberVM) {
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
			if(xi > 0.0){
				this.numberContainers = (int) Math.floor((double)numberVM * xi);
			}else{
				this.numberContainers = (int) (numberVM * numCores);
			}
		}
		return this;
	}

	public void setNumberContainers(int numberContainers) {
		if (this.numberContainers == null || this.numberContainers.intValue() != numberContainers) {
			changed = true;
		}
		this.numberContainers = numberContainers;
		// update num of vm
		if (xi != null) {
			this.numberVM = (int) Math.ceil((double) this.numberContainers / xi);
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

}
