package dao.mongo.enum_attributes;

public enum AnswerAttr implements Attribute {

    TEXT("text"), NEXT_ITEM("nextItem"), NEXT_ITEM_ID("nextItemId");

    private AnswerAttr(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

}
