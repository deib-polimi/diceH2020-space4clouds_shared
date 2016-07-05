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
package it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;

@Data
public class InstanceDataMultiProvider {

	private String id;

	@JsonUnwrapped private JobProfilesMap mapJobProfiles;
	
	@JsonUnwrapped private ClassParametersMap mapClassParameters;
	
	@JsonUnwrapped private PublicCloudParametersMap mapPublicCloudParameters; //Map<String, Map<String,Map<String,List<PublicCloudParameters>>>>
	
	private PrivateCloudParameters privateCloudParameters;
	
	@JsonUnwrapped private VMConfigurationsMap mapVMConfigurations;
	
	public InstanceDataMultiProvider(String id, JobProfilesMap mapJobProfiles, ClassParametersMap mapClassParameters, PublicCloudParametersMap mapPublicCloudParameters, PrivateCloudParameters privateCloudParameters,
			VMConfigurationsMap mapVMConfigurations) {
		this.id = id;
		this.mapJobProfiles = mapJobProfiles;
		this.mapClassParameters = mapClassParameters;
		this.mapPublicCloudParameters = mapPublicCloudParameters;
		this.privateCloudParameters = privateCloudParameters;
		this.mapVMConfigurations = mapVMConfigurations;
	}
	
	public InstanceDataMultiProvider() {}
	
	@JsonIgnore
	public boolean validate(){
		return (validateJobIDs() && validateProviders() && validateProviderTypeVM() && validateIdProviderTypeVM());
	}
	
	@JsonIgnore
	private boolean validateJobIDs(){
		if(!mapClassParameters.getJobIDs().equals(mapJobProfiles.getJobIDs())) return false;//mandatory parameters
		
		if(mapPublicCloudParameters.getMapPublicCloudParameters() != null){
			if(!mapJobProfiles.getJobIDs().equals(mapPublicCloudParameters.getJobIDs())) return false;
		}
		return true;
	}
	
	@JsonIgnore
	private boolean validateProviders(){
		if(mapPublicCloudParameters.getMapPublicCloudParameters() != null){
			if(!mapJobProfiles.getProviders().equals(mapPublicCloudParameters.getProviders())) return false;
		}
		return true;
	}
	
	@JsonIgnore
	private boolean validateProviderTypeVM(){
		if(mapPublicCloudParameters.getMapPublicCloudParameters() != null){
			if(!mapJobProfiles.getProvidersTypesMap().equals(mapPublicCloudParameters.getProvidersTypesMap())) return false;
		}
		
		if(mapVMConfigurations.getMapVMConfigurations() != null){
			if(!mapJobProfiles.getProvidersTypesMap().equals(mapVMConfigurations.getProvidersTypesMap())) return false;
		}
		return true;
	}
	
	@JsonIgnore
	private boolean validateIdProviderTypeVM(){
		if(mapPublicCloudParameters.getMapPublicCloudParameters() != null){
			if(!mapJobProfiles.getKeysTriples().equals(mapPublicCloudParameters.getKeysTriples())) return false;
		}
		return true;
	}

}