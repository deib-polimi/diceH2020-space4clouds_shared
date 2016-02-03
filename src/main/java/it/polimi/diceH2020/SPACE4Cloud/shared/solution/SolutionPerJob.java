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

	private double alfa;	
	private double beta;
	private boolean changed;
	private double cost;
	private double deltaBar;
	private double duration;
	private boolean feasible;
	private JobClass job;
	private int numberContainers;
	private int numberUsers;
	private int numberVM;
	private int numCores;
	private int numOnDemandVM;
	private int numReservedVM;
	private int numSpotVM;
	private int pos;
	private Profile profile;
	private double rhoBar;
	private double sigmaBar;
	private TypeVM typeVMselected;


	public void setNumberVM(int numberVM) {
		this.numberVM = numberVM;
		if (typeVMselected.getEta() >= 0) {
			this.numSpotVM = (int) Math.floor(typeVMselected.getEta() * this.numberVM);
			this.numReservedVM = (int) Math.min(typeVMselected.getR(), (this.numberVM - numSpotVM));
			this.numOnDemandVM = Math.max(0, this.numberVM - numSpotVM - numReservedVM);
		}
	}


}
