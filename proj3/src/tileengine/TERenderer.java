package tileengine;

import core.Layer;
import core.LayerManager;
import core.Player;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

/**
 * Utility class for rendering tiles. You do not need to modify this file. You're welcome
 * to, but be careful. We strongly recommend getting everything else working before
 * messing with this renderer, unless you're trying to do something fancy like
 * allowing scrolling of the screen or tracking the avatar or something similar.
 */
public class TERenderer{
    public static final int TILE_SIZE = 16;

    private int width;
    private int height;
    private int xOffset;
    private int yOffset;

    private int mode;

    public TERenderer(int width, int height) { initialize(width, height); }

    public void initialize(int w, int h) {
        initialize(w, h, 0, 0, 0);
    }
    public void initialize(int w, int h, int xOff, int yOff, int mode) {
        this.width = w;
        this.height = h;
        this.xOffset = xOff;
        this.yOffset = yOff;
        this.mode = mode;
    }

    public void renderFrame(TETile[][] world) {
        renderFrame(world, (Player) null);
    }

    public void renderFrame(TETile[][] world, Player player) {
        StdDraw.clear(StdDraw.WHITE);
        resetFont();
        switch (mode) {
            case 0:
                drawTiles(world, player);
                break;

            case 1:
                break;
        }
        System.out.println("Frame rendered.");
    }

    public void renderFrame(TETile[][] world, Player[] players) {}

    public void drawTiles(TETile[][] world, Player player) {
        if (world == null) { return ; }
        int numXTiles = world.length;
        int numYTiles = world[0].length;
        drawTiles(world, numXTiles, numYTiles, player);
    }

    public void drawTiles(TETile[][] world, int width, int height) {
        drawTiles(world, width, height, null);
    }
    public void drawTiles(TETile[][] world, int width, int height, Player player) {
        if (world == null || world.length == 0) { return ; }
        int numXTiles = width;
        int numYTiles = height;
        for (int x = 0; x < numXTiles; x += 1) {
            if (world[x] == null) { continue; }
            for (int y = 0; y < numYTiles; y += 1) {
                if (world[x][y] == null) {
                    throw new IllegalArgumentException("Tile at position x=" + x + ", y=" + y + " is null.");
                }
                world[x][y].draw(x + xOffset, y + yOffset);

                if (player == null || !player.coordinate.equals(new Coordinate(x, y))) continue;
                player.draw(x + xOffset, y + yOffset);
            }
        }
    }

    // helper functions
    public void resetFont() {
        Font font = new Font("Monaco", Font.BOLD, TILE_SIZE - 2);
        StdDraw.setFont(font);
    }

    public boolean isValid(Coordinate c) { return (c.x < 0) || (c.x >= width) || (c.y < 0) || (c.y >= height); }
}
