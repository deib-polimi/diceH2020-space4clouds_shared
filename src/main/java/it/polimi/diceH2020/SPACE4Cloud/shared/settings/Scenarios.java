package it.polimi.diceH2020.SPACE4Cloud.shared.settings;

public enum Scenarios {
	PublicPeakWorkload(CloudType.Public,"Peak Workload"),
	PublicAvgWorkLoad(CloudType.Public,"Average Workload"),
	PrivateAdmissionControl(CloudType.Private,"Admission Control"), //With Capacity constraint
	PrivateNoAdmissionControl(CloudType.Private,"No Admission Control");
	
	private final String description;       
	private final CloudType cloudType;       

    private Scenarios(CloudType cloudType,String description) {
        this.description = description;
        this.cloudType = cloudType;
    }

    public String getDescription() {
       return this.description;
    }
    
    public CloudType getCloudType() {
        return this.cloudType;
    }
}
