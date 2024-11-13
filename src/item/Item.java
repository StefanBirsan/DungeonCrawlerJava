package item;

import Interfaces.Consumable;
import Interfaces.Storable;
import Interfaces.ItemAction;

public class Item implements Consumable, Storable {

    private String itemName;
    private int quantity;
    private String description;
    private boolean isConsumable;
    private ItemAction itemAction;

    public Item(String itemName, String description, int quantity, boolean isConsumable, ItemAction action) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.isConsumable = isConsumable;
        this.description = description;
        this.itemAction = action;
    }

    @Override
    public void consume() {
        if (isConsumable && quantity > 0) {
            quantity--;
            System.out.println("You consume one " + itemName + ". " + quantity + " remaining.");
            itemAction.execute();
        } else {
            System.out.println("Cannot consume " + itemName + ".");
        }
    }

    @Override
    public void store() {
        System.out.println("The " + itemName + " is stored safely.");
    }

    @Override
    public void retrieve() {
        System.out.println("The " + itemName + " is retrieved from storage.");
    }

    @Override
    public void use() {
        System.out.println("You use the " + itemName + ".");
        itemAction.execute();
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isConsumable() {
        return isConsumable;
    }
}