package player;

public class Player {
    protected String name;
    protected int health;
    protected int experience;

    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.experience = 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }


    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public void heal(int healAmount) {
        this.health += healAmount;
    }

    public void gainExperience(int exp) {
        experience += exp;
        System.out.println(name + " gained " + exp + " experience.");
    }


}
