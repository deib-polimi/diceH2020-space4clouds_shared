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
