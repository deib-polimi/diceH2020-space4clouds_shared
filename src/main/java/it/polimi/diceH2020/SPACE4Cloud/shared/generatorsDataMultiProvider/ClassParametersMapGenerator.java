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

import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.ClassParameters;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.ClassParametersMap;


public class ClassParametersMapGenerator {
		private static List<String> jobsIDs =  Arrays.asList("23","32","64");
		
		public static ClassParametersMap build() {
			List<ClassParameters> lstClassParam = lstClassParam();
			Map<String,ClassParameters> mapClassParameters = new HashMap<>();
			int counter = 0;
			for(String i : jobsIDs){
				mapClassParameters.put(i,lstClassParam.get(counter%2));
				counter++;
			}
			return new ClassParametersMap(mapClassParameters);
		}
		
		public static String[] getMapFirstEntryKeys(){
			String[] keys = new String[1]; 
			keys[0] = jobsIDs.get(0);
			return keys;
		}
		
		private static List<ClassParameters> lstClassParam() {
			List<ClassParameters> lst = new ArrayList<>();
			ClassParameters t1 = ClassParametersGenerator.build(1);
			lst.add(t1);
			ClassParameters t2 = ClassParametersGenerator.build(2);
			lst.add(t2);
			return lst;
		}
}
