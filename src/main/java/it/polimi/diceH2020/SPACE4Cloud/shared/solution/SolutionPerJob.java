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

import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.JobClass;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.Profile;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.TypeVM;
import lombok.Data;

@Data
public class SolutionPerJob {

	private String parentID;
	private Double alfa;	
	private Double beta;
	private Boolean changed;
	private Double cost;
	private Double deltaBar;
	private Double duration;
	private Boolean feasible;
	private JobClass job;
	private Integer numberContainers;
	private Integer numberUsers;
	private Integer numberVM;
	private Integer numCores;
	private Integer numOnDemandVM;
	private Integer numReservedVM;
	private Integer numSpotVM;
	private Integer pos;
	private Profile profile;
	private Double rhoBar;
	private Double sigmaBar;
	private TypeVM typeVMselected;

	//@JsonIgnore
	public SolutionPerJob setNumberVM(int numberVM) {
		if (this.numberVM == null || this.numberVM.intValue() != numberVM) {
			changed = true;
		}
		this.numberVM = numberVM;
		if (typeVMselected!=null && typeVMselected.getEta() >= 0) {
			this.numSpotVM = (int) Math.floor(typeVMselected.getEta() * this.numberVM);
			this.numReservedVM = (int) Math.min(typeVMselected.getR(), (this.numberVM - numSpotVM));
			this.numOnDemandVM = Math.max(0, this.numberVM - numSpotVM - numReservedVM);
		}
		//update num of containers
		if (this.numCores != null) {
			this.numberContainers = numberVM*numCores;
		}
		return this;
	}


}
