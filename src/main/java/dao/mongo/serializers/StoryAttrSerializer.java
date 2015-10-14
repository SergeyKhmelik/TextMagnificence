package dao.mongo.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dao.mongo.enum_attributes.StoryAttr;

import java.io.IOException;

/**
 * @author Koloturka
 * @version 31.08.2015 13:50
 */
public class StoryAttrSerializer extends JsonSerializer<StoryAttr> {

	@Override
	public void serialize(StoryAttr storyAttr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
		jsonGenerator.writeString(storyAttr.toValue().toUpperCase());
	}

}
