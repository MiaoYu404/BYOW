package game;

import core.Layer;

public class Property {
    int points = 10;
    int strength;
    int agility;  // Dodge
    int defence;
    int perception;
    int health;
    int healthLimit = 10;
    Layer message = new Layer();

    Property (int strength, int agility, int defence,  int perception, int healthLimitAdd) {
        this.strength = strength;
        this.agility = agility;
        this.defence = defence;
        this.perception = perception;
        this.healthLimit += healthLimitAdd;
        this.health = 10;
        if (strength + agility + defence + perception + healthLimitAdd < points) {
            message.add("There are still points left.");
        }
    }

    void changeStrength(int changeValue) {
        this.strength += changeValue;
    }
    void changeAgility(int changeValue) {
        this.agility += changeValue;
    }
    void changeDefence(int changeValue) {
        this.defence += changeValue;
    }
    void changePerception(int changeValue) {
        this.perception += changeValue;
    }
    void changeHealth(int changeValue) {
        if (this.health + changeValue > healthLimit) {
            this.health = healthLimit;
        } else {
            this.health += changeValue;
            if (health < 0) {
                message.add("You lose.");
            }
        }
    }
    void changeHealthLimit(int changeValue) {
        this.healthLimit += changeValue;
    }


}
