package core;

import edu.princeton.cs.algs4.StdDraw;

import tileengine.TERenderer;

import java.awt.*;

public class LayerManager {
    public static long seed;
    public static int width;
    public static int height;

    public static UserInteraction ui;

    private Layer[] layers;
    public static Menu menuLayer;
    public static World worldLayer;
    public static Message messageLayer;
    public static HUD hudLayer;

    private static TERenderer ter;

    public LayerManager(int width, int height) {
        this.width = width;
        this.height = height;
        init();
    }

    public void init() {
        setDefaultFrame();

        menuLayer = new Menu(width, height, this);
        worldLayer = new World(width, height);
        messageLayer = new Message();
        hudLayer = new HUD();
        layers  = new Layer[]{worldLayer, hudLayer, messageLayer, menuLayer};

        ui = new UserInteraction(this);
        ter = new TERenderer(width, height);
    }

    public static void menu() { menuLayer.mainMenu(); }

    public void startGame() {
        worldLayer.startGame();
        hudLayer.makeVisible();
    }

    public void renderFrame() {
        StdDraw.clear(Color.WHITE);
        for (Layer l : layers) {
            if (l.visible) {
                l.render();
            }
        }
        StdDraw.show();
    }

    // helper functions
    public static void updateSeed(long value) {
        seed = value;
        System.out.println("Seed is updated");
    }

    public static void setDefaultFrame() {
        StdDraw.setCanvasSize(width * 16, (height + 3) * 16);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height + 3);
        StdDraw.clear(new Color(0, 0, 0));
        StdDraw.enableDoubleBuffering();
    }

    public static void setIsoFrame() {
        double maxX = (width + height) * 0.5; // Approx max x in isometric coords
        double maxY = (width + height) * 0.25 + 0.5; // Approx max y with wall height
        StdDraw.setXscale(0.5, maxX + 0.5); // Adjusted for left-aligned (0,0)
        StdDraw.setYscale(-0.25 - maxY * 0.25, maxY * 1.5); // Center vertically
    }
}
