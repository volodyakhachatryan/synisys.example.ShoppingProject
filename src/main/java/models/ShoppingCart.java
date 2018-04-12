package models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by volodya.khachatryan on 4/11/2018.
 */
public class ShoppingCart implements Serializable{
    private Map<Item, Integer> addedItems;

    public ShoppingCart() {
        addedItems = new HashMap<>();
    }

    public void addItemToCart(Item item, Integer count){
        addedItems.put(item, count);
    }

    public Map<Item, Integer> getAddedItems() {
        return addedItems;
    }

    public void setAddedItems(Map<Item, Integer> addedItems) {
        this.addedItems = addedItems;
    }
}
