package Dungeon;

public class Encounter implements Interactable {

    String encounterMessage;
    String encounterType;

    public Encounter(String encounterType, String encounterMessage) {
        this.encounterType = encounterType;
        this.encounterMessage = encounterMessage;
    }

    public void interact() {
        System.out.println("You've encountered a monster!");
    }

}
