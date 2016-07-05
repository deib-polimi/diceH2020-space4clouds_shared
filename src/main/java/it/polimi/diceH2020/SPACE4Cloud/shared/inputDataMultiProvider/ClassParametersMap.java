package it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider;

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ClassParametersMap {
	private Map<String,ClassParameters> mapClassParameters;
	
	public ClassParametersMap(Map<String,ClassParameters> mapClassParameters){
		this.mapClassParameters = mapClassParameters;
	}
	
	public ClassParameters getClassParameters(String jobID) {
		return mapClassParameters.get(jobID);
	}
	
	public ClassParametersMap() {}

	@JsonIgnore
	public boolean validate() {
		for (Map.Entry<String, ClassParameters> jobIDs : mapClassParameters.entrySet()) {
			if(!jobIDs.getValue().validate()) return false;
		}
		return true;
	}
	
	@JsonIgnore
	public Set<String> getJobIDs(){
		return mapClassParameters.keySet();
	}
	
//	@JsonAnyGetter
//	public Map<String, ClassParameters> getMapClassParameters(){
//		return mapClassParameters;
//	}
	
//	@JsonAnyGetter
//	public Map<String,ClassParameters> getMapClassParameters(){
//		return mapClassParameters;
//	}
	
}

