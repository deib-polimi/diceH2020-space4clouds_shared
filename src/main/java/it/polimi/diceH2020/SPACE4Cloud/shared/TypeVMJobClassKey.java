package it.polimi.diceH2020.SPACE4Cloud.shared;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@JsonSerialize(using = KeySerializer.class)
@JsonDeserialize(using = KeyDeserializer.class)
//@Data
public class TypeVMJobClassKey {

	private int job;
	private String typeVM;
	
	public TypeVMJobClassKey(int jobClass, String typeVM){
		this.setJob(jobClass);
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
}
