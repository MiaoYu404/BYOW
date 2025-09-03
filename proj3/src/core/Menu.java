package core;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.awt.event.KeyEvent;

import static core.LayerManager.width;
import static core.LayerManager.height;

public class Menu extends Layer{
//    public static int width;
//    public static int height;
    private static LayerManager lm;

    public static long seed;

    private String inputSeed;

    public Menu(int width, int height, LayerManager lm) {
        name = "Menu";
//        this.width = width;
//        this.height = height;
        this.lm = lm;
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
    }

    public void CS61B() {
        StdDraw.setPenColor(Color.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 40);
        StdDraw.setFont(font);
        StdDraw.text((double) width / 2, (double) height / 4 * 3, "CS61B: BYOW");
    }

    @Override
    public void render() {
        System.out.println("-- Menu start render --");
        switch(page) {
            case 0:
                renderMainMenu();
                break;

            case 1:
                renderNewGameMenu();
                break;
        }
        System.out.println("-- Menu finish render --");
    }

    public void renderMainMenu() {
        CS61B();
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text((double) width / 2, (double) height / 2, "(N) New Game");
        StdDraw.text((double) width / 2, (double) height / 10 * 4, "(L) Load Game");
        StdDraw.text((double) width / 2, (double) height / 10 * 3, "(Q) Quit Game");
    }

    public void renderNewGameMenu() {
        CS61B();
        drawFrame("Enter seed followed by S");
        StdDraw.text((double) width / 2, (double) height / 10 * 3, inputSeed);
    }

    // draw the given String at the center of the screen.
    public void drawFrame(String s) {
        StdDraw.setPenColor(Color.BLACK);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text((double) width / 2, (double) height / 2, s);
        StdDraw.show();
    }

    public boolean input() {
        switch (page) {
            case 0:
                return inputMainMenu();

            case 1:
                return inputNewGame();
        }
        return false;
    }

    public boolean inputMainMenu() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_N)) {
            newGame();
            return true;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_L)) {
            loadGame();
            return true;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
            exit();
            return true;
        }
        return false;
    }
    public boolean inputNewGame() {
        if (!StdDraw.hasNextKeyTyped()) return false;

        char c = StdDraw.nextKeyTyped();
        if (c == 'S' || c == 's') {
            // TODO: startGame Here
            seed = Long.valueOf(inputSeed);
            System.out.println("Seed is inputted");
            lm.updateSeed(seed);
            startGame();
        }
        if (Character.isDigit(c)) {
            inputSeed += String.valueOf(c);
        }
        return true;
    }

    // page 0
    public void mainMenu() {
        page = 0;
        makeVisible();
    }

    // page 1
    public void newGame() {
        System.out.println("'New Game' is called.");
        UserInteraction.clearKeyTyped();
        page = 1;
        inputSeed = "";
        makeVisible();
    }

    // page 2
    public void loadGame() {
        System.out.println("'Load Game' is called.");
        page = 2;
        Save.loadSave("proj3/src/saves/save1.txt");
        startGame();
    }

    public void exit() {
        System.out.println("'Exit' is called.");
        System.exit(0);
    }

    public void startGame() {
        makeInvisible();
        lm.startGame();
    }
}
