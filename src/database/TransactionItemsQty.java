package database;

public class TransactionItemsQty {
    private final Item item;
    private final int qty;
    public TransactionItemsQty(Item item, int qty) {
        this.item = item;
        this.qty = qty;
    }

    public Item getItem() {
        return item;
    }

    public int getQty() {
        return qty;
    }
}
