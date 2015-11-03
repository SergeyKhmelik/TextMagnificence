package entity;

/**
 * Created by koloturka on 20.07.15.
 */
public class Answer {

    private String text;
    private ItemType itemType;
    private int nextItemId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public int getNextItemId() {
        return nextItemId;
    }

    public void setNextItemId(int nextItemId) {
        this.nextItemId = nextItemId;
    }
}
