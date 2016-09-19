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


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.polimi.diceH2020.SPACE4Cloud.shared.settings.Scenarios;
import lombok.Data;

@Data
public class InstanceDataMultiProvider {

	private String id;

	@JsonUnwrapped private JobProfilesMap mapJobProfiles;
	
	@JsonUnwrapped private ClassParametersMap mapClassParameters;
	
	@JsonUnwrapped 
	@JsonInclude(Include.NON_DEFAULT)
	private PublicCloudParametersMap mapPublicCloudParameters; //Map<String, Map<String,Map<String,List<PublicCloudParameters>>>>
	
	@JsonInclude(Include.NON_NULL)
	private PrivateCloudParameters privateCloudParameters;
	
	@JsonUnwrapped
	@JsonInclude(Include.NON_DEFAULT)
	private VMConfigurationsMap mapVMConfigurations;
	
	@JsonUnwrapped private JobMLProfilesMap mapJobMLProfiles;
	
	@JsonIgnore private String validationError;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Optional<Scenarios> scenario; 

	public InstanceDataMultiProvider(String id, JobProfilesMap mapJobProfiles, ClassParametersMap mapClassParameters, PublicCloudParametersMap mapPublicCloudParameters, PrivateCloudParameters privateCloudParameters,
			VMConfigurationsMap mapVMConfigurations, JobMLProfilesMap mapJobMLProfiles) {
		this.id = id;
		this.mapJobProfiles = mapJobProfiles;
		this.mapClassParameters = mapClassParameters;
		this.mapPublicCloudParameters = mapPublicCloudParameters;
		this.privateCloudParameters = privateCloudParameters;
		this.mapVMConfigurations = mapVMConfigurations;
		this.mapJobMLProfiles = mapJobMLProfiles;
		this.validationError = new String();
	}
	
	public InstanceDataMultiProvider() {
		this.id = new String();
		this.mapJobProfiles = new JobProfilesMap();
		this.mapClassParameters = new ClassParametersMap();
		this.mapPublicCloudParameters = new PublicCloudParametersMap();
		this.mapJobMLProfiles = new JobMLProfilesMap();
		this.validationError = new String();
		this.scenario = Optional.of(Scenarios.PublicPeakWorkload);
		//this.privateCloudParameters = new PrivateCloudParameters();
		this.mapVMConfigurations = new VMConfigurationsMap();
	}
	
	
	@JsonIgnore
	public boolean validate(){
		return (validateJobIDs() && validateProviders() && validateProviderTypeVM() && validateIdProviderTypeVM());
	}
	
	@JsonIgnore
	private boolean validateJobIDs(){
		
		if(mapJobProfiles.getMapJobProfile() == null || mapClassParameters.getMapClassParameters() == null ) {
			validationError = "Missing mapJobProfiles or mapClassParameters";
			return false;
		}
		
		if(!mapClassParameters.getJobIDs().equals(mapJobProfiles.getJobIDs())){
			validationError = "Not coinciding IDs";
			return false;//mandatory parameters
		}
		if(mapPublicCloudParameters.getMapPublicCloudParameters() != null){
			if(!mapJobProfiles.getJobIDs().equals(mapPublicCloudParameters.getJobIDs())) {
				validationError = "Not coinciding IDs for MapPublicCloudParameters";
				return false;
			}
		}
		
		if(mapJobMLProfiles.getMapJobMLProfile() != null){
			if(!mapJobProfiles.getJobIDs().equals(mapJobMLProfiles.getJobIDs())){
				validationError = "Not coinciding IDs For MLProfile";
				return false;
			}
		}
		return true;
	}
	
	@JsonIgnore
	private boolean validateProviders(){
		if(mapPublicCloudParameters.getMapPublicCloudParameters() != null){
			if(!mapJobProfiles.getProviders().equals(mapPublicCloudParameters.getProviders())){
				validationError = "Not coinciding providers";
				return false;
			}
		}
		return true;
	}
	
	@JsonIgnore
	private boolean validateProviderTypeVM(){
		if(mapPublicCloudParameters.getMapPublicCloudParameters() != null){
			if(!mapJobProfiles.getProvidersTypesMap().equals(mapPublicCloudParameters.getProvidersTypesMap())){
				validationError = "Not coinciding providers";
				return false;
			}
		}
		
		if(mapVMConfigurations.getMapVMConfigurations() != null){
			if(!mapJobProfiles.getProvidersTypesMap().equals(mapVMConfigurations.getProvidersTypesMap())){
				validationError = "Not coinciding providers mapJobProfiles or mapClassParameters";
				return false;
			}
		}
		return true;
	}
	
	@JsonIgnore
	public List<String> getProvidersList(){
		List<String> providers = new ArrayList<String>();
		providers.addAll(mapJobProfiles.getProviders());
		return providers;
	}
	
	@JsonIgnore
	public String getProvider(){
		List<String> providers = getProvidersList();
		if(providers.size() == 1) return providers.get(0);
		return null;
	}
	
	@JsonIgnore
	public Map<String, PublicCloudParameters> getMapTypeVM(String jobID, String provider) {
		return mapPublicCloudParameters.getLstTypeVM(jobID, provider);
	}
	
	@JsonIgnore
	public JobProfile getProfile(String classID, String providerName,String tVM) {
		return mapJobProfiles.getEntry(classID, providerName, tVM);
	}
	
	@JsonIgnore
	public int getNumberOfClasses(){
		return mapJobProfiles.getJobIDs().size();
	}
	
	@JsonIgnore
	private boolean validateIdProviderTypeVM(){
		if(mapPublicCloudParameters.getMapPublicCloudParameters() != null){
			if(!mapJobProfiles.getKeysTriples().equals(mapPublicCloudParameters.getKeysTriples())) return false;
		}
		return true;
	}

}