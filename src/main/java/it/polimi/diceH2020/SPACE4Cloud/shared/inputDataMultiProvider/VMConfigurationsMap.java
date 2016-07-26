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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class VMConfigurationsMap {
	
	private Map<String,VMConfiguration> mapVMConfigurations; 
	
	public VMConfigurationsMap(Map<String,VMConfiguration> listVMConfigurations){
		this.mapVMConfigurations = listVMConfigurations;
	}
	
	public VMConfigurationsMap() {}

	@JsonIgnore
	public boolean validate() {
		for(Map.Entry<String, VMConfiguration> typeVMs : mapVMConfigurations.entrySet()){
			if(!typeVMs.getValue().validate()) return false;
		}
		return true;
	}
	
	//TODO still useful?
	@JsonIgnore
	public Set<String> getTypeVMs(){
		return mapVMConfigurations.keySet();
	}
	

	@JsonIgnore
	public Map<String, Set<String>> getProvidersTypesMap(){
		Map<String, Set<String>> providerAndTypes = new HashMap<String, Set<String>>();
		Set<String> set = new HashSet<>();
		for (Map.Entry<String, VMConfiguration> vm : mapVMConfigurations.entrySet()) {
			set.add(vm.getKey());
		}
		providerAndTypes.put("inHouse", set);
		return providerAndTypes;
	}
	
//	@JsonAnyGetter
//	public Map<String, VMConfiguration> getMapVMConfigurations(){
//		return mapVMConfigurations;
//	}
	
}
