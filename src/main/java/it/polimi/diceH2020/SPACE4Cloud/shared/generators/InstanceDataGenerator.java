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
package it.polimi.diceH2020.SPACE4Cloud.shared.generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.InstanceData;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.JobClass;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.Profile;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.TypeVM;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.TypeVMJobClassKey;

public class InstanceDataGenerator {

	public static InstanceData build() {
		List<JobClass> lstClasses = lstJobClasses();
		List<TypeVM> lstTypeVMs = lstTypeVMs();
		Map<String,List<TypeVM>> mapTypes = new HashMap<String, List<TypeVM>>();
		for (JobClass job : lstClasses) {
				mapTypes.put(job.getId(), lstTypeVMs);
		}

		Profile p1 = ProfileGenerator.build(1);
		Profile p2 = ProfileGenerator.build(2);
		Profile p3 = ProfileGenerator.build(1);
		Profile p4 = ProfileGenerator.build(2);

		Map<TypeVMJobClassKey, Profile> map = new HashMap<TypeVMJobClassKey, Profile>();
		TypeVMJobClassKey key1 = new TypeVMJobClassKey();
		key1.setJob(lstClasses.get(0).getId());
		key1.setTypeVM(lstTypeVMs.get(0).getId());
		map.put(key1, p1);
		TypeVMJobClassKey key2 = new TypeVMJobClassKey();
		key2.setJob(lstClasses.get(0).getId());
		key2.setTypeVM(lstTypeVMs.get(1).getId());
		map.put(key2, p2);
		TypeVMJobClassKey key3 = new TypeVMJobClassKey();
		key3.setJob(lstClasses.get(1).getId());
		key3.setTypeVM(lstTypeVMs.get(0).getId());
		map.put(key3, p3);
		TypeVMJobClassKey key4 = new TypeVMJobClassKey();
		key4.setJob(lstClasses.get(1).getId());
		key4.setTypeVM(lstTypeVMs.get(1).getId());
		map.put(key4, p4);
		return new InstanceData("id_1",240,"Amazon", lstClasses, mapTypes, map);
	}

	private static List<JobClass> lstJobClasses() {
		List<JobClass> lst = new ArrayList<>();
		JobClass class1 = new JobClass();
		class1.setD(180);
		class1.setHlow(5);
		class1.setHup(10);
		class1.setId("1");
		class1.setJob_penalty(25.0);
		class1.setThink(10);
		lst.add(class1);
		JobClass class2 = new JobClass();
		class2.setD(150);
		class2.setHlow(5);
		class2.setHup(10);
		class2.setId("2");
		class2.setJob_penalty(14.99);
		class2.setThink(5);
		lst.add(class2);
		return lst;
	}

	private static List<TypeVM> lstTypeVMs() {
		List<TypeVM> lst = new ArrayList<>();
		TypeVM t1 = TypeVMGenerator.build(1);
		lst.add(t1);
		TypeVM t2 = TypeVMGenerator.build(2);
		lst.add(t2);
		return lst;

	}

}
