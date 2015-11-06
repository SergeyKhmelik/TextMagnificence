package dao.mongo.enum_attributes;

public enum ChapterAttr implements Attribute {

    ID_CHAPTER("idChapter"), ID_STORY("idStory"), NAME("name"),
    DESCRIPTION("description"), START_PAGES("start_pages");

    private String name;

    private ChapterAttr(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
