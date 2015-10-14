package domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Koloturka
 * @version 19.08.2015 19:29
 */
public class ItemTypeSerializer extends JsonSerializer<ItemType> {

	@Override
	public void serialize(ItemType itemType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
		jsonGenerator.writeString(itemType.toValue().toLowerCase());
	}

}
