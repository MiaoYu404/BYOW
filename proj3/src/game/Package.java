package game;

import core.Message;
import tileengine.Coordinate;

import java.util.ArrayList;

public class Package {
    Coordinate location;
    ArrayList<Item> containsWhat;
    int Volume;
    Message message;

    Package(int Volume) {
        containsWhat = new ArrayList<>(5);
    }

    int size() {
        return containsWhat.size();
    }

    boolean contains(Item item) {
        return containsWhat.contains(item);
    }

    void addThings(Item item) {
        if (this.size() < 5) {
            containsWhat.add(item);
        } else {
            message.add("The backage is full. Please throw something.");
        }
    }
    void remove(Item item) {
        if (containsWhat.contains(item)) containsWhat.remove(item);
        else {
            message.add("ERROR!");
        }
    }
    Boolean isOverWeighted() {
        return containsWhat.size() >= Volume;
    }

}
