package controller;

import database.Category;
import input.InputHandler;

import java.util.ArrayList;

public class CategoryControler {
    private final ArrayList<Category> categories = new ArrayList<>();

    public CategoryControler() {

    }

    public int getCategoriesSize() {
        return categories.size();
    }
    public Category getCategoryByName(String categoryName) {
        int indexCategory = getIndexCategoryByName(categoryName);
        if (indexCategory < 0) return null;
        return categories.get(indexCategory);
    }

    public Category getCategoryByID(String categoryID) {
        int indexCategory = getIndexCategoryByID(categoryID);
        if (indexCategory < 0) return null;
        return categories.get(indexCategory);
    }
    public boolean isCategoryNameExist(String categoryName) {
        for (Category category : categories) {
            if (category.getNameCategory().equalsIgnoreCase(categoryName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCategoryIDExist(String categoryID) {
        for (Category category : categories) {
            if (category.getIdCategory().equalsIgnoreCase(categoryID)) {
                return true;
            }
        }
        return false;
    }

    public String getNewIDCategory(String categoryName) {
        return String.format("%03d", categories.size());
    }

    public int getIndexCategoryByName(String categoryName) {
        for (int indexCategory = 0; indexCategory < categories.size(); indexCategory++) {
            if(categories.get(indexCategory).getNameCategory().equals(categoryName)){
                return indexCategory;
            }
        }
        return -1;
    }

    public int getIndexCategoryByID(String categoryID) {
        for (int indexCategory = 0; indexCategory < categories.size(); indexCategory++) {
            if(categories.get(indexCategory).getIdCategory().equalsIgnoreCase(categoryID)){
                return indexCategory;
            }
        }
        return -1;
    }

    public String getIDByNameCategory(String categoryName) {
        int indexCategory = getIndexCategoryByName(categoryName);
        if (indexCategory < 0) return null;
        return categories.get(indexCategory).getIdCategory();
    }

    public String getNameByIDCategory(String categoryID) {
        int indexCategory = getIndexCategoryByName(categoryID);
        if (indexCategory < 0) return null;
        return categories.get(indexCategory).getNameCategory();
    }

    public boolean addCategory(String categoryName) {
        if(isCategoryNameExist(categoryName)) {
            InputHandler inputHandler = new InputHandler();
            String choice = inputHandler.getInputText("Nama kategori sudah ada, ingin tetap melanjutkan (Y/n)?  ");
            inputHandler.close();
            if(!choice.equals("Y")) {
                return false;
            }
        }
        categories.add(new Category(getNewIDCategory(categoryName), categoryName));
        return true;
    }

    public boolean removeCategoryByID(String categoryID) {
        if(!isCategoryIDExist(categoryID)) return false;
        categories.remove(getIndexCategoryByID(categoryID));
        return true;
    }

    public boolean removeCategoryByName(String categoryName) {
        if(!isCategoryNameExist(categoryName)) return false;
        categories.remove(getIndexCategoryByName(categoryName));
        return true;
    }

    public boolean updateNameCategoryByID(String categoryID, String newNameCategory) {
        if(!isCategoryIDExist(categoryID)) return false;
        categories.get(getIndexCategoryByID(categoryID)).setNameCategory(newNameCategory);
        return true;
    }

    public boolean updateNameCategoryByName(String categoryName, String newNameCategory) {
        if(!isCategoryNameExist(categoryName)) return false;
        categories.get(getIndexCategoryByName(categoryName)).setNameCategory(newNameCategory);
        return true;
    }

    public boolean showCategories() {
        if (categories.size() == 0) return false;
        for (Category category : categories) {
            System.out.println(category.getIdCategory() + " - " + category.getNameCategory());
        }
        return true;
    }
}
