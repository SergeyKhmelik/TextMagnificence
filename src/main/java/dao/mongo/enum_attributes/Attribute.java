package dao.mongo.enum_attributes;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by koloturka on 22.07.15.
 */
@JsonFormat(shape = JsonFormat.Shape.STRING)
public interface Attribute {

    String getName();

}
