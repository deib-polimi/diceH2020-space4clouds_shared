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
package it.polimi.diceH2020.SPACE4Cloud.shared.settings;

public enum Scenarios {
	PublicPeakWorkload(CloudType.Public,"Peak Workload"),
	PublicAvgWorkLoad(CloudType.Public,"Average Workload"),
	PrivateAdmissionControl(CloudType.Private,"Admission Control",Models.knapsack), 
	PrivateAdmissionControlWithPhysicalAssignment(CloudType.Private,"Admission Control",Models.binPacking), 
	PrivateNoAdmissionControl(CloudType.Private,"No Admission Control");
	
	private final String description;       
	private final CloudType cloudType;   
	private final Models model;

    private Scenarios(CloudType cloudType,String description) {
        this.description = description;
        this.cloudType = cloudType;
        this.model = Models.centralized;
    }
    
    private Scenarios(CloudType cloudType,String description, Models model) {
        this.description = description;
        this.cloudType = cloudType;
        this.model = model;
    }
    
    public String getDescription() {
       return this.description;
    }
    
    public CloudType getCloudType() {
        return this.cloudType;
    }
    
    public Models getModel(){
    	return this.model;
    }
    
}