package it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider;

import lombok.Data;

@Data
public class PrivateCloudParameters {
	private double m;  //node RAM capacity
	private double v;  //node CPUs capacity
	private int n;	   //number of physical nodes
	private double e;  //cost of energy to run one physical machine
	
	
	public boolean validate(){
		return (m>=0 && v>=0 && n>=0 && e>=0);
	}
}
