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
import java.util.List;
import java.util.Map;
import java.util.Random;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.JobMLProfile;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.SVRFeature;


public class JobMLProfileGenerator {
		
		public static JobMLProfile build(List<String> featuresNames) {
			Map<String,SVRFeature> mapClassFeature = mapClassFeature(featuresNames);
			return new JobMLProfile(mapClassFeature,3,2.93,1.92);
		}
		
		private static Map<String,SVRFeature> mapClassFeature(List<String> featuresNames) {
			Map<String,SVRFeature> map = new HashMap<>();
			
			for(String featureName : featuresNames){
				SVRFeature feature = new SVRFeature();
				feature.setMu(random(0, 9));
				feature.setSigma(random(0, 9));
				feature.setW(random(0, 9));
				map.put(featureName, feature);
			}
			
			return map;
		}
		
		public static double random(double min, double max) {
			Random random = new Random();
			return (random.nextDouble() * (max-min)) + min;
		}
}
