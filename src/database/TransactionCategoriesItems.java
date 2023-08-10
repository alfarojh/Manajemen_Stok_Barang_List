package database;

public class TransactionCategoriesItems {
    private final Category category;
    private final Item item;

    public TransactionCategoriesItems(Category category, Item item) {
        this.category = category;
        this.item = item;
    }

    public Category getCategory() {
        return category;
    }

    public Item getItem() {
        return item;
    }
}
