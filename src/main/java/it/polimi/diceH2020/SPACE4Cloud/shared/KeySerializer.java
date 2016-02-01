package it.polimi.diceH2020.SPACE4Cloud.shared;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class KeySerializer extends JsonSerializer<TypeVMJobClassKey>{

	@Override
	public void serialize(TypeVMJobClassKey value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeNumberField("id_JobClass", value.getJob());
        jgen.writeStringField("id_TypeVM", value.getTypeVM());
        jgen.writeEndObject();
		
	}

}
