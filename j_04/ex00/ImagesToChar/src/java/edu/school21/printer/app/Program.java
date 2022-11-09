package edu.school21.printer.app;


import edu.school21.printer.logic.Logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Program {

    private static final String USAGE = "Error ! Read README.txt";

    public static void main(String[] args) {
        if (args.length != 3 || args[0].length() != 1 || args[1].length() != 1) {
            System.err.println(USAGE);
            System.exit(-1);
        }
        char white = args[0].charAt(0);
        char black = args[1].charAt(0);
        String path = args[2];
        try {
            Logic logic = new Logic(ImageIO.read(new File(path)), white, black);
            logic.convert();
        } catch (IOException e) {
            System.err.println(USAGE);
            System.exit(-1);
        }

    }
}
