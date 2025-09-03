package game;

import tileengine.Coordinate;
import core.Layer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 录入已有怪兽，设置自己的角色
public class Figure {
    Property property;
    String avatar;
    Package packages;
    ArrayList<Integer> equipment;
    Coordinate location;
    Queue<Effect> effects;
    Layer message = new Layer();

    Figure(Property property, String avatar, Coordinate location) {
        this.property = property;
        this.avatar = avatar;
        this.packages = new Package(5);
        this.equipment = new ArrayList<>(1);
        this.location = location;
        this.effects = new LinkedList<>();
    }

    void addThings(Item item) {
        packages.addThings(item);
    }

    void throwThings(Item item) {
        packages.remove(item);
    }

    void equipWeapons(Integer id) {
        if (equipment.isEmpty()) {
            equipment.add(id);
        } else {
            equipment.removeFirst();
            equipment.add(id);
        }
    }

    void throwWeapons(Integer id) {
        equipment.remove(id);
    }

    void changeProperty() {
        // TODO
    }

    void drinkLuck(int id) {
        // TODO
    }

    void drinkBlood(int id) {
        // TODO
    }

}
