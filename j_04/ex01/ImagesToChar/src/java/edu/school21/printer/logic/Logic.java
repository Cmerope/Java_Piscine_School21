package edu.school21.printer.logic;
import java.awt.image.BufferedImage;

public class Logic {
    public static void convert(BufferedImage image, char white, char black) {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                if (image.getRGB(j, i) == -1)
                    System.out.print(white);
                else
                    System.out.print(black);
            }
            System.out.println();
        }
    }
}
