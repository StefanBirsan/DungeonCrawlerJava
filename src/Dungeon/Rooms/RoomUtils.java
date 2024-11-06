package Dungeon.Rooms;

import Dungeon.Encounter;
import Dungeon.Enemy;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RoomUtils {
    private static final String[] ROOM_DESCRIPTIONS = {
            "Hallway", "Treasure Room", "Boss Room", "Library", "Armory", "Dining Room"
    };

    public static void sortRoomsById(List<Room> rooms) {
        Collections.sort(rooms);
    }

    public static RedBlackTree generateDungeon(int numberOfRooms) {
        RedBlackTree tree = new RedBlackTree();
        Random random = new Random();

        // Insert the Entrance room
        tree.insert(new Room(1, "Entrance", false, false, null));

        // Generate and insert the rest of the rooms
        for (int i = 1; i <= numberOfRooms; i++) {
            String description = ROOM_DESCRIPTIONS[random.nextInt(ROOM_DESCRIPTIONS.length)];
            boolean hasEnemy = random.nextBoolean();
            boolean hasTreasure = random.nextBoolean();
            Encounter encounter = null;

            if (hasEnemy) {
                encounter = new Enemy("Enemy", "A wild enemy appears!", "Goblin", 50, 10, 5);
            }

            tree.insert(new Room(i, description, hasEnemy, hasTreasure, encounter));
        }

        return tree;
    }
}