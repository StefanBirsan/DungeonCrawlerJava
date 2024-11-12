import Dungeon.Rooms.RedBlackTree;
import Dungeon.Rooms.Room;
import Dungeon.Rooms.RoomUtils;
import player.PlayerClass;
import player.Classes.Barbarian;
import player.Classes.Wizzard;
import Exception.RoomNotSetException;

import java.util.Scanner;

public class GameEngine {
    private PlayerClass player;
    private Room currentRoom;
    private RedBlackTree roomTree;
    private Room previousRoom;

    public GameEngine() {
        this.roomTree = new RedBlackTree();
        this.previousRoom = null;
    }

    public void action() {
        try {
            if (currentRoom == null) {
                throw new RoomNotSetException("Current room is not set.");
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("You are in a " + currentRoom.getDescription());
            System.out.println("Choose an action:");
            System.out.println("1. Attack");
            System.out.println("2. Special Action");
            System.out.println("3. Show Inventory");
            System.out.println("4. Move to next room");
            System.out.println("5. Use item");
            System.out.println("6. Quit game");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    player.attack();
                    break;
                case 2:
                    player.specialAction();
                    break;
                case 3:
                    player.showInventory();
                    break;
                case 4:
                    moveToNextRoom();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (RoomNotSetException e) {
            System.out.println(e.getMessage());
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        // Get the player's name
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        // Choose the player's class
        System.out.println("Choose your class:");
        System.out.println("1. Barbarian");
        System.out.println("2. Wizzard");
        int classChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (classChoice) {
            case 1:
                player = new Barbarian(playerName);
                break;
            case 2:
                player = new Wizzard(playerName);
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Barbarian.");
                player = new Barbarian(playerName);
        }

        System.out.print("Enter the number of rooms: ");
        int numberOfRooms = scanner.nextInt();

        roomTree = RoomUtils.generateDungeon(numberOfRooms);

        currentRoom = roomTree.searchTree(1).room;

        System.out.println("Game started!");

        // Game loop
        while (player.getHealth() > 0) {
            action();
        }

        if (player.getHealth() <= 0) {
            System.out.println("Game over! You have been defeated.");
        } else {
            System.out.println("Congratulations! You have visited all rooms.");
        }
    }

    private void moveToNextRoom() throws RoomNotSetException {
        if (currentRoom == null) {
            throw new RoomNotSetException("Current room is not set.");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a direction to move:");
        if (roomTree.getLeftChild(currentRoom) != null) {
            System.out.println("1. Move to left room");
        }
        if (roomTree.getRightChild(currentRoom) != null) {
            System.out.println("2. Move to right room");
        }
        if (previousRoom != null) {
            System.out.println("3. Move south to return to the previous room");
        }

        int direction = scanner.nextInt();
        switch (direction) {
            case 1:
                if (roomTree.getLeftChild(currentRoom) != null) {
                    previousRoom = currentRoom;
                    currentRoom = roomTree.getLeftChild(currentRoom).room;
                    if (currentRoom != null) {
                        System.out.println("Moved to left room: " + currentRoom.getDescription());
                    } else {
                        System.out.println("No left room available.");
                    }
                } else {
                    System.out.println("No left room available.");
                }
                break;
            case 2:
                if (roomTree.getRightChild(currentRoom) != null) {
                    previousRoom = currentRoom;
                    currentRoom = roomTree.getRightChild(currentRoom).room;
                    if (currentRoom != null) {
                        System.out.println("Moved to right room: " + currentRoom.getDescription());
                    } else {
                        System.out.println("No right room available.");
                    }
                } else {
                    System.out.println("No right room available.");
                }
                break;
            case 3:
                if (previousRoom != null) {
                    Room temp = currentRoom;
                    currentRoom = previousRoom;
                    previousRoom = temp;
                    if (currentRoom != null) {
                        System.out.println("Moved to previous room: " + currentRoom.getDescription());
                    } else {
                        System.out.println("No previous room available.");
                    }
                } else {
                    System.out.println("No previous room available.");
                }
                break;
            default:
                System.out.println("Invalid direction.");
        }
    }
}