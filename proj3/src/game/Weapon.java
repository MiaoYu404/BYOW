package game;

import java.util.Random;

public class Weapon extends Item {
    Property addition;
    Random rand = new Random();

    Weapon (String avatar, int id, String name, String description, Property addition) {
        this.avatar = avatar;
        this.id = id;
        this.name = name;
        this.description = description;
        this.addition = addition;
    }

    void addAddition() {
        // TODO
    }

}
