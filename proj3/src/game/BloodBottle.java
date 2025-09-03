package game;

import java.util.Random;

public class BloodBottle extends Item implements Consumable {
    Random rand = new Random();
    int maxAddition;

    BloodBottle (String avatar, int id, String name, String description, int maxAddition) {
        this.avatar = avatar;
        this.id = id;
        this.name = name;
        this.description = description;
        this.maxAddition = maxAddition;
    }
}
