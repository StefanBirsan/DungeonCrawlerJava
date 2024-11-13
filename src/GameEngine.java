import Dungeon.Rooms.RedBlackTree;
import Dungeon.Rooms.Room;
import Dungeon.Rooms.RoomUtils;
import player.PlayerClass;
import player.Classes.Barbarian;
import player.Classes.Wizzard;
import Exception.RoomNotSetException;
import Exception.ItemNonExistent;
import item.Item;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Scanner;

public class GameEngine {
    private PlayerClass player;
    private Room currentRoom;
    private RedBlackTree roomTree;
    private Room previousRoom;
    private Map<Integer, String> roomMap;

    public GameEngine() {
        this.roomTree = new RedBlackTree();
        this.previousRoom = null;
        this.roomMap = new HashMap<>();
        loadRoomsFromFile("red_black_tree.txt");
    }

    private void loadRoomsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                int roomId = Integer.parseInt(parts[0].split(": ")[1]);
                String description = parts[1].split(": ")[1];
                boolean visited = line.endsWith("#");
                roomMap.put(roomId, description + (visited ? " #" : ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void markRoomAsVisited(int roomId) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("red_black_tree.txt"));
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith("Room ID: " + roomId + ",") && !lines.get(i).endsWith("#")) {
                    lines.set(i, lines.get(i) + " #");
                    break;
                }
            }
            Files.write(Paths.get("red_black_tree.txt"), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void action() {
        try {
            if (currentRoom == null) {
                throw new RoomNotSetException("Current room is not set.");
            }

            System.out.println("You are in a " + currentRoom.getDescription());
            System.out.println("=====================================");
            System.out.println("What shall be your next move?");
            System.out.println("=====================================");

            if (currentRoom.hasEnemy()) {
                System.out.println("An enemy is here! Choose an action:");
                System.out.println("1. Attack");
                System.out.println("2. Special Action");
                System.out.println("3. Show Inventory");
                System.out.println("4. Use item");
                System.out.println("5. Quit game");

                int choice = new Scanner(System.in).nextInt();
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
                        useItem();
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("Choose an action:");
                System.out.println("1. Move to next room");
                System.out.println("2. Move to previous room");
                System.out.println("3. Show Inventory");
                System.out.println("4. Use item");
                System.out.println("5. Quit game");

                int choice = new Scanner(System.in).nextInt();
                switch (choice) {
                    case 1:
                        moveToNextRoom();
                        break;
                    case 2:
                        moveToPreviousRoom();
                        break;
                    case 3:
                        player.showInventory();
                        break;
                    case 4:
                        useItem();
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } catch (RoomNotSetException e) {
            System.out.println(e.getMessage());
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + "Welcome to the dungeon !" + "\n" +
                "   _________________________________________________________\n" +
                " /|     -_-                                             _-  |\\\n" +
                "/ |_-_- _                                         -_- _-   -| \\   \n" +
                "  |                            _-  _--                      | \n" +
                "  |                            ,                            |\n" +
                "  |      .-'````````'.        '(`        .-'```````'-.      |\n" +
                "  |    .` |           `.      `)'      .` |           `.    |          \n" +
                "  |   /   |   ()        \\      U      /   |    ()       \\   |\n" +
                "  |  |    |    ;         | o   T   o |    |    ;         |  |\n" +
                "  |  |    |     ;        |  .  |  .  |    |    ;         |  |\n" +
                "  |  |    |     ;        |   . | .   |    |    ;         |  |\n" +
                "  |  |    |     ;        |    .|.    |    |    ;         |  |\n" +
                "  |  |    |____;_________|     |     |    |____;_________|  |  \n" +
                "  |  |   /  __ ;   -     |     !     |   /     `'() _ -  |  |\n" +
                "  |  |  / __  ()        -|        -  |  /  __--      -   |  |\n" +
                "  |  | /        __-- _   |   _- _ -  | /        __--_    |  |\n" +
                "  |__|/__________________|___________|/__________________|__|\n" +
                " /                                             _ -        lc \\\n" +
                "/   -_- _ -             _- _---                       -_-  -_ \\");

        System.out.println("=====================================");
        System.out.print("\nEnter your name: ");
        String playerName = scanner.nextLine();

        System.out.println("Choose your class by entering the number of the class:");
        System.out.println("1. Barbarian");
        System.out.println("2. Wizzard");
        System.out.println("=====================================");

        int classChoice = scanner.nextInt();
        scanner.nextLine();

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

        System.out.println("=====================================");
        System.out.println("Game started!");
        System.out.println(" _   _                    __             _ \n" +
                "| | | | __ ___   _____   / _|_   _ _ __ | |\n" +
                "| |_| |/ _` \\ \\ / / _ \\ | |_| | | | '_ \\| |\n" +
                "|  _  | (_| |\\ V /  __/ |  _| |_| | | | |_|\n" +
                "|_| |_|\\__,_| \\_/ \\___| |_|  \\__,_|_| |_(_)");
        System.out.println("=========================================");


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
        System.out.println("\nYou are in a " + currentRoom.getDescription());
        System.out.println("Choose a room description to move to:");

        int count = 0;
        for (String description : roomMap.values()) {
            if (!description.equals(currentRoom.getDescription())) {
                System.out.println(" - " + description.replace(" #", ""));
                count++;
                if (count == 2) break;
            }
        }

        System.out.print("\nEnter the room description: ");
        String roomDescription = scanner.nextLine();
        Integer roomId = null;

        for (Map.Entry<Integer, String> entry : roomMap.entrySet()) {
            if (entry.getValue().replace(" #", "").equalsIgnoreCase(roomDescription)) {
                roomId = entry.getKey();
                break;
            }
        }

        if (roomId != null) {
            previousRoom = currentRoom;
            currentRoom = new Room(roomId, roomDescription, false, false, null);
            System.out.println("\nMoved to room: " + currentRoom.getDescription());
            markRoomAsVisited(roomId);
        } else {
            System.out.println("\nInvalid room description.");
        }
    }

    private void moveToPreviousRoom() {
        if (previousRoom != null) {
            Room tempRoom = currentRoom;
            currentRoom = previousRoom;
            previousRoom = tempRoom;
            System.out.println("\nMoved back to room: " + currentRoom.getDescription());
        } else {
            System.out.println("\nNo previous room to move to.");
        }
    }

    private void useItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the item you want to use:");
        String itemName = scanner.nextLine();

        try {
            List<String> inventory = player.getInventory();
            String itemToUse = null;

            for (String itemNameInInventory : inventory) {
                if (itemNameInInventory.equalsIgnoreCase(itemName)) {
                    itemToUse = itemNameInInventory;
                    break;
                }
            }

            if (itemToUse == null) {
                throw new ItemNonExistent("Item does not exist in inventory.");
            }

            Item item = getItemByName(itemToUse);

            if (item.isConsumable()) {
                item.consume();
            } else {
                item.use();
            }
        } catch (ItemNonExistent e) {
            System.out.println(e.getMessage());
        }
    }

    private Item getItemByName(String itemName) {
        for (Item item : player.getInventoryItems()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
}