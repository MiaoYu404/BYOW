package core;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.awt.event.KeyEvent;

import static core.World.center;

public class Message extends Layer {
    private int mode;
    String message1;
    String message2;
    String message3;

    public Message() {
        name = "Message box";
    }

    public void exitRequest() {
        mode = 0;
        message1 = "You are now request to exit.";
        message2 = "Press 'q' or 'Q' to proceed;";
        message3 = "Press keys other than 'q' or 'Q' to continue.";
        makeVisible();
    }

    // default returns: -1 means no input yet.
    public int input() {
        switch (mode) {
            case 0:
                return inputExit();
        }
        return -1;
    }

    public int inputExit() {
        if (!StdDraw.hasNextKeyTyped()) return -1;
        char c = StdDraw.nextKeyTyped();
        System.out.println(String.format("Key '%s' is typed.", c));
        if (c == 'q' || c == 'Q') return 1;
        makeInvisible();
        return 0;
    }

    @Override
    public void render() {
        System.out.println("-- Menu start render --");
        Font font = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle((double) center.x, (double) center.y, 20, 7);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.filledRectangle((double) center.x, (double) center.y, 20, 7);

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setFont(font);
        StdDraw.text((double) center.x, (double) center.y + 1.5, message1);
        StdDraw.text((double) center.x, (double) center.y, message2);
        StdDraw.text((double) center.x, (double) center.y - 1.5, message3);
        System.out.println("-- Menu finish render --");
    }
}
