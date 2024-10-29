package player;

import java.util.ArrayList;

public abstract class PlayerClass extends Player {
    private String playerClass;
    protected int mana;
    protected int attackPower;
    protected ArrayList<String> inventory;

    public PlayerClass(String name, int health, int mana, int attackPower) {
        super(name);
        this.health = health;  // override default health
        this.mana = mana;
        this.attackPower = attackPower;
        this.inventory = new ArrayList<>();
        setupStartingItems();
    }

    protected abstract void setupStartingItems();

    public abstract void specialAction();

    public String getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    public void attack() {
        System.out.println(name + " attacks with " + attackPower + " power.");
    }

    public void showInventory() {
        System.out.println(name + "'s Inventory: " + inventory);
    }

}
