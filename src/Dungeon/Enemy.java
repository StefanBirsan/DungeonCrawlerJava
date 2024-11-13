package Dungeon;

import player.Player;

public class Enemy extends Encounter implements Comparable<Enemy> {
    private String name;
    private int health;
    private int damage;
    private int gold;
    private String description;
    private boolean isBoss;
    private boolean isDead;
    private boolean messageShown;

    public Enemy(String encounterType, String encounterMessage, String name, int health, int damage, int gold, String description) {
        super(encounterType, encounterMessage);
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.gold = gold;
        this.description = description;
        this.messageShown = false;
    }

    @Override
    public int compareTo(Enemy other) {
        return Integer.compare(this.health, other.health);
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getAttack() {
        return damage;
    }

    public Integer getGold() {
        return gold;
    }
    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }

    public String getType() {
        return encounterType;
    }

    public Integer getDamage() {
        return damage;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    public void attack(Player player) {
        player.takeDamage(this.damage);
    }

    public String getEncounterMessage() {
        return encounterMessage;
    }

    public boolean isMessageShown() {
        return messageShown;
    }

    public void setMessageShown(boolean messageShown) {
        this.messageShown = messageShown;
    }

}