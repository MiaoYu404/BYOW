package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    // List of orders
    // TETile[][]: rend world
    List<Object> orders;

    // parameters
    public String name;
    public boolean visible;
    public double transparency;
    public double xOffset;
    public double yOffset;

    int page;

    // basic Layer constructor and more here
    public Layer() {
        // TODO: null parameter constructor
        init(false, 100.0, 0.0, 0.0);
    }

    public void init(boolean visiable, double transparency, double xOffset, double yOffset) {
        // TODO: initialize member variables here
        this.visible = visiable;
        this.transparency = transparency;
        this.xOffset = xOffset;
        this.yOffset = yOffset;

        this.orders = new ArrayList<>();
    }

    public void render() {
        if (!visible) return ;

        for (Object o : orders) {
            switch (o) {
                case String s:
                    break;
                default:
                    throw new IllegalArgumentException("unsupported order");
            }
        }
    }

    // add new order
    public void add(TETile[][] tiles) {
        // TODO: add new order into order List
        orders.add(tiles);
    }

    public void add(Object o) {
        orders.add(o);
    }

    // remove order
    public void remove(int index) { orders.remove(index); }

    // list exist orders
    public void list() {
        // TODO: list orders in order List
        for (Object o : orders) {
            switch (o) {
                case TETile[][] tiles:
                    System.out.println("tiles with size of " + tiles.length + " * " + tiles[0].length);
                    break;
                default:
                    throw new IllegalArgumentException("unsupported order type" + o.toString());
            }
        }
    }

    //
    public void move(int oldIndex, int newIndex) {
        // TODO: move a element from oldIndex to new Index;
        Object tmp = orders.remove(oldIndex);
        orders.add(newIndex, tmp);
    }

    public int size() { return orders.size(); }

    // Helper functions
    public void makeInvisible() {
        if (!visible) {
            System.out.println(String.format("Layer [%s] does not change since it was invisible.", name));
        }
        visible = false;
        System.out.println(String.format("Layer [%s] is now invisible", name));
    }
    public void makeVisible() {
        if (visible) {
            System.out.println(String.format("Layer [%s] does not change since it was visible.", name));
        }
        visible = true;
        System.out.println(String.format("Layer [%s] is now visible", name));
    }
}
