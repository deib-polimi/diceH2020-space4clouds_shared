package it.polimi.diceH2020.SPACE4Cloud.shared;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;

public class KeyDeserializer extends JsonDeserializer<TypeVMJobClassKey> {

	@Override
	public TypeVMJobClassKey deserialize(JsonParser jp, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
	       	JsonNode node = jp.getCodec().readTree(jp);
	        int id_JobClass = (Integer) ((IntNode) node.get("id_JobClass")).numberValue();
	        String id_TypeVM = node.get("id_TypeVM").asText();
	 
	        return new TypeVMJobClassKey(id_JobClass, id_TypeVM);
	}

}
