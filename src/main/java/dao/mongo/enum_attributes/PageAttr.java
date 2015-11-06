package dao.mongo.enum_attributes;

public enum PageAttr implements Attribute {

    ID_PAGE("idPage"), ID_CHAPTER("idChapter"), IS_END_CHAPTER("isEndChapter"),
    NEXT_CHAPTER("nextChapter"), SCREENS("screens");

    private String name;

    private PageAttr(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
