package database;

public class Item {
    private final String idItem;
    private String nameItem;

    public Item(String itemId, String itemName) {
        this.idItem = itemId;
        this.nameItem = itemName;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public String getIdItem() {
        return idItem;
    }
}
