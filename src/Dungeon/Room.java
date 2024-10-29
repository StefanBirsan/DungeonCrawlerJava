package Dungeon;

public class Room {
    private int id;
    private String description;
    private boolean hasEnemy;
    private boolean hasTreasure;

    public Room(int id, String description, boolean hasEnemy, boolean hasTreasure) {
        this.id = id;
        this.description = description;
        this.hasEnemy = hasEnemy;
        this.hasTreasure = hasTreasure;
    }

}