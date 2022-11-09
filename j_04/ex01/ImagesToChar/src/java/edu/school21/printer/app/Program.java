package edu.school21.printer.app;


import edu.school21.printer.logic.Logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Program {

    public static void main(String[] args) {
        if (args.length != 2 || args[0].length() != 1 || args[1].length() != 1) {
            System.err.println("Error ! Read README.txt");
            System.exit(-1);
        }
        char white = args[0].charAt(0);
        char black = args[1].charAt(0);

        try {
            URL path = Program.class.getResource("/resources/it.bmp");
            assert path != null;
            BufferedImage img = ImageIO.read(path);
            Logic.convert(img, white, black);
        } catch (IOException e) {
            System.err.println("Error ! Read README.txt");
            System.exit(-1);
        }

    }
}
