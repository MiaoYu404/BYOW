package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.*;

import java.awt.event.KeyEvent;
import java.util.Random;

public class World extends Layer{
    public static final Coordinate[] dirs = {
            new Coordinate(0, 1), new Coordinate(1, 0),
            new Coordinate(0, -1), new Coordinate(-1, 0)};
    public static Random rand;
    public static Coordinate center;

    // properties
    public static int width;
    public static int height;
    public int mode = 0;

    // engine
    private TERenderer ter;
    private IsometricRender ir;
    private DungeonGenerator dg;

    // main process data
    public Player player;
    public static TETile[][] world = null;

    // constructor
    public World(int width, int height) {
        name = "World";
        this.width = width;
        this.height = height;
    }

    public static boolean isValid(int x, int y) {
        return  x >= 0 && x < width && y >= 0 && y < height;
    }

    public void init() {
        rand = new Random(LayerManager.seed);
        center = new Coordinate(width >> 1, height >> 1);

        ter = new TERenderer(width, height);
        ir = new IsometricRender(width, height);
        dg = new DungeonGenerator(width, height, LayerManager.seed);
    }

    public void startGame() {
        makeVisible();
        init();
        if (world == null) {
            generateWorld();
            setPlayer();
        }
    }

    public boolean input() {
        boolean ret = false;
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            if (mode == 0) ret |= player.move(0);
            else ret |= player.move(3);
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            if (mode == 0) ret |= player.move(2);
            else ret |= player.move(1);
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            if (mode == 0) ret |= player.move(3);
            else ret |= player.move(2);
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            if (mode == 0) ret |= player.move(1);
            else ret |= player.move(0);
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_T)) {
            ret |= changeMode(0);
            if (ret) LayerManager.setDefaultFrame();
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_I)) {
            ret |= changeMode(1);
            if (ret) LayerManager.setIsoFrame();
        } else if ((StdDraw.isKeyPressed(KeyEvent.VK_SEMICOLON) && StdDraw.isKeyPressed(KeyEvent.VK_SHIFT))
        || StdDraw.isKeyPressed(KeyEvent.VK_COLON)) {
            UserInteraction.exitRequest();
            UserInteraction.clearKeyTyped();
            return true;
        }
        return ret;
    }

    @Override
    public void render() {
        // TODO: rend world
        System.out.println("-- World start render --");
        switch (mode) {
            case 0:
                ter.renderFrame(world, player);
                break;

            case 1:
                ir.renderFrame(world, player);
                break;
        }

        System.out.println("-- World finish render --");
    }

    public void generateWorld() {
        world = dg.generateWorld();
        System.out.println(String.format("[%s] World is now generated.", System.currentTimeMillis()));
    }

    public Coordinate randomSpawnPoint() {
        Coordinate ret = null;
        while (!isValid(ret) || !tryMove(ret)) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            ret = new Coordinate(x, y);
        }
        return ret;
    }
    public boolean tryMove(Coordinate target) {
        if (!isValid(target)) {
            System.out.println("move failed since target is invalid.");
            return false;
        }
        int x = target.x,  y = target.y;
        // TODO: extend unreachable tiles
        if (world[x][y].equals(Tileset.FLOOR)) {
            return true;
        }
        return false;
    }

    public boolean changeMode(int newMode) {
        // TODO: BUG here. If you switch I and T so fast, it will fall into endless loop.
        if (mode == newMode) {
            System.out.println("Change mode to " + newMode + " fails since it is already done!");
            return false;
        }
        mode = newMode;
        return true;
    }

    // Helper Functions
    public static boolean isValid(Coordinate c) {
        if (c == null) return false;
        return (c.x >= 0) && (c.x < width) && (c.y >= 0) && (c.y < height);
    }

    public void clear() {
        world = null;
    }

    public void setWorld(TETile[][] world) {
        if (world == null) throw new IllegalArgumentException("world cannot be null");
        this.world = world;
        System.out.println("[World] World has been set.");
    }

    public void setPlayer() {
        setPlayer(randomSpawnPoint());
    }
    public void setPlayer(Coordinate c) {
        if (world == null) throw new IllegalArgumentException("world cannot be null");
        if (c == null) throw new IllegalArgumentException("coordinate cannot be null");
        this.player = new Player(c, this);
        System.out.println(String.format("Player is set at %s.", player.coordinate));
    }

    public static TETile getTile(int x, int y) { return world[x][y]; }
    public static TETile getTile(Coordinate c) { return getTile(c.x, c.y); }
}
