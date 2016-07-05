package it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider;

import lombok.Data;

@Data
public class VMConfiguration {
	private double core;
    private double memory;
    private String provider = "inHouse";
    
    public boolean validate(){
		return (core>=0 && memory>=0 && provider.equals("inHouse"));
	}
}
