package dao.mongo.enum_attributes;

/**
 * Created by koloturka on 22.07.15.
 */
public enum ScreenAttr implements Attribute {

    ID_SCREEN("idScreen"), TEXT("text"),
    ANSWERS("answers");

    private String name;

    private ScreenAttr(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
