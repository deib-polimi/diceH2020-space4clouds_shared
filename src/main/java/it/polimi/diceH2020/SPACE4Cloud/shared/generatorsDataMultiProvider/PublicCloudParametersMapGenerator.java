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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.PublicCloudParameters;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.PublicCloudParametersMap;


public class PublicCloudParametersMapGenerator {
	
	private static List<String> jobsIDs =  Arrays.asList("23","32","64");
	private static List<String> providers =  Arrays.asList("Amazon","Cineca");
	private static List<String> typesVMs =  Arrays.asList("medium","large");
	
	public static PublicCloudParametersMap build() {
		List<PublicCloudParameters> lstTypeVMs = lstTypeVMs();
		
		Map<String,Map<String,Map<String,PublicCloudParameters>>> mapPublicCloudParameters = new HashMap<String,Map<String,Map<String,PublicCloudParameters>>>();
		
		
		int counter = 0;
		
		Map<String,Map<String,PublicCloudParameters>> tmp2 = new HashMap<>();
		Map<String,PublicCloudParameters> tmp1 = new HashMap<>();
		for(String i : jobsIDs){
			for(String provider : providers){
				for(String typeVMs : typesVMs){
					tmp1.put(typeVMs, lstTypeVMs.get(counter%2)); //<-- %2 
					counter ++;
				}
				tmp2.put(provider,tmp1);
			}
			mapPublicCloudParameters.put(i,tmp2);
		}
		
		return new PublicCloudParametersMap(mapPublicCloudParameters);
	}
	
	public static String[] getMapFirstEntryKeys(){
		String[] keys = new String[3]; 
		keys[0] = jobsIDs.get(0);
		keys[1] = providers.get(0);
		keys[2] = typesVMs.get(0);
		
		return keys;
	}

	private static List<PublicCloudParameters> lstTypeVMs() {
		List<PublicCloudParameters> lst = new ArrayList<>();
		PublicCloudParameters t1 = PublicCloudParametersGenerator.build(1);
		lst.add(t1);
		PublicCloudParameters t2 = PublicCloudParametersGenerator.build(2);
		lst.add(t2);
		return lst;

	}
}
