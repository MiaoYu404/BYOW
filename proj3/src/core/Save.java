package core;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import tileengine.Coordinate;
import tileengine.TETile;
import tileengine.Tileset;

import java.io.File;

import static core.LayerManager.seed;
import static core.LayerManager.worldLayer;
import static core.World.*;

public class Save {
    public static void loadSave(String filename) {
        System.out.println("'Load Game' is called.");
        In file = new In(filename);
        if (file.isEmpty()) throw new IllegalArgumentException("file is empty");

        readSeed(file);
        readSize(file);
        readWorld(file);
        readPlayer(file);

        System.out.println("'Save.loadSave' is done.");
    }

    private static void readSeed(In file) {
        long readSeed = Long.parseLong(file.readLine());
        LayerManager.updateSeed(readSeed);
        System.out.println("seed is updated with " + readSeed);
    }

    private static void readSize(In file) {
        String line = file.readLine();
        String[] splitLine = line.split(",");
        LayerManager.width = Integer.parseInt(splitLine[0]);
        LayerManager.height = Integer.parseInt(splitLine[1]);
        System.out.println("layer with width " +  LayerManager.width + ", height " + LayerManager.height);
    }

    private static void readWorld(In file) {
        String line;
        String[] splitLine;
        TETile[][] world = new TETile[LayerManager.width][LayerManager.height];
        for (int row = 0; row < width; row++) {
            line =  file.readLine();
            splitLine = line.split(",");
            for (int col = 0; col < height; col++) {
                try {
                    world[row][col] = Tileset.id2tile.get(Integer.parseInt(splitLine[col]));
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
            }
        }
        worldLayer.setWorld(world);
    }

    private static void readPlayer(In file) {
        String line = file.readLine();
        String[] splitLine = line.split(",");
        int x = Integer.parseInt(splitLine[0]), y = Integer.parseInt(splitLine[1]);
        worldLayer.setPlayer(new Coordinate(x, y));
        file.close();
    }

    public static void saveFile() {
        System.out.println("'Save.saveFile()' is called.");
        Out file = new Out("proj3/src/saves/save1.txt");

        saveSeed(file);
        saveSize(file);
        saveWorld(file);
        savePlayer(file);

        file.close();
        System.out.println("'Save.saveFile()' is done.");
    }

    private static void saveSeed(Out file) { file.println(seed); }

    private static void saveSize(Out file) { file.println(String.format("%d,%d", width, height)); }

    private static void saveWorld(Out file) {
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                file.print(world[row][col].id() + ",");
            }
            file.println();
        }
    }

    private static void savePlayer(Out file) {
        file.println(String.format("%d,%d", worldLayer.player.coordinate.x, worldLayer.player.coordinate.y));
    }

    public static void clearSave(String filename) {
        System.out.println("'Save.clearSave' is called.");
        Out file = new Out(filename);
        file.print("");
        file.close();
    }
}
