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

import java.util.HashMap;
import java.util.Map;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.VMConfiguration;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.VMConfigurationsMap;


public class VMConfigurationsMapGenerator {
	
	public static VMConfigurationsMap build() {
		Map<String,VMConfiguration> lstTypeVMs = lstVMConfigurations();
		return new VMConfigurationsMap(lstTypeVMs);
	}
	

	private static Map<String,VMConfiguration> lstVMConfigurations() {
		Map<String,VMConfiguration> map = new HashMap<>();
		VMConfiguration t1 = VMConfigurationsGenerator.build(1);
		map.put("medium", t1);
		VMConfiguration t2 = VMConfigurationsGenerator.build(2);
		map.put("large", t2);
		return map;
	}
}
