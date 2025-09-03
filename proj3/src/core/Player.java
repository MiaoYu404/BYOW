package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.Coordinate;
import tileengine.TETile;
import tileengine.Tileset;


public class Player extends Layer{
    // properties
    String filePath;
    TETile avatar;

    // main process data
    public Coordinate coordinate;
    World world;

    // Constructors
    public Player(Coordinate coordinate, World world) {
        this.coordinate = coordinate;
        this.filePath = "proj3/src/assets/images/cat.png";
        this.world = world;
        this.avatar = Tileset.AVATAR;
    }

    public Player(Coordinate coordinate, TETile avatar, World world) {
        this(coordinate, world);
        this.avatar = avatar;
    }
    public Player(Coordinate coordinate, TETile avatar, World world, String filePath) {
        this(coordinate, avatar, world);
        this.filePath = filePath;
    }

    public boolean move(int direction) {
        if (direction < 0 || direction > 3) { throw new IllegalArgumentException("Invalid direction"); }
        System.out.println(String.format("'move' is called, direction: %d", direction));
        int x = coordinate.x,  y = coordinate.y;
        Coordinate newC = coordinate.plus(World.dirs[direction]);
        if (!world.tryMove(newC)) { return false; }
        coordinate = newC;
        System.out.println("'move' is done.");
        return true;
    }

    public void draw(int x, int y) { draw((double) x, (double) y); }
    public void draw(double x, double y) {
        if (filePath != null) {
            try {
                StdDraw.picture(x + 0.5, y + 0.5, filePath, 2, 2);
                System.out.println(String.format("Player is now at (%.2f, %.2f).", x, y));
                return ;
            } catch (IllegalArgumentException e) {
                // nothing here
            }
        }
        avatar.draw(x, y);
        System.out.println(String.format("Player is now at (%.2f, %.2f).", x, y));
    }
}
