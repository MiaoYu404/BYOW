package game;

import java.util.Random;

public class LuckyWater extends Item implements Consumable {
        Random rand = new Random();
        int restChance;

    LuckyWater (String avatar, int id, String name, String description) {
            this.avatar = avatar;
            this.id = id;
            this.name = name;
            this.description = description;
        }
    }


