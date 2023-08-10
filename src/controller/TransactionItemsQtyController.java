package controller;

import database.Item;
import database.TransactionItemsQty;

import java.util.ArrayList;

public class TransactionItemsQtyController {
    private final ArrayList<TransactionItemsQty> transactionItemsQties = new ArrayList<>();

    public TransactionItemsQtyController() {

    }

    public int getAmountQty(Item item) {
        int sum = 0;
        for (TransactionItemsQty transactionItemsQty : transactionItemsQties) {
            if (transactionItemsQty.getItem().equals(item)) {
                sum += transactionItemsQty.getQty();
            }
        }
        return sum;
    }

    private boolean isInsufficient(Item item, int qty) {
        return getAmountQty(item) < Math.abs(qty) && qty < 0;
    }

    public boolean addTransaction(Item item, int qty) {
        if(isInsufficient(item, qty)) return false;
        transactionItemsQties.add(new TransactionItemsQty(item, qty));
        return true;
    }

    public void showTransaction() {
        if (transactionItemsQties.size() == 0) {
            System.out.println("Belum ada relasi.");
            return;
        }
        for (TransactionItemsQty transactionItemsQty : transactionItemsQties) {
            System.out.println(transactionItemsQty.getItem().getNameItem() + " - "
                    + transactionItemsQty.getQty());
        }
    }
}
