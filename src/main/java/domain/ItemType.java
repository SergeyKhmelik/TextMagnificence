package domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonFormat(shape = JsonFormat.Shape.STRING)
@JsonSerialize(using = ItemTypeSerializer.class)
public enum ItemType {

    PAGE("page"), SCREEN("screen"), CHAPTER("chapter");

    private final String value;

    private ItemType(String value) {
        this.value = value;
    }

    public static ItemType fromValue(String value) {
        if (value != null) {
            for (ItemType itemType : values()) {
                if (itemType.value.equals(value)) {
                    return itemType;
                }
            }
        }
        return getDefault();
    }

    public String toValue() {
        return value;
    }

    public static ItemType getDefault() {
        return PAGE;
    }

}
