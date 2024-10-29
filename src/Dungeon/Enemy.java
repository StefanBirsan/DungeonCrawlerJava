package Dungeon;

public class Enemy extends Encounter {
    private String name;
    private int health;
    private int damage;
    private int gold;

    public Enemy(String encounterType, String encounterMessage, String name, int health, int damage, int gold) {
        super(encounterType, encounterMessage);
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.gold = gold;
    }
}
