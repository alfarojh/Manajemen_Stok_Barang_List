package controller;

import database.Category;
import database.Item;
import database.TransactionCategoriesItems;

import java.util.ArrayList;

public class TransactionCategoriesItemsController {
    private final ArrayList<TransactionCategoriesItems> transactionCategoriesItems = new ArrayList<>();

    public TransactionCategoriesItemsController() {

    }

    private boolean isDuplicate(Category category, Item item) {
        for (TransactionCategoriesItems transactionCategoriesItem : transactionCategoriesItems) {
            if (transactionCategoriesItem.getCategory().equals(category) &&
                    transactionCategoriesItem.getItem().equals(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean addTransaction(Category category, Item item) {
        if (isDuplicate(category, item)) return false;
        transactionCategoriesItems.add(new TransactionCategoriesItems(category, item));
        return true;
    }

    public void showTransaction() {
        if (transactionCategoriesItems.size() == 0) {
            System.out.println("Belum ada relasi.");
            return;
        }
        for (TransactionCategoriesItems transactionCategoriesItem : transactionCategoriesItems) {
            System.out.println(transactionCategoriesItem.getCategory().getNameCategory() + " - "
                    + transactionCategoriesItem.getItem().getNameItem());
        }
    }
}
