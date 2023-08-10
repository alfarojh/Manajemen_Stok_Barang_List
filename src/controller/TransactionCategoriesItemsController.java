package controller;

import custom.Text;
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
        Text text = new Text("",
                new String[]{"Kategori", "Item"},
                new char[]{'l', 'l'},
                new int[]{20,20});
        text.printSubTitle();
        for (int index = 0; index < transactionCategoriesItems.size(); index++) {
            text.printBody(index, new String[]{
                    transactionCategoriesItems.get(index).getCategory().getNameCategory(),
                    transactionCategoriesItems.get(index).getItem().getNameItem()
            });
        }
        text.line();
    }

    public void getCategoriesByItem(Item item) {
        for (int index = 0; index < transactionCategoriesItems.size(); index++) {
            if (transactionCategoriesItems.get(index).getItem().equals(item)) {
                System.out.println((index + 1) + ". " + transactionCategoriesItems.get(index).getCategory().getNameCategory());
            }
        }
    }
}
