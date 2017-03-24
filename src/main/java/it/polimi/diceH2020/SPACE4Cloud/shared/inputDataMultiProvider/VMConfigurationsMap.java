/*
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
package it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.util.*;

@Data
public class VMConfigurationsMap {

	@JsonInclude(Include.NON_NULL)
	private Map<String, VMConfiguration> mapVMConfigurations;

	public VMConfigurationsMap (Map<String, VMConfiguration> listVMConfigurations) {
		this.mapVMConfigurations = listVMConfigurations;
	}

	VMConfigurationsMap () {}

	@JsonIgnore
	public boolean validate () {
		boolean valid = true;

		Iterator<VMConfiguration> iterator = mapVMConfigurations.values ().iterator ();
		while (valid && iterator.hasNext ()) {
			VMConfiguration vmConfiguration = iterator.next ();
			valid = vmConfiguration.validate ();
		}

		return valid;
	}

	@JsonIgnore
	Map<String, Set<String>> getProvidersTypesMap () {
		Map<String, Set<String>> providerAndTypes = new HashMap<>();

		Set<String> set = new HashSet<>();
		for (Map.Entry<String, VMConfiguration> vm : mapVMConfigurations.entrySet()) {
			set.add(vm.getKey());
		}

		providerAndTypes.put("inHouse", set);
		return providerAndTypes;
	}
}
