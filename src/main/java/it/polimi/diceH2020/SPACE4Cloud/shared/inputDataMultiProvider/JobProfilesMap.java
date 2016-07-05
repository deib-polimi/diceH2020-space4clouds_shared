package it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class JobProfilesMap {
	
    private Map<String, Map<String, Map<String, JobProfile>>> mapJobProfile; //jobId->provider->typeVM
	
	public JobProfilesMap(Map<String, Map<String,Map<String,JobProfile>>> mapJobProfiles){
		this.mapJobProfile = mapJobProfiles;
	}
	
	public int getNumberTypeVM(String jobID, String provider) {
		return getMapJobProfile().get(jobID).get(provider).size();
	}
	
	
	public Map<String, JobProfile> getLstTypeVM(String jobID, String provider) {
		return getMapJobProfile().get(jobID).get(provider);
	}
	
	public JobProfile getEntry(String jobID, String provider,String typeVM) {
		return getMapJobProfile().get(jobID).get(provider).get(typeVM);
	}
	
	public JobProfilesMap() {}

	@JsonIgnore
	public boolean validate() {
		for (Map.Entry<String, Map<String, Map<String, JobProfile>>> jobIDs : mapJobProfile.entrySet()) {
		    for (Map.Entry<String, Map<String, JobProfile>> providers : jobIDs.getValue().entrySet()) {
		    	for (Map.Entry<String, JobProfile> typeVMs : providers.getValue().entrySet()) {
		    		if(!typeVMs.getValue().validate()) return false;
		    	}
		    }
		}
		return true;
	}
	
	@JsonIgnore
	public Set<String> getJobIDs(){
		return getMapJobProfile().keySet();
	}
	
	@JsonIgnore
	public Set<String> getProviders(){
		Set<String> set = new HashSet<>();
		for (Map.Entry<String, Map<String, Map<String, JobProfile>>> jobIDs : getMapJobProfile().entrySet()) {
			set.addAll(jobIDs.getValue().keySet());
		}
		
		return set;
	}
	
	//TODO useful?
	@JsonIgnore
	public Set<String> getTypeVMs(){
		Set<String> set = new HashSet<>();
		
		for (Map.Entry<String, Map<String, Map<String, JobProfile>>> jobIDs : getMapJobProfile().entrySet()) {
		    for (Map.Entry<String, Map<String, JobProfile>> providers : jobIDs.getValue().entrySet()) {
		    	set.addAll(providers.getValue().keySet());
		    }
		}
		return set;
	}
	
	@JsonIgnore
	public Map<String, Set<String>> getProvidersTypesMap(){
		Map<String, Set<String>> providerAndTypes = new HashMap<String, Set<String>>();
		for (Map.Entry<String, Map<String, Map<String, JobProfile>>> jobIDs : getMapJobProfile().entrySet()) {
		    for (Map.Entry<String, Map<String, JobProfile>> providers : jobIDs.getValue().entrySet()) {
		    	providerAndTypes.put(providers.getKey(), providers.getValue().keySet());
		    	System.out.println(providers.getKey()+" "+providers.getValue().keySet());
		    }
		}
		return providerAndTypes;
	}
	
	@JsonIgnore
	public Map<String, Map<String, JobProfile>> get_IdVMTypes_Map(String provider){
		Map<String, Map<String, JobProfile>> idAndTypes = new HashMap<String, Map<String, JobProfile>>();
		
		for (Map.Entry<String, Map<String, Map<String, JobProfile>>> jobIDs : getMapJobProfile().entrySet()) {
	    	idAndTypes.put(jobIDs.getKey(), jobIDs.getValue().get(provider));
	    	System.out.println(jobIDs.getKey() + " " + jobIDs.getValue().get(provider));
		}
		
		return idAndTypes;
	}
	
	@JsonIgnore
	public Set<Triple<String,String,String>> getKeysTriples(){
		Set<Triple<String,String,String>> set = new HashSet<>();
		
		for (Map.Entry<String, Map<String, Map<String, JobProfile>>> jobIDs : getMapJobProfile().entrySet()) {
		    for (Map.Entry<String, Map<String, JobProfile>> providers : jobIDs.getValue().entrySet()) {
		    	for (Map.Entry<String, JobProfile> typeVM : providers.getValue().entrySet()) {
		    		Triple<String,String,String> tmpTriple = new Triple<>(jobIDs.getKey(),providers.getKey(),typeVM.getKey());
		    		set.add(tmpTriple);
		    	}
		    }
		}
		
		return set;
	}
	
//	@JsonAnyGetter
//	public Map<String, Map<String, Map<String, JobProfile>>> getMapJobProfile(){
//		return mapJobProfile;
//	}
	
	
//	@JsonAnySetter
//	public void setMapJobProfile(String value) throws JsonParseException, JsonMappingException, IOException{
//		ObjectMapper mapper = new ObjectMapper();
//		Map<String,Map<String, Map<String, JobProfile>>> data2;
//		data2 = mapper.readValue( value, new TypeReference<Map<String,Map<String, Map<String, JobProfile>>>>(){});
//		mapJobProfile.putAll(data2);
//	}
	
//	@JsonAnySetter
//    public void add(String key, Map<String, Map<String, JobProfile>> value) throws JsonParseException, JsonMappingException, IOException {
//		mapJobProfile.put(key, value);
//    }
	
}
