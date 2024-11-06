// src/player/PlayerClass.java
package player;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerClass {
    protected String name;
    protected int health;
    protected int mana;
    protected int attackPower;
    protected List<String> inventory;
    private String playerClass;

    public PlayerClass(String name, int health, int mana, int attackPower) {
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.attackPower = attackPower;
        this.inventory = new ArrayList<>();
        setupStartingItems();
    }

    protected abstract void setupStartingItems();

    public abstract void specialAction();

    public abstract void attack();

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public void showInventory() {
        System.out.println(name + "'s Inventory:");
        for (String item : inventory) {
            System.out.println("- " + item);
        }
    }

    public int getHealth() {
        return health;
    }
}