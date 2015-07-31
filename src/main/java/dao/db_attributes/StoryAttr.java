package dao.db_attributes;

/**
 * Created by koloturka on 22.07.15.
 */
public enum StoryAttr implements Attribute {

    ID_STORY("idStory"), NAME("name"), GENRE("genre"), CHAPTERS("chapters"),
    ANNOTATION("annotation"), IMAGE("image");

    private String name;

    StoryAttr(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
