package Dungeon;

import Interfaces.Interactable;

public class Encounter implements Interactable {

    String encounterMessage;
    String encounterType;

    public Encounter(String encounterType, String encounterMessage) {
        this.encounterType = encounterType;
        this.encounterMessage = encounterMessage;
    }

    public void interact() {
        switch (encounterType) {
            case "Enemy":
                System.out.println("You've encountered an enemy! " + encounterMessage);
                break;
            case "Treasure":
                System.out.println("You've found a treasure! " + encounterMessage);
                break;
            case "Trap":
                System.out.println("You've triggered a trap! " + encounterMessage);
                break;
            default:
                System.out.println("You've encountered something unexpected! " + encounterMessage);
                break;
        }
    }
}