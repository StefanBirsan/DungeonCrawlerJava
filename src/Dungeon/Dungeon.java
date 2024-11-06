package Dungeon;

import Dungeon.Rooms.RedBlackTree;
import Dungeon.Rooms.Room;
import Dungeon.Rooms.RoomUtils;

import java.util.Scanner;

public class Dungeon {
    private RedBlackTree roomTree;

    public Dungeon(int numberOfRooms) {
        this.roomTree = RoomUtils.generateDungeon(numberOfRooms);
    }

    public void displayDungeon() {
        System.out.println("In-order traversal of the dungeon rooms: ");
        roomTree.inOrder();
        System.out.println();
    }

    public Room searchRoom(int roomId) {
        return roomTree.searchTree(roomId).room;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rooms to generate: ");
        int numberOfRooms = scanner.nextInt();

        Dungeon dungeon = new Dungeon(numberOfRooms);
        dungeon.displayDungeon();

        System.out.print("Enter the room ID to search: ");
        int roomIdToSearch = scanner.nextInt();

        Room room = dungeon.searchRoom(roomIdToSearch);
        if (room != null) {
            System.out.println("Room found: " + room.getDescription());
        } else {
            System.out.println("Room not found.");
        }

        scanner.close();
    }
}