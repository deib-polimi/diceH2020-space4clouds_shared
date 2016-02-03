package it.polimi.diceH2020.SPACE4Cloud.shared.inputData;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.google.common.primitives.Ints;

public class TypeVMJobClassDeserializer extends KeyDeserializer {

	@Override
	public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String[] res = key.split(", |\\(|\\)");
		String[] arrayJob = res[1].split("=");
		int jobID = Ints.tryParse(arrayJob[1]);
		String[] arrayTypeVM = res[2].split("=");
		String tVM = arrayTypeVM[1];
		return new TypeVMJobClassKey(jobID, tVM);
	}

}