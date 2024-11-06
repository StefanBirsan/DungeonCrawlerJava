package Dungeon.Rooms;

import Dungeon.Encounter;

public class Room implements Comparable<Room> {
    private int id;
    private String description;
    private boolean hasEnemy;
    private boolean hasTreasure;
    private Encounter encounter;

    public Room(int id, String description, boolean hasEnemy, boolean hasTreasure, Encounter encounter) {
        this.id = id;
        this.description = description;
        this.hasEnemy = hasEnemy;
        this.hasTreasure = hasTreasure;
        this.encounter = encounter;
    }

    @Override
    public int compareTo(Room other) {
        return Integer.compare(this.id, other.id);
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean hasEnemy() {
        return hasEnemy;
    }

    public boolean hasTreasure() {
        return hasTreasure;
    }

    public Encounter getEncounter() {
        return encounter;
    }
}