package controller;

import custom.Text;
import database.Category;
import database.Item;
import input.InputHandler;

import java.util.ArrayList;

public class ItemController {
    private final ArrayList<Item> items = new ArrayList<>();
    
    public ItemController() {
        
    }
    public int getItemsSize() {
        return items.size();
    }

    public Item getItemyByName(String itemName) {
        int indexCategory = getIndexItemByName(itemName);
        if (indexCategory < 0) return null;
        return items.get(indexCategory);
    }

    public Item getItemyByID(String itemID) {
        int indexCategory = getIndexItemByID(itemID);
        if (indexCategory < 0) return null;
        return items.get(indexCategory);
    }

    public boolean isItemNameExist(String itemName) {
        for (Item item : items) {
            if (item.getNameItem().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isItemIDExist(String itemID) {
        for (Item item : items) {
            if (item.getIdItem().equalsIgnoreCase(itemID)) {
                return true;
            }
        }
        return false;
    }

    public String getNewIDItem(String itemName) {
        if (items.size() == 0) return "1";
        else return String.valueOf(Integer.parseInt(items.get(items.size()-1).getIdItem()) + 1);
    }

    public int getIndexItemByName(String itemName) {
        for (int indexCategory = 0; indexCategory < items.size(); indexCategory++) {
            if(items.get(indexCategory).getNameItem().equals(itemName)){
                return indexCategory;
            }
        }
        return -1;
    }

    public int getIndexItemByID(String categoryID) {
        for (int indexCategory = 0; indexCategory < items.size(); indexCategory++) {
            if(items.get(indexCategory).getIdItem().equalsIgnoreCase(categoryID)){
                return indexCategory;
            }
        }
        return -1;
    }

    public String getIDByNameItem(String itemName) {
        return items.get(getIndexItemByName(itemName)).getIdItem();
    }

    public String getNameByIDItem(String categoryID) {
        return items.get(getIndexItemByName(categoryID)).getNameItem();
    }

    public boolean addItem(String itemName) {
        if(isItemNameExist(itemName)) {
            InputHandler inputHandler = new InputHandler();
            String choice = inputHandler.getInputText("Nama item sudah ada, ingin tetap melanjutkan (Y/n)?  ");
            inputHandler.close();
            if(!choice.equals("Y")) {
                return false;
            }
        }
        items.add(new Item(getNewIDItem(itemName), itemName));
        return true;
    }

    public boolean removeItemByID(String itemId) {
        if(!isItemIDExist(itemId)) return false;
        items.remove(getIndexItemByID(itemId));
        return true;
    }

    public boolean removeItemByName(String itemName) {
        if(!isItemNameExist(itemName)) return false;
        items.remove(getIndexItemByName(itemName));
        return true;
    }

    public boolean updateNameItemByID(String itemId, String newNameItem) {
        if(!isItemIDExist(itemId)) return false;
        items.get(getIndexItemByID(itemId)).setNameItem(newNameItem);
        return true;
    }

    public boolean updateNameItemByName(String itemName, String newNameItem) {
        if(!isItemNameExist(itemName)) return false;
        items.get(getIndexItemByName(itemName)).setNameItem(newNameItem);
        return true;
    }

    public boolean showItems() {
        if (items.size() == 0) return false;
        Text text = new Text("",
                new String[]{"ID", "Nama Item"},
                new char[]{'c', 'l'},
                new int[]{5, 50});
        text.printSubTitle();
        for (int index = 0; index < items.size(); index++) {
            text.printBody(index, new String[]{
                    items.get(index).getIdItem(),
                    items.get(index).getNameItem()
            });
        }
        text.printBody(items.size(), new String[]{ "0", "Keluar" });
        text.line();
        return true;
    }
}
