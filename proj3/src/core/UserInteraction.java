package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;

import static core.LayerManager.*;

public class UserInteraction {
    private LayerManager lm;

    private TERenderer ter;

    public UserInteraction(LayerManager lm) {
        this.lm = lm;
    }

    public Thread run() {
        lm.renderFrame();
        System.out.println("Key type listener is now start.");
        while (true) {
            if (handleKeys() || handleMouse()) {
                lm.renderFrame();
                System.out.println(String.format("[%s] render new frame", System.currentTimeMillis()));
            }
            if (StdDraw.isMousePressed()) {
                System.out.println(String.format("mouse coordinate (%.2f, %.2f)", StdDraw.mouseX(), StdDraw.mouseY()));
            }
            StdDraw.pause(40);
        }
    }

    private boolean handleKeys() {
        boolean flag = false;
        if (menuLayer.visible) {
            flag |= menuLayer.input();
        } else if (messageLayer.visible) {
            switch (messageLayer.input()) {
                case 0:
                    return true;

                case 1:
                    // TODO: exit method in menu?
                    Save.saveFile();
                    menuLayer.exit();
            }
        } else {
            flag |= worldLayer.input();
        }
        return flag;
    }

    private boolean handleMouse() {
        boolean flag = false;
        if (hudLayer.visible && worldLayer.mode == 0) {
            flag |= hudLayer.input();
        }
        return flag;
    }

    public static void exitRequest() {
        System.out.println("'Exit' request has been called.");
        messageLayer.exitRequest();
    }

    public static void clearKeyTyped() {
        while (StdDraw.hasNextKeyTyped()) {StdDraw.nextKeyTyped(); }
    }
}