package dao.db_attributes;

/**
 * Created by koloturka on 22.07.15.
 */
public enum ScreenAttr implements Attribute {

    ID_SCREEN("screens.$.idScreen"), TEXT("screens.$.text"),
    ANSWERS("screens.$.answers");

    private String name;

    ScreenAttr(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
