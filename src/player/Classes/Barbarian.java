package player.Classes;

import Dungeon.Enemy;
import player.PlayerClass;
import item.Item;
import Interfaces.ItemAction;

public class Barbarian extends PlayerClass {
    private boolean isRaging;

    public Barbarian(String name) {
        super(name, 150, 10, 30);
        this.isRaging = false;
    }

    @Override
    protected void setupStartingItems() {
        inventory.add(new Item("Battle Axe", "A large double axe used for battle.", 1, false, new ItemAction() {
            @Override
            public void execute() {
                System.out.println("Equiped the axe!");
                increaseAttackPower(25);
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
        System.out.println(name + " uses Rage, doubling attack power for the next hit!");
        isRaging = true;
    }

    @Override
    public void attack(Enemy enemy) {
        int damage = isRaging ? attackPower * 2 : attackPower;
        System.out.println(name + " attacks with " + damage + " power.");
        enemy.takeDamage(damage);
        isRaging = false;
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