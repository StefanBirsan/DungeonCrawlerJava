package player;

import Dungeon.Enemy;
import item.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerClass extends Player {
    protected int mana;
    protected int attackPower;
    protected List<Item> inventory;
    private String playerClass;

    public PlayerClass(String name, int health, int mana, int attackPower) {
        super(name);
        this.mana = mana;
        this.attackPower = attackPower;
        this.inventory = new ArrayList<>();
        setupStartingItems();
    }

    protected abstract void setupStartingItems();

    public abstract void specialAction();

    public abstract void attack(Enemy enemy);

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public void showInventory() {
        System.out.println(name + "'s Inventory:");
        for (Item item : inventory) {
            System.out.println("- " + item.getItemName());
        }
    }

    public int getHealth() {
        return health;
    }

    public void showStats() {
        System.out.println("===== Player Stats =====");
        System.out.println("Name: " + name);
        System.out.println("Health: " + health);
        System.out.println("Mana: " + mana);
        System.out.println("Base DMG: " + attackPower);
        System.out.println("XP: " + experience);
        System.out.println("========================");
    }

    public List<String> getInventory() {
        List<java.lang.String> itemNames = new ArrayList<>();
        for (Item item : inventory) {
            itemNames.add(item.getItemName());
        }
        return itemNames;
    }

    public List<Item> getInventoryItems() {
        return inventory;
    }

}