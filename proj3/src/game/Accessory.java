package game;

public class Accessory extends Item {
    Property addition;
    Accessory (String avatar, int id, String name, String description, Property addition) {
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
