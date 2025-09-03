package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;

import java.awt.*;

import static core.World.center;
import static core.LayerManager.width;
import static core.LayerManager.height;

public class HUD extends Layer{
    TETile tile;

    public HUD() {
        name = "HUD";
        tile = null;
    }

    @Override
    public void render() {
        System.out.println("-- HUD start render --");
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.filledRectangle(center.x, LayerManager.height + 1.5, (double) LayerManager.width / 2, 1.5);
        if (tile != null) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.text(center.x, LayerManager.height + 1.5, tile.description());
        }
        System.out.println("-- HUD finish render --");
    }

    public boolean input() {
        int x = (int) Math.floor(StdDraw.mouseX()), y = (int) Math.floor(StdDraw.mouseY());
        if (!World.isValid(x, y)) return false;

        TETile tmp = World.getTile(x, y);
        if (!tmp.equals(tile)) {
            tile = tmp;
            return true;
        }
        return false;
    }
}
