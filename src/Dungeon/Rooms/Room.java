package Dungeon.Rooms;

import Dungeon.Enemy;

public class Room {
    private int id;
    private String description;
    private boolean hasEnemy;
    private boolean hasTreasure;
    private Enemy encounter;

    public Room(int id, String description, boolean hasEnemy, boolean hasTreasure, Enemy encounter) {
        this.id = id;
        this.description = description;
        this.hasEnemy = hasEnemy;
        this.hasTreasure = hasTreasure;
        this.encounter = encounter;
        if (hasEnemy) {
            if ("Boss Room".equals(description)) {
                this.encounter = new Enemy("Boss", "A mighty boss appears!\" +\n" +
                        "                        \"                            /|               |\\\\                              \\n\" +\n" +
                        "                        \"                           / | ___-------___ | \\\\                             \\n\" +\n" +
                        "                        \"                          /  \\\\/ ^ /\\\\   /\\\\ ^ \\\\/  \\\\                            \\n\" +\n" +
                        "                        \"                         |   (  O-. \\\\ / .-O  )   |                           \\n\" +\n" +
                        "                        \"                      /-\\\\/   ^-----^-V-^-----^   \\\\/-\\\\                        \\n\" +\n" +
                        "                        \"                    /-      (~ 0O0 ~) (~ 000 ~)     -\\\\                       \\n\" +\n" +
                        "                        \"                   <        (~ OOO ~) (~ 000 ~)       >                      \\n\" +\n" +
                        "                        \"                   \\\\-      (____---===---____)     -/                       \\n\" +\n" +
                        "                        \"                   \\\\-   /\\\\ \\\\ \\\\|         |/ / /\\\\  -/                        \\n\" +\n" +
                        "                        \"                    -/\\\\-/  \\\\ \\\\ V         V / /  \\\\-/\\\\-                       \\n\" +\n" +
                        "                        \"                        v    \\\\ \\\\           / /    v                          \\n\" +\n" +
                        "                        \"                              \\\\ \\\\ A     A / /                                \\n\" +\n" +
                        "                        \"                               \\\\_\\\\^-----^/_/                                 \\n\" +\n" +
                        "                        \"                                \\\\_/\\\\___/\\\\_/                                  \\n\" +\n" +
                        "                        \"                                  \\\\_____/\"", "Dragon", 200, 30, 15, "A large fire breathing dragon.");
            } else {
                this.encounter = new Enemy("Enemy", "A wild enemy appears!" +
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
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀", "Goblin", 50, 15, 5, "A small green creature.");
            }
        }
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

    public Enemy getEnemy() {
        return encounter;
    }
}