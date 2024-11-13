package Dungeon.Rooms;

import Dungeon.Encounter;
import Dungeon.Enemy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RoomUtils {
    private static final String[] ROOM_DESCRIPTIONS = {
            "Hallway", "Treasure Room", "Boss Room", "Library", "Armory",
    };

    public static void sortRoomsById(List<Room> rooms) {
        Collections.sort(rooms);
    }

    public static RedBlackTree generateDungeon(int numberOfRooms) {
        RedBlackTree tree = new RedBlackTree();
        Random random = new Random();

        List<Integer> roomIds = new ArrayList<>();
        for (int i = 1; i <= numberOfRooms; i++) {
            roomIds.add(i);
        }
        Collections.shuffle(roomIds);

        tree.insert(new Room(1, "Entrance", false, false, null));

        for (int i = 1; i < numberOfRooms; i++) {
            String description = ROOM_DESCRIPTIONS[random.nextInt(ROOM_DESCRIPTIONS.length)];
            boolean hasEnemy = random.nextBoolean();
            boolean hasTreasure = random.nextBoolean();
            Encounter encounter = null;

            if ("Boss Room".equals(description)) {
                hasEnemy = true;
                encounter = new Enemy("Boss", "A mighty boss appears!", "Dragon", 200, 30, 15);
            } else if (hasEnemy) {
                encounter = new Enemy("Enemy", "A wild enemy appears!", "Goblin", 50, 15, 5);
            }

            tree.insert(new Room(roomIds.get(i), description, hasEnemy, hasTreasure, encounter));
        }

        tree.writeTreeToFile("red_black_tree.txt");
        return tree;
    }
}