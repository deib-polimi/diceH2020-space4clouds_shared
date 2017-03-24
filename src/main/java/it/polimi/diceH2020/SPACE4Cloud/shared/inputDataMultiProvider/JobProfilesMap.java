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
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class JobProfilesMap {

	// jobId -> provider -> typeVM
	@JsonUnwrapped private Map<String, Map<String, Map<String, JobProfile>>> mapJobProfile;

	public JobProfilesMap (Map<String, Map<String, Map<String, JobProfile>>> mapJobProfiles) {
		this.mapJobProfile = mapJobProfiles;
	}

	JobProfile getEntry (String jobID, String provider, String typeVM) {
		return getMapJobProfile().get(jobID).get(provider).get(typeVM);
	}

	JobProfilesMap () {
		initializeMap();
	}

	@JsonIgnore
	private void initializeMap () {
		this.mapJobProfile = new HashMap<>();
	}

	@JsonIgnore
	public boolean validate () {
		for (Map.Entry<String, Map<String, Map<String, JobProfile>>> jobIDs : mapJobProfile.entrySet()) {
			for (Map.Entry<String, Map<String, JobProfile>> providers : jobIDs.getValue().entrySet()) {
				for (Map.Entry<String, JobProfile> typeVMs : providers.getValue().entrySet()) {
					if(!typeVMs.getValue().validate()) return false;
				}
			}
		}
		return true;
	}

	@JsonIgnore
	Set<String> getJobIDs () {
		return getMapJobProfile().keySet();
	}

	@JsonIgnore
	public Set<String> getProviders () {
		Set<String> set = new HashSet<>();

		for (Map.Entry<String, Map<String, Map<String, JobProfile>>> jobIDs : getMapJobProfile().entrySet()) {
			set.addAll(jobIDs.getValue().keySet());
		}

		return set;
	}

	@JsonIgnore
	Map<String, Set<String>> getProvidersTypesMap () {
		Map<String, Set<String>> providerAndTypes = new HashMap<>();

		for (Map.Entry<String, Map<String, Map<String, JobProfile>>> jobIDs : getMapJobProfile().entrySet()) {
			for (Map.Entry<String, Map<String, JobProfile>> providers : jobIDs.getValue().entrySet()) {
				providerAndTypes.put(providers.getKey(), providers.getValue().keySet());
			}
		}

		return providerAndTypes;
	}

	@JsonIgnore
	Set<Triple<String,String,String>> getKeysTriples () {
		Set<Triple<String,String,String>> set = new HashSet<>();

		for (Map.Entry<String, Map<String, Map<String, JobProfile>>> jobIDs : getMapJobProfile().entrySet()) {
			for (Map.Entry<String, Map<String, JobProfile>> providers : jobIDs.getValue().entrySet()) {
				for (Map.Entry<String, JobProfile> typeVM : providers.getValue().entrySet()) {
					Triple<String,String,String> tmpTriple = new Triple<>(jobIDs.getKey(),providers.getKey(),typeVM.getKey());
					set.add(tmpTriple);
				}
			}
		}

		return set;
	}
}
