package Dungeon.Rooms;

public class Node {
    public Room room;
    Node left, right, parent;
    boolean color; // true for RED, false for BLACK

    public Node(Room room) {
        this.room = room;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.color = true; // New nodes are red by default
    }
}