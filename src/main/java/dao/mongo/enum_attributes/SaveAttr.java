package dao.mongo.enum_attributes;

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
