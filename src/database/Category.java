package database;

public class Category {
    private final String idCategory;
    private String nameCategory;

    public Category(String categoryId, String categoryName) {
        this.idCategory = categoryId;
        this.nameCategory = categoryName;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
