package it.polimi.diceH2020.SPACE4Cloud.shared.inputData;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TypeVMJobClassKey {

	private int job;
	private String typeVM;

	public TypeVMJobClassKey() {
	}

	@JsonCreator
	public TypeVMJobClassKey(@JsonProperty("job") int job, @JsonProperty("typeVM") String typeVM) {
		this.setJob(job);
		this.setTypeVM(typeVM);
	}

	public int getJob() {
		return job;
	}

	public void setJob(int job) {
		this.job = job;
	}

	public String getTypeVM() {
		return typeVM;
	}

	public void setTypeVM(String typeVM) {
		this.typeVM = typeVM;
	}
	
	public static TypeVMJobClassDeserializer getDeserializer (){ 
		return new TypeVMJobClassDeserializer();
	}
	
	
	
}
