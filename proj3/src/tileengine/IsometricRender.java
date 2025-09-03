package tileengine;

import core.Player;
import edu.princeton.cs.algs4.StdDraw;

public class IsometricRender {
    private int width, height;
    private static final double isoScaleX = 0.5;
    private static final double isoScaleY = 0.25;
    private final double tileHeight = 0.2;

    private static double offsetX;
    private static double offsetY;

    public IsometricRender(int width, int height) {
        this.width = width;
        this.height = height;

        this.offsetX = 0.5;
        this.offsetY = height * isoScaleY - 0.5;
    }

    public void renderFrame(TETile[][] tiles) { renderFrame(tiles, null); }
    public void renderFrame(TETile[][] tiles, Player player) {
        for (int k = height - 1; k > height * (-1); k--) {
            for (int y = height - 1; y >= 0; y--) {
                int x = y - k;
                if (x < 0 || x >= width) {
                    continue;
                }
                // Modified isometric coordinates to place (0,0) at leftmost
                double[] c = t2i(x, y);
                double isoX = c[0], isoY = c[1];

                if (tiles[x][y].equals(Tileset.WALL)) {
                    StdDraw.setPenColor(StdDraw.DARK_GRAY);
                    double[] xRight = {isoX + isoScaleX, isoX + 2 * isoScaleX, isoX + 2 * isoScaleX, isoX + isoScaleX};
                    double[] yRight = {isoY - isoScaleY + tileHeight, isoY + tileHeight, isoY, isoY - isoScaleY};
                    StdDraw.filledPolygon(xRight, yRight);

                    StdDraw.setPenColor(StdDraw.DARK_GRAY);
                    double[] xLeft = {isoX, isoX + isoScaleX, isoX + isoScaleX, isoX};
                    double[] yLeft = {isoY + tileHeight, isoY - isoScaleY + tileHeight, isoY - isoScaleY, isoY};
                    StdDraw.filledPolygon(xLeft, yLeft);

                    StdDraw.setPenColor(StdDraw.GRAY);
                    double[] xTop = {isoX, isoX + isoScaleX, isoX + 2 * isoScaleX, isoX + isoScaleX};
                    double[] yTop = {isoY + tileHeight, isoY + isoScaleY + tileHeight, isoY + tileHeight, isoY - isoScaleY + tileHeight};
                    StdDraw.filledPolygon(xTop, yTop);
                } else {
                    // Draw floor
                    if (tiles[x][y].equals(Tileset.FLOOR)) {
                        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    } else if (tiles[x][y].equals(Tileset.FLOWER)) {
                        StdDraw.setPenColor(StdDraw.PINK);
                    } else {
                        StdDraw.setPenColor(StdDraw.WHITE);
                    }
                    double[] xPoints = {isoX, isoX + isoScaleX, isoX + 2 * isoScaleX, isoX + isoScaleX};
                    double[] yPoints = {isoY, isoY + isoScaleY, isoY, isoY - isoScaleY};
                    StdDraw.filledPolygon(xPoints, yPoints);
                }

                if (player == null || !player.coordinate.equals(new Coordinate(x, y))) continue;
                player.draw(isoX, isoY);
            }
        }
        StdDraw.show();
    }

    public static double[] t2i(int x, int y) { return t2i(new Coordinate(x, y)); }
    public static double[] t2i(Coordinate c) {
        int x = c.x, y = c.y;
        double isoX = (x + y) * isoScaleX + offsetX;
        double isoY = (1 + (-1) * (x - y)) * isoScaleY + offsetY;
        return new double[]{isoX, isoY};
    }
}