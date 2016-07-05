package it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class PublicCloudParametersMap {
	private Map<String, Map<String,Map<String,PublicCloudParameters>>> mapPublicCloudParameters; //jobId->provider->typeVM
	
	public PublicCloudParametersMap(Map<String, Map<String,Map<String,PublicCloudParameters>>> mapPublicCloudParameters){
		this.mapPublicCloudParameters = mapPublicCloudParameters;
	}
	
	public int getNumberTypeVM(String jobID, String provider) {
		return mapPublicCloudParameters.get(jobID).get(provider).size();
	}
	
	public Map<String,PublicCloudParameters> getLstTypeVM(String jobID, String provider) {
		return mapPublicCloudParameters.get(jobID).get(provider);
	}
	
	public PublicCloudParameters getEntry(String jobID, String provider,String typeVM) {
		return mapPublicCloudParameters.get(jobID).get(provider).get(typeVM);
	}
	
	public PublicCloudParametersMap() {}
	
	@JsonIgnore
	public boolean validate() {
		for (Map.Entry<String, Map<String, Map<String, PublicCloudParameters>>> jobIDs : mapPublicCloudParameters.entrySet()) {
		    for (Map.Entry<String, Map<String, PublicCloudParameters>> providers : jobIDs.getValue().entrySet()) {
		    	for (Map.Entry<String, PublicCloudParameters> typeVMs : providers.getValue().entrySet()) {
		    		if(!typeVMs.getValue().validate()) return false;
		    	}
		    }
		}
		return true;
	}
	
	@JsonIgnore
	public Set<String> getJobIDs(){
		return mapPublicCloudParameters.keySet();
	}
	
	@JsonIgnore
	public Set<String> getProviders(){
		Set<String> set = new HashSet<>();
		for (Map.Entry<String, Map<String, Map<String, PublicCloudParameters>>> jobIDs : mapPublicCloudParameters.entrySet()) {
			set.addAll(jobIDs.getValue().keySet());
		}
		return set;
	}
	
	@JsonIgnore
	public Set<String> getTypeVMs(){
		Set<String> set = new HashSet<>();
		for (Map.Entry<String, Map<String, Map<String, PublicCloudParameters>>> jobIDs : mapPublicCloudParameters.entrySet()) {
		    for (Map.Entry<String, Map<String, PublicCloudParameters>> providers : jobIDs.getValue().entrySet()) {
		    	set.addAll(providers.getValue().keySet());
		    }
		}
		return set;
	}
	
	@JsonIgnore
	public Map<String, Set<String>> getProvidersTypesMap(){
		Map<String, Set<String>> providerAndTypes = new HashMap<String, Set<String>>();
		for (Map.Entry<String, Map<String, Map<String, PublicCloudParameters>>> jobIDs : mapPublicCloudParameters.entrySet()) {
		    for (Map.Entry<String, Map<String, PublicCloudParameters>> providers : jobIDs.getValue().entrySet()) {
		    	providerAndTypes.put(providers.getKey(), providers.getValue().keySet());
		    	System.out.println(providers.getKey()+" "+providers.getValue().keySet());
		    }
		}
		return providerAndTypes;
	}
	
	@JsonIgnore
	public Set<Triple<String,String,String>> getKeysTriples(){
		Set<Triple<String,String,String>> set = new HashSet<>();
		
		for (Map.Entry<String, Map<String, Map<String, PublicCloudParameters>>> jobIDs : mapPublicCloudParameters.entrySet()) {
		    for (Map.Entry<String, Map<String, PublicCloudParameters>> providers : jobIDs.getValue().entrySet()) {
		    	for (Map.Entry<String, PublicCloudParameters> typeVM : providers.getValue().entrySet()) {
		    		Triple<String,String,String> tmpTriple = new Triple<>(jobIDs.getKey(),providers.getKey(),typeVM.getKey());
		    		set.add(tmpTriple);
		    	}
		    }
		}
		return set;
	}
	
	@JsonIgnore
	public Map<String, Map<String, PublicCloudParameters>> get_IdVMTypes_Map(String provider){
		Map<String, Map<String, PublicCloudParameters>> idAndTypes = new HashMap<String, Map<String, PublicCloudParameters>>();
		
		for (Map.Entry<String, Map<String, Map<String, PublicCloudParameters>>> jobIDs : mapPublicCloudParameters.entrySet()) {
	    	idAndTypes.put(jobIDs.getKey(), jobIDs.getValue().get(provider));
	    	System.out.println(jobIDs.getKey() + " " + jobIDs.getValue().get(provider));
		}
		
		return idAndTypes;
	}
	
//	@JsonAnyGetter
//	public Map<String, Map<String, Map<String, PublicCloudParameters>>> getMapPublicCloudParameters(){
//		return mapPublicCloudParameters;
//	}
	
}
