package dao.db_attributes;

/**
 * Created by koloturka on 22.07.15.
 */
public enum ChapterAttr implements Attribute {

    ID_CHAPTER("idChapter"), NAME("name"),
    DESCRIPTION("description"), PAGES("pages");

    private String name;

    ChapterAttr(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
