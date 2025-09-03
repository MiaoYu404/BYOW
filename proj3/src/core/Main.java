package core;

public class Main {
    public static final long[] seeds = new long[]{ 5068527438788806346L,
            1685622556882347430L, 2517102648739503568L,
            7076918456032142437L, 5368466833381451274L };

    public static long seed = seeds[1];
    public static final int WORLD_WIDTH = 50;
    public static final int WORLD_HEIGHT = 50;

    // use after fit with world size;
    public static final int WINDOW_WIDTH = 80 * 16;
    public static final int WINDOW_HEIGHT = 60 * 16;

    public static void main(String[] args) {
        LayerManager lm = new LayerManager(WORLD_WIDTH, WORLD_HEIGHT);

        System.out.println("preparing...");
        tileengine.Tileset.init();
        lm.menu();
        lm.ui.run().start();

//        DungeonGenerator generator = new DungeonGenerator(WORLD_WIDTH, WORLD_HEIGHT, seed);
//        TETile[][] world = generator.generateWorld();
//
//        TERenderer ter = new TERenderer();
//        ter.initialize(WORLD_WIDTH, WORLD_HEIGHT);
//        ter.renderFrame(world);
//
//        System.out.println("Coverage: " + generator.calculateCoverage());
    }
}
