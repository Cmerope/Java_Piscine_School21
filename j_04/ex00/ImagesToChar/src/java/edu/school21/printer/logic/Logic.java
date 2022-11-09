package edu.school21.printer.logic;
import java.awt.image.BufferedImage;

public class Logic {
    private static BufferedImage image = null;
    private static char whiteSymbol;
    private static char blackSymbol;

    public Logic(BufferedImage img, char ws, char bs) {
        image = img;
        whiteSymbol = ws;
        blackSymbol = bs;
    }

    public void convert() {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                if (image.getRGB(j, i) == -1)
                    System.out.print(whiteSymbol);
                else
                    System.out.print(blackSymbol);
            }
            System.out.println();
        }
    }
}
