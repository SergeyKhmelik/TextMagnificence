package domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by koloturka on 20.07.15.
 */

public class Answer {

    @NotNull
    @Size(min = 2, max = 3000, message = "Text size should be in 2-3000 symbols range.")
    private String text;

    @NotNull(message = "Next item should not be null.")
    private ItemType nextItem;

    @Min(value = 1, message = "Next item id should be positive integer.")
    private int nextItemId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ItemType getNextItem() {
        return nextItem;
    }

    public void setNextItem(ItemType itemType) {
        this.nextItem = itemType;
    }

    public int getNextItemId() {
        return nextItemId;
    }

    public void setNextItemId(int nextItemId) {
        this.nextItemId = nextItemId;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", nextItem=" + nextItem +
                ", nextItemId=" + nextItemId +
                '}';
    }

}
