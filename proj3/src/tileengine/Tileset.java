package tileengine;

import java.awt.Color;
import java.util.HashMap;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 *
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 *
 * Ex:
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 */

public class Tileset {
    public static final TETile AVATAR = new TETile('@', Color.white, Color.black, "you", 0);

    public static final TETile WALL = new TETile(
            '#', new Color(216, 128, 128), Color.darkGray, "wall", "proj3/src/assets/wall.png", 1);
    public static final TETile WALL_ORANGE = new TETile(
            '#', new Color(216, 128, 128), Color.orange, "wall",31);
    public static final TETile WALL_BLUE = new TETile(
            '#', new Color(216, 128, 128), Color.blue, "wall",32);
    public static final TETile WALL_PINK = new TETile(
            '#', new Color(216, 128, 128), Color.pink, "wall",33);

    public static final TETile FLOOR = new TETile(
            '·', new Color(128, 192, 128), Color.black, "floor", "proj3/src/assets/floor.png",2);
    public static final TETile VOID = new TETile(
            ' ', Color.black, Color.white, "nothing", "proj3/src/assets/void.png", 3);
    public static final TETile GRASS = new TETile(
            '"', Color.green, Color.black, "grass", 4);
    public static final TETile WATER = new TETile(
            '≈', Color.white, Color.blue, "water", 5);
    public static final TETile FLOWER = new TETile(
            '❀', Color.magenta, Color.pink, "flower", 6);
    public static final TETile LOCKED_DOOR = new TETile(
            '█', Color.orange, Color.black, "locked door", 7);
    public static final TETile UNLOCKED_DOOR = new TETile(
            '▢', Color.orange, Color.black, "unlocked door", 8);
    public static final TETile SAND = new TETile(
            '▒', Color.yellow, Color.black, "sand", 9);
    public static final TETile MOUNTAIN = new TETile(
            '⛰', Color.gray, Color.lightGray, "mountain", 10);
    public static final TETile TREE = new TETile(
            '♠', Color.green, Color.black, "tree", 11);

    public static final TETile CELL = new TETile('█', Color.white, Color.black, "cell", 12);
    public static final TETile NUMBER1 = new TETile('1', Color.white, Color.black, "number 1", 21);
    public static final TETile NUMBER2 = new TETile('2', Color.white, Color.black, "number 2", 22);
    public static final TETile NUMBER3 = new TETile('3', Color.white, Color.black, "number 3", 23);
    public static final TETile NUMBER4 = new TETile('4', Color.white, Color.black, "number 4", 24);

    public static HashMap<Integer, TETile> id2tile;
    public static void init() {
        id2tile = new HashMap<>();
        id2tile.put(0, AVATAR);
        id2tile.put(1, WALL);
        id2tile.put(2, FLOOR);
        id2tile.put(3, VOID);
        id2tile.put(4, GRASS);
        id2tile.put(5, WATER);
        id2tile.put(6, FLOWER);
        id2tile.put(7, LOCKED_DOOR);
        id2tile.put(8, UNLOCKED_DOOR);
        id2tile.put(9, SAND);
        id2tile.put(10, MOUNTAIN);
        id2tile.put(11, TREE);
        id2tile.put(12, CELL);
        id2tile.put(21, NUMBER1);
        id2tile.put(22, NUMBER2);
        id2tile.put(23, NUMBER3);
        id2tile.put(24, NUMBER4);
        id2tile.put(31, WALL_ORANGE);
        id2tile.put(32, WALL_BLUE);
        id2tile.put(33, WALL_PINK);
    }
}


