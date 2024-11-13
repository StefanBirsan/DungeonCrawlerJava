package player.Classes;

import Dungeon.Enemy;
import Interfaces.ItemAction;
import player.PlayerClass;
import item.Item;

public class Wizzard extends PlayerClass {
    private boolean isDoubled;

    public Wizzard(String name) {
        super(name, 100, 20, 10);
        this.isDoubled = false;
        setPlayerClass("Wizzard");
    }

    @Override
    protected void setupStartingItems() {
        inventory.add(new Item("Staff", "An elder wood staff used for casting spells.", 1, false, new ItemAction() {
            @Override
            public void execute() {
                System.out.println("A power surges through you as you equip the Staff!");
                increaseAttackPower(30);
            }
        }));
        inventory.add(new Item("Healing Potion", "A potion that heals 50 health.", 3, true, new ItemAction() {
            @Override
            public void execute() {
                System.out.println("Drinking the Healing Potion!");
                increaseHealth(50);
            }
        }));
    }

    @Override
    public void specialAction() {
        System.out.println(name + " starts casting doubling truble, doubling the power for the next hit!");
        isDoubled = true;
    }

    @Override
    public void attack(Enemy enemy) {
        int damage = isDoubled ? attackPower * 2 : attackPower;
        System.out.println(name + " attacks with " + damage + " power.");
        enemy.takeDamage(damage);
        isDoubled = false;
    }

    public void castSpell(String spell, Integer damage) {
        System.out.println(name + " casts " + spell + "!");
        System.out.println("Deals " + damage + " damage.");
    }

    public void increaseHealth(int amount) {
        int maxHealth = health;
        health += amount;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public void increaseAttackPower(int amount) {
        attackPower += amount;
    }
}