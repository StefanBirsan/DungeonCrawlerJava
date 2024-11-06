package player.Classes;

import player.PlayerClass;

public class Wizzard extends PlayerClass {
    private boolean isDoubled;

    public Wizzard(String name) {
        super(name, 100, 20, 10);
        this.isDoubled = false;
        setPlayerClass("Wizzard");
    }

    @Override
    protected void setupStartingItems() {
        inventory.add("Staff");
        inventory.add("Healing Potion");
    }

    @Override
    public void specialAction() {
        System.out.println(name + " starts casting doubling truble, doubling the power for the next hit!");
        isDoubled = true;
    }

    @Override
    public void attack() {
        if (isDoubled) {
            System.out.println(name + " attacks with " + attackPower * 2 + " power.");
            isDoubled = false;
        } else {
            System.out.println(name + " attacks with " + attackPower + " power.");
        }
    }

    public void castSpell(String spell, Integer damage) {
        System.out.println(name + " casts " + spell + "!");
        System.out.println("Deals " + damage + " damage.");
    }
}