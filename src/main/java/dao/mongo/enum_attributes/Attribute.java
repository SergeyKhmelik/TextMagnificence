package dao.mongo.enum_attributes;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public interface Attribute {

    public String getName();

}
