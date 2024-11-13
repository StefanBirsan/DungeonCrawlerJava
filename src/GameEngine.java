import Dungeon.Enemy;
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
    private Map<Integer, Enemy> roomEnemies = new HashMap<>();

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
                Enemy enemy = currentRoom.getEnemy();
                if (!enemy.isMessageShown()) {
                    System.out.println(enemy.getEncounterMessage());
                    enemy.setMessageShown(true);
                }

                System.out.println("An enemy is here! Choose an action:");
                System.out.println("1. Attack");
                System.out.println("2. Special Action");
                System.out.println("3. Show Inventory");
                System.out.println("4. Use item");
                System.out.println("5. Show Enemy Info");
                System.out.println("6. Quit game");

                int choice = new Scanner(System.in).nextInt();
                switch (choice) {
                    case 1:
                        player.attack(currentRoom.getEnemy());
                        if (currentRoom.getEnemy().isDead()) {
                            System.out.println("You have defeated the enemy!");
                            moveToNextRoom();
                        } else {
                            currentRoom.getEnemy().attack(player);
                            if (player.getHealth() <= 0) {
                                System.out.println("You have been defeated by the enemy!");
                                System.exit(0);
                            }
                        }
                        playerTakeDamage(player);
                        break;
                    case 2:
                        player.specialAction();
                        playerTakeDamage(player);
                        break;
                    case 3:
                        player.showInventory();
                        break;
                    case 4:
                        useItem();
                        break;
                    case 5:
                        showEnemyInfo();
                        playerTakeDamage(player);
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("\nChoose an action:");
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
                    case 5:
                        System.exit(0);
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

        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("red_black_tree.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int currentIndex = -1;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains("Room ID: " + currentRoom.getId() + ",")) {
                currentIndex = i;
                break;
            }
        }

        if (currentIndex == -1) {
            System.out.println("Current room not found in file.");
            return;
        }

        int nextRoomIndex1 = currentIndex + 1;
        int nextRoomIndex2 = currentIndex + 2;

        if (nextRoomIndex1 >= lines.size() || nextRoomIndex2 >= lines.size()) {
            System.out.println("No more rooms to move to.");
            return;
        }

        String nextRoomDescription1 = lines.get(nextRoomIndex1).split(", ")[1].split(": ")[1];
        String nextRoomDescription2 = lines.get(nextRoomIndex2).split(", ")[1].split(": ")[1];

        System.out.println("1. " + nextRoomDescription1);
        System.out.println("2. " + nextRoomDescription2);

        int choice = scanner.nextInt();
        scanner.nextLine();

        int chosenRoomIndex = (choice == 1) ? nextRoomIndex1 : nextRoomIndex2;
        String chosenRoomLine = lines.get(chosenRoomIndex);
        int chosenRoomId = Integer.parseInt(chosenRoomLine.split(", ")[0].split(": ")[1]);
        String chosenRoomDescription = chosenRoomLine.split(", ")[1].split(": ")[1];
        boolean hasEnemy = Boolean.parseBoolean(chosenRoomLine.split(", ")[2].split(": ")[1]);

        Enemy enemy = null;
        if (hasEnemy) {
            if ("Boss Room".equals(chosenRoomDescription)) {
                enemy = new Enemy("Boss", "A mighty boss appears!" +
                        "                            /|               |\\                              \n" +
                        "                           / | ___-------___ | \\                             \n" +
                        "                          /  \\/ ^ /\\   /\\ ^ \\/  \\                            \n" +
                        "                         |   (  O-. \\ / .-O  )   |                           \n" +
                        "                      /-\\/   ^-----^-V-^-----^   \\/-\\                        \n" +
                        "                    /-      (~ 0O0 ~) (~ 000 ~)     -\\                       \n" +
                        "                   <        (~ OOO ~) (~ 000 ~)       >                      \n" +
                        "                   \\-      (____---===---____)     -/                       \n" +
                        "                   \\-   /\\ \\ \\|         |/ / /\\  -/                        \n" +
                        "                    -/\\-/  \\ \\ V         V / /  \\-/\\-                       \n" +
                        "                        v    \\ \\           / /    v                          \n" +
                        "                              \\ \\ A     A / /                                \n" +
                        "                               \\_\\^-----^/_/                                 \n" +
                        "                                \\_/\\___/\\_/                                  \n" +
                        "                                  \\_____/", "Dragon", 200, 30, 75, "A mighty dragon that dungeon.");
            } else {
                enemy = new Enemy("Enemy", "A wild enemy appears!" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⣿⣿⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⢀⡼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢧⡀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠢⣤⣀⡀⠀⠀⠀⢿⣧⣄⡉⠻⢿⣿⣿⡿⠟⢉⣠⣼⡿⠀⠀⠀⠀⣀⣤⠔⠀\n" +
                        "⠀⠀⠈⢻⣿⣶⠀⣷⠀⠉⠛⠿⠶⡴⢿⡿⢦⠶⠿⠛⠉⠀⣾⠀⣶⣿⡟⠁⠀⠀\n" +
                        "⠀⠀⠀⠀⠻⣿⡆⠘⡇⠘⠷⠠⠦⠀⣾⣷⠀⠴⠄⠾⠃⢸⠃⢰⣿⠟⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠋⢠⣾⣥⣴⣶⣶⣆⠘⣿⣿⠃⣰⣶⣶⣦⣬⣷⡄⠙⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⢋⠛⠻⠿⣿⠟⢹⣆⠸⠇⣰⡏⠻⣿⠿⠟⠛⡙⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠈⢧⡀⠠⠄⠀⠈⠛⠀⠀⠛⠁⠀⠠⠄⢀⡼⠁⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠈⢻⣦⡀⠃⠀⣿⡆⢰⣿⠀⠘⢀⣴⡟⠁⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣿⣦⡀⠘⠇⠸⠃⢀⣴⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⣷⣄⣠⣾⣿⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⣿⣿⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀", "Goblin", 50, 15, 5, "A wild goblin appears in the dungeon.");
            }
        }

        previousRoom = currentRoom;
        currentRoom = new Room(chosenRoomId, chosenRoomDescription, hasEnemy, false, enemy);
        System.out.println("\nMoved to room: " + currentRoom.getDescription());
        markRoomAsVisited(chosenRoomId);
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

    private void showEnemyInfo() {
        if (currentRoom.getEnemy() != null) {
            Enemy enemy = currentRoom.getEnemy();
            System.out.println("Enemy name: " + enemy.getName());
            System.out.println("Enemy description: " + enemy.getDescription());
            System.out.println("Enemy type: " + enemy.getType());
            System.out.println("Enemy health: " + enemy.getHealth());
            System.out.println("Enemy attack: " + enemy.getAttack());
        } else {
            System.out.println("There is no enemy in this room.");
        }
    }

    private void playerTakeDamage(PlayerClass player) {
        int damage = currentRoom.getEnemy().getDamage();
        currentRoom.getEnemy().attack(player);
        System.out.println("You took " + damage + " damage.");
        if (player.getHealth() <= 0) {
            System.out.println("You have been defeated!");
            System.exit(0);
        }
    }
}