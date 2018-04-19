package models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by volodya.khachatryan on 4/11/2018.
 */

@Component
@Scope(value = "prototype")
public class Item {
    private int itemId;
    private String itemName;
    private String itemDescription;
    private int itemPrice;

    public Item(String itemName, String itemDescription, int itemPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    public Item(int itemId, String itemName, String itemDescription, int itemPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    public Item() {
    }

    public int getItemId() {
        return itemId;
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
