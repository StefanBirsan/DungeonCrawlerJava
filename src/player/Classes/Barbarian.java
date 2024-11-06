package player.Classes;

import player.PlayerClass;

public class Barbarian extends PlayerClass {
    private boolean isRaging;

    public Barbarian(String name) {
        super(name, 150, 10, 30);
        this.isRaging = false;
    }

    @Override
    protected void setupStartingItems() {
        inventory.add("Battle Axe");
        inventory.add("Healing Potion");
    }

    @Override
    public void specialAction() {
        System.out.println(name + " uses Rage, doubling attack power for the next hit!");
        isRaging = true;
    }

    @Override
    public void attack() {
        if (isRaging) {
            System.out.println(name + " attacks with " + attackPower * 2 + " power.");
            isRaging = false;
        } else {
            System.out.println(name + " attacks with " + attackPower + " power.");
        }
    }
}