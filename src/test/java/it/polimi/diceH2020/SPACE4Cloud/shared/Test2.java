package it.polimi.diceH2020.SPACE4Cloud.shared;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import it.polimi.diceH2020.SPACE4Cloud.shared.generators.InstanceDataGenerator;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.InstanceData;
import it.polimi.diceH2020.SPACE4Cloud.shared.inputData.TypeVMJobClassKey;

public class Test2 {

	@Test
	public void test() {
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
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(data.getGamma() ==240);
	}

}
