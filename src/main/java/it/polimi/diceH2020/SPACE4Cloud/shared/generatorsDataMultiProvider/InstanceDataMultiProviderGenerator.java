/**
 * Copyright 2015 deib-polimi
 * Contact: deib-polimi <michele.ciavotta@polimi.it>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
