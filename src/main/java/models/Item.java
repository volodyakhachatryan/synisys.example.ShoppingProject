package models;

/**
 * Created by volodya.khachatryan on 4/11/2018.
 */
public class Item {
    private String itemName;
    private String itemDescription;
    private int itemPrice;

    public Item(String itemName, String itemDescription, int itemPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    public Item() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}
