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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.JobMLProfile;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.JobMLProfilesMap;


public class JobMLProfilesMapGenerator {
		private static List<String> jobsIDs =  Arrays.asList("23","32","64");
		
		public static JobMLProfilesMap build() {
			List<String> featuresNames = Arrays.asList("x", "h", "cm","cr","mavg","mmax","nm","nr","ravg","rmax","sh1max","shtypmax","shtypavg");
			Map<String, JobMLProfile> map = new HashMap<>();
			
			for(String i : jobsIDs){
				map.put(i, JobMLProfileGenerator.build(featuresNames));
			}
			return new JobMLProfilesMap(map);
		}
}
