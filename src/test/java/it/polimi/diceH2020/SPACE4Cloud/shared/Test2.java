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
package it.polimi.diceH2020.SPACE4Cloud.shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import it.polimi.diceH2020.SPACE4Cloud.shared.generators.InstanceDataGenerator;
import it.polimi.diceH2020.SPACE4Cloud.shared.generators.SolutionGenerator;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.InstanceData;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.TypeVMJobClassKey;
import it.polimi.diceH2020.SPACE4Cloud.shared.solution.Solution;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class Test2 {

	@Test
	public void test1() {
		InstanceData data = InstanceDataGenerator.build();
		System.out.println(data.toString());

		try {
			ObjectMapper mapper = new ObjectMapper();
			SimpleModule module = new SimpleModule();
			module.addKeyDeserializer(TypeVMJobClassKey.class, TypeVMJobClassKey.getDeserializer() );
			mapper.registerModule(module);

			String serialized = mapper.writeValueAsString(data);
			System.out.println(serialized);

			InstanceData data2 = mapper.readValue(serialized, InstanceData.class);
			System.out.println(data2.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(data.getGamma() ==240);
	}

	@Test
	public void test2(){
		Solution sol = SolutionGenerator.build();
		try {
			ObjectMapper mapper = new ObjectMapper();

			String serialized = mapper.writeValueAsString(sol);
			System.out.println(serialized);
			Solution data2 = mapper.readValue(serialized, Solution.class);
			System.out.println(data2.toString());
			System.out.println(data2.toStringReduced());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
