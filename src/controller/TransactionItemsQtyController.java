package controller;

import custom.Text;
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
            System.out.println("Belum ada transaksi.");
            return;
        }
        Text text = new Text("",
                new String[]{"Kategori", "Item"},
                new char[]{'l', 'c'},
                new int[]{30, 10});
        text.printSubTitle();
        for (int index = 0; index < transactionItemsQties.size(); index++) {
            String qty = String.valueOf(transactionItemsQties.get(index).getQty());
            if (transactionItemsQties.get(index).getQty() > 0){
                qty = "+" + transactionItemsQties.get(index).getQty();
            }
            text.printBody(index, new String[]{
                    transactionItemsQties.get(index).getItem().getNameItem(), qty
            });
        }
        text.line();
    }
}
