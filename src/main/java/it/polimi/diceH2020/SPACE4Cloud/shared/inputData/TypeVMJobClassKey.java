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
package it.polimi.diceH2020.SPACE4Cloud.shared.inputData;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.TypeVMJobClassDeserializer;

import lombok.Data;

@Data
public class TypeVMJobClassKey {

	private String job;
	private String typeVM;

	public TypeVMJobClassKey() {
	}

	@JsonCreator
	public TypeVMJobClassKey(@JsonProperty("job") String job, @JsonProperty("typeVM") String typeVM) {
		this.setJob(job);
		this.setTypeVM(typeVM);
	}

	public String getJob() {
		return job;
	}

	public void setJob(String string) {
		this.job = string;
	}

	public String getTypeVM() {
		return typeVM;
	}

	public void setTypeVM(String typeVM) {
		this.typeVM = typeVM;
	}
	
	public static TypeVMJobClassDeserializer getDeserializer (){ 
		return new TypeVMJobClassDeserializer();
	}
	
	
	
}
