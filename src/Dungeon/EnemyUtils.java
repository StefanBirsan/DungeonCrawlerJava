package Dungeon;
import java.util.*;

public class EnemyUtils {
    public static void sortEnemiesByHealth(List<Enemy> enemies) {
        Collections.sort(enemies);
    }

    public static Map<String, List<Enemy>> groupEnemiesByHealthRange(List<Enemy> enemies) {
        Map<String, List<Enemy>> groupedEnemies = new HashMap<>();

        for (Enemy enemy : enemies) {
            String range = getHealthRange(enemy.getHealth());
            groupedEnemies.computeIfAbsent(range, k -> new ArrayList<>()).add(enemy);
        }

        return groupedEnemies;
    }

    private static String getHealthRange(int health) {
        if (health < 50) {
            return "Low";
        } else if (health < 100) {
            return "Medium";
        } else {
            return "High";
        }
    }
}

