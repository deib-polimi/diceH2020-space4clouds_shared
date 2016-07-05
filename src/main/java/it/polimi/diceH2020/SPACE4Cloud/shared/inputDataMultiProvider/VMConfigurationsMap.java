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
