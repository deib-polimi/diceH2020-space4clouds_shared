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
package it.polimi.diceH2020.SPACE4Cloud.shared.inputData.old;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import it.polimi.diceH2020.SPACE4Cloud.shared.inputDataMultiProvider.TypeVMJobClassKey;

public class TypeVMJobClassDeserializer_old extends KeyDeserializer {

	@Override
	public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String[] res = key.split(", |\\(|\\)");
		String[] arrayJob = res[1].split("=");
		String jobID = arrayJob[1];//Ints.tryParse(arrayJob[1]); import com.google.common.primitives.Ints;
		String[] arrayTypeVM = res[2].split("=");
		String tVM = arrayTypeVM[1];
		return new TypeVMJobClassKey(jobID, tVM);
	}

}