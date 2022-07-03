package dao.mongo.enum_attributes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dao.mongo.serializers.StoryAttrSerializer;

@JsonFormat(shape = JsonFormat.Shape.STRING)
@JsonSerialize(using = StoryAttrSerializer.class)
public enum StoryAttr implements Attribute {

    ID_STORY("idStory"), NAME("name"), GENRE("genre"), CHAPTERS("chapters"), ANNOTATION("annotation"), IMAGE("image"),
    AUTHOR("author"), DATE("date"), START_PAGE("start_page");


    private final String name;

    private StoryAttr(String value) {
        this.name = value;
    }

    @Override
    public String getName() {
        return this.name;
    }




    public static StoryAttr fromValue(String value) {
        if (value != null) {
            for (StoryAttr storyAttr : values()) {
                if (storyAttr.name.equals(value)) {
                    return storyAttr;
                }
            }
        }
        return getDefault();
    }

    public String toValue() {
        return name;
    }

    public static StoryAttr getDefault() {
        return ID_STORY;
    }


}
