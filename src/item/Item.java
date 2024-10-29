package item;

public class Item implements Consumable, Storable {

    private String itemName;
    private int quantity;
    private String description;

    public Item(String itemName, String description, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;

    }

    @Override
    public void consume() {
        System.out.println("You consume the " + itemName + ".");
    }

    @Override
    public void store() {
        System.out.println("The " + itemName + " is stored safely.");
    }

    @Override
    public void retrieve() {
        System.out.println("The " + itemName + " is retrieved from storage.");
    }
}