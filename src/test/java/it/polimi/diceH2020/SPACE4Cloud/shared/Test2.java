package it.polimi.diceH2020.SPACE4Cloud.shared;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.ExtendedSSLSession;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class Test2 {

	@Test
	public void test() {
		InstanceData data = InstanceDataGenerator.build();
		System.out.println(data.toString());
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			SimpleModule module = new SimpleModule();
			module.addSerializer(TypeVMJobClassKey.class, new KeySerializer());
			mapper.registerModule(module);
			TypeVMJobClassKey key = new TypeVMJobClassKey(10, "prova");			
			
			String serialized = mapper.writeValueAsString(key);
			System.out.println(serialized);
			module.addDeserializer(TypeVMJobClassKey.class, new KeyDeserializer());
			mapper.registerModule(module);

			TypeVMJobClassKey myData = mapper.readValue(serialized, TypeVMJobClassKey.class);
			assertTrue(myData.getJob() ==10);
			
			Map<TypeVMJobClassKey, String> map = new HashMap<>();
			map.put( key, "ciao");
			serialized = mapper.writeValueAsString(map);
			System.out.println(serialized);
			Map<TypeVMJobClassKey, String> myData2 = mapper.readValue(serialized, Map.class );
			String p = myData2.get(key);
			System.out.println(p);
			assertTrue(p == "ciao" );
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
