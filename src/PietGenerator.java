import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class PietGenerator {
    public static void main(String[] args) throws IOException {
        String message = "Thank you for being a Great Mom for Another Year <3";
        int width = (message.length()*6) + 9;
        int height = 26;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // Set up the color values
        Color white = new Color(255, 255, 255);
        Color black = new Color(0, 0, 0);

        Color[] lightColors = {
                new Color(192, 192, 255),
                new Color(192, 255, 255),
                new Color(192, 255, 192),
                new Color(255, 255, 192),
                new Color(255, 192, 192),
                new Color(255, 192, 255)
        };
        Color[] darkColors = {
                new Color(0, 0, 192),
                new Color(0, 192, 192),
                new Color(0, 192, 0),
                new Color(192, 192, 0),
                new Color(192, 0, 0),
                new Color(192, 0, 192)
        };
        // Init to White
        for(int w = 0; w < width; w++){
            for(int h = 0; h < height; h++){
                image.setRGB(w, h, white.getRGB());
            }
        }
        // Set the Vertical Light Lines
        int currColor = -1;
        for(int w = 5; w <= width; w += 6){
            currColor = ((currColor == 5) ? 0 : (currColor + 1));
            for(int h = 0; h < height; h++){
                image.setRGB(w, h, lightColors[currColor].getRGB());
            }
        }
        // Set the dark squares
        int currCornerIndex = 0;
        int currCharInt = 0;
        int row = 0;
        int remainder = 0;
        currColor = -1;
        for(int i = 0; i < message.length(); i++){ // for ever letter in the message
            currColor = ((currColor == 5) ? 0 : (currColor + 1)); // the current color is iterated through
            currCornerIndex = i * 6; // the column corner of this set of 5 columns
            currCharInt = message.charAt(i);
            row = currCharInt/ 5; // the last full row
            remainder = currCharInt - (row*5); // the remaining squares to add
            for(int r = 0; r < row; r++){ // for every row until the last full row
                for(int c = currCornerIndex; c < currCornerIndex + 5; c++){ // for all five columns, starting at the currCornerIndex
                    image.setRGB(c, r, darkColors[currColor].getRGB());
                }
            }
            for(int c = currCornerIndex; c < currCornerIndex + remainder; c++){
                image.setRGB(c, row, darkColors[currColor].getRGB());
            }
        }
        // set extra 5x2 square
        currColor = ((currColor == 5) ? 0 : (currColor + 1));
        currCornerIndex += 6;
        for(int i = 0; i < 5 ; i++) {
            for(int j = 0; j < 2; j++) {
                image.setRGB(currCornerIndex + i, j, darkColors[currColor].getRGB());
            }
        }
        // set little end loop
        currCornerIndex += 6;
        //image.setRGB(currCornerIndex -1 , 0, lightColors[currColor].getRGB());
        currColor = ((currColor == 5) ? 0 : (currColor + 1));
        int[][] coloredLoop = {
                {0, currCornerIndex},
                {1, currCornerIndex},
                {2, currCornerIndex},
                {2, currCornerIndex - 1},
                {2, currCornerIndex + 1}
        };
        int[][] blackLoop = {
                {1, currCornerIndex -1},
                {2, currCornerIndex-2},
                {3, currCornerIndex-1},
                {3, currCornerIndex},
                {3, currCornerIndex+1},
                {2, currCornerIndex+2},
                {1, currCornerIndex+1},
                {0, currCornerIndex+1}
        };
        for(int[] pair : coloredLoop) {
            image.setRGB(pair[1], pair[0], darkColors[currColor].getRGB());
        }
        for(int[] pair : blackLoop) {
            image.setRGB(pair[1], pair[0], black.getRGB());
        }
        File outputfile = new File("image.png");
        ImageIO.write(image, "png", outputfile);
    }
    /*
    * The flame that burns Twice as bright burns half as long.
    public static void main(String[] args) {
        String message = "Heineman - Thank you for the best Q Term <3 - Algos 2020";
        System.out.println(message.length());
        char currChar;
        int row;
        int remainder;
        for(int i = 0; i <message.length(); i++){
            currChar = message.charAt(i);
            row = ((int)currChar / 5) - 1;
            remainder = currChar - ((row+1)*5);
            System.out.println(currChar + "\t" + ((int) currChar) + "\t" + row + "\t"  + remainder + "\t");
        }
    }
    * */
}
