package dao.db_attributes;

/**
 * Created by koloturka on 22.07.15.
 */
public enum PageAttr implements Attribute {

    ID_PAGE("idPage"), IS_END_CHAPTER("isEndChapter"),
    NEXT_CHAPTER("nextCHapter"), SCREENS("screens");

    private String name;

    PageAttr(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
