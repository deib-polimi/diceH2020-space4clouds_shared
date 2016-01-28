package it.polimi.diceH2020.SPACE4Cloud.shared;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class InstanceData {
	private int gamma;

	private List<JobClass> lstClass;

	private Map<Integer, TypeVM> mapTypeVMs;

	private Map<TypeVMJobClassKey, Profile> mapProfiles;

	public InstanceData(int gamma, List<JobClass> classes, Map<Integer, TypeVM>  types, Map<TypeVMJobClassKey, Profile> profiles) {
		this.gamma = gamma;
		lstClass = classes;
		mapTypeVMs = types;
		mapProfiles = profiles;
	}

}
