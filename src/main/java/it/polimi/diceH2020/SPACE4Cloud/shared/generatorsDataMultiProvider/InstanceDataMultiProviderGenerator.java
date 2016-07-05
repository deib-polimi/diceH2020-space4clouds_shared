package it.polimi.diceH2020.SPACE4Cloud.shared.generatorsDataMultiProvider;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.ClassParametersMap;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.InstanceDataMultiProvider;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.JobProfilesMap;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.PrivateCloudParameters;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.PublicCloudParametersMap;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.VMConfigurationsMap;

public class InstanceDataMultiProviderGenerator {
	
	public static InstanceDataMultiProvider build() {
		
		ClassParametersMap mapClassParameters = ClassParametersMapGenerator.build();
		PublicCloudParametersMap mapPublicCloudParameters = PublicCloudParametersMapGenerator.build();
		JobProfilesMap mapJobProfiles = JobProfilesMapGenerator.build();
		VMConfigurationsMap listVMConfigurations = VMConfigurationsMapGenerator.build();
		
		PrivateCloudParameters privateCloudParameters = new PrivateCloudParameters();
		privateCloudParameters.setE(1.789);
		privateCloudParameters.setM(1);
		privateCloudParameters.setN(4);
		privateCloudParameters.setV(7);
		return new InstanceDataMultiProvider("ID123", mapJobProfiles, mapClassParameters,  mapPublicCloudParameters,  privateCloudParameters, listVMConfigurations);
	}


}
