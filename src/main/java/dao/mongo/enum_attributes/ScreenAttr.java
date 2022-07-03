package dao.mongo.enum_attributes;

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
