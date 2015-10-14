package dao.mongo.enum_attributes;

/**
 * Created by koloturka on 28.09.2015.
 */
public enum SaveAttr {

    ID_STORY("idStory"), USERNAME("username"), PAGES("pages");

    private String name;

    private SaveAttr(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
