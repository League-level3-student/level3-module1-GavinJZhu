package _05_Retro_Sun;

import processing.core.PApplet;

import java.util.ArrayList;

/*
 * Goal: Create an animated retro sun image!
 * 
 * Follow the inline instructions below. Open RetroSun.html in this folder to
 * see final image and what each step should look like.
 */
public class RetroSun extends PApplet {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static float rectY;
    static float rectHeight;

    // RGB colors
    int[] sunColors = {
            color(212, 202, 11), color(214, 198, 30), color(211, 170, 26),
            color(216, 157, 51), color(217, 124, 64), color(213, 104, 81),
            color(212, 51, 98), color(215, 29, 121), color(217, 11, 139),
            color(217, 0, 151) };

    int bgColor = color(31, 0, 48);
    private float sunCenterX;
    private float sunCenterY;
    private float sunHeight;
    private ArrayList<Rectangle> rectangles = new ArrayList<>();
    private float sunBottomY;


    @Override
    public void settings() {
        // 1. Set the size of your sketch to at least 800 width, 600 height
        size(WIDTH, HEIGHT);
    }

    @Override
    public void setup() {
        // 2. Set bgColor as the background color
        background(bgColor);
        rectY = WIDTH/2;
        rectHeight = 20;
        int sunCenterY = 300;
        int sunHeight = 400;
        int sunCenterX = 400;
        int sunRadius = 200;
        int sunBottomY = sunCenterY+sunRadius;
        fill(sunColors[0]);
        noStroke();
        text("sunCenterX = "+sunCenterX, 25, 100);
        ellipse(sunCenterX, sunCenterY, sunHeight, sunHeight);
        loadPixels();
        for (int i = 0; i < pixels.length; i++) {

            if (pixels[i] == sunColors[0]){
                // If pixel[i] is the same color as the color of our circle (sunColors[0]),
                // we need to map the pixel to a color in our sunColors[] array
                // (see 2nd gradient image in RetroSun.html)

                // The top of the sun is yellow (sunColors[0]) and the bottom
                // of the sun is red (sunColors[sunColors.length - 1]

                // In order to get the right color, the y value from the top of
                // the sun to the bottom has to be mapped to a range from 0 to 1.
                // Use the map() function to do that:

                int y = i/width;
                float step = map(y, sunCenterY, sunBottomY, 0, 1);
//                System.out.println(y);
//                System.out.println("Step:"+step);
                // Call interpolateColor(sunColors, step) and save the color
                // variable that's returned
                int interpolatedColor = interpolateColor(sunColors, step);
                // Set pixels[i] to the returned color
                pixels[i] = interpolatedColor;
            }
        }
        updatePixels();

        // *The y position can be any value within the sun:
        // *The height can be any value you choose:
        float h = rectHeight;
        // *The x position can be the center of the sun's x position minus the radius:
        float sunLeftX = sunCenterX - sunRadius;
        // *The width can be 2 times the radius
        float w = 2 * sunRadius;
        for (int i = 0; i < 1; i++) {
            Rectangle rect = new Rectangle(sunLeftX, sunBottomY+50*i, w, h);
            rectangles.add(rect);

        }
    }

    @Override
    public void draw() {
        /*
         * PART 1: Drawing the sun
         */

        // Draw an ellipse for the sun in the center of the window
        // Use fill(sunColors[0]) to make it yellow
        // Use noStroke() to remove the black outline
//        fill(sunColors[0]);
//        noStroke();
//        text("sunCenterX = "+sunCenterX, 25, 100);
//        ellipse(sunCenterX, sunCenterY, sunHeight, sunHeight);
        // Do you see a yellow sun like in the 1st image?
        // If not, fix your code before proceeding.


        /*
         * PART 2: Drawing a color gradient on the sun
         *
         * This will make the sun have gradually different colors from the top to bottom
         */

        // Call the loadPixels() method to put all the pixel colors into
        // the pixels[] array
        // https://processing.org/reference/loadPixels_.html
        //loadPixels();
        // We want to change the color of our sun so use an if statement
        // to check if the pixel is the color of the yellow circle.
        //System.out.println(pixels.length);


        // Call updatePixels() after you loop through all the pixels to
        // update the pixel colors
        // https://processing.org/reference/updatePixels_.html
        //updatePixels();

        /*
         * PART 3: Drawing the missing sections at the bottom of the sun
         *
         * The missing parts of the sun are created by drawing rectangles over the sun
         * with the same color as the background.
         */

        // Set the fill color to the background color
        //fill(bgColor);

        // To draw each rectangle we need to find its x, y, width, height






        for (Rectangle rec : rectangles) {
            //stroke(bgColor);
            fill(128);
            rect(rec.x, rec.y, rec.w, rec.h);
            text(rec.x+", "+rec.y+", "+rec.w+", "+rec.h+", ", 25, 120);

            System.out.println("making rectangle "+rec.y);

            // Do you see a section missing from the sun like in the 3rd image?


            /*
             * PART 4: Moving the missing sun sections
             *
             * To move a section upwards each rectangle's y value needs to decrease. To make
             * the section get smaller, its height needs to also decrease.
             */

            // Decrease the y variable of the rectangular section created in PART 3.
            // If there isn't a variable, declare a float variable OUTSIDE of the
            // draw function AND initialize it in the setup() function.

            // Do you see the rectangle moving upwards?
            rec.y -= 1;

            // Pick a y positon to be the location when the sections stop moving up.
            // If the rectangle's y positon is above this, move the rectangle's
            // y position back to the bottom of the sun.

            //iterate through each rectangle, check each y val, if y is less than threshhold
            //reset to bottom
            for (Rectangle rect : rectangles) {
                if (rect.y <= 300) {

                    System.out.println("before " + rect.y);
                    rect.y = 400;
                    System.out.println("after " + rect.y);
                }
            }
            // Does the rectangle move back to the bottom?

            // Decrease the height of the rectangle as it moves upwards.
            // Similar to the y positon, a float variable for the height needs to be
            // created if it doesn't already exist.

            // Adjust the amount to decrease so that it disappears close to the top.
            // HINT: You can use the map() function again,

            rectHeight = map(sunBottomY, 300, sunBottomY, 1, 40);
            System.out.println(rectHeight);
        }
        //System.out.println(rectH);
        // The map() function will make the value of h = 1 if y is at the top,
        // and h = 40 if y is at the bottom.


        /*
         * PART 5: Managing the missing sun sections
         *
         * Using a list to manage moving multiple missing sun sections
         */

        // Figure out how to create the other missing sun sections using the
        // code you wrote for the 1 missing sun section.
        // HINT: You can use the Rectangle class defined below to create
        // a list of Rectangles.


        /*
         * PART 6: Adding extras
         *
         * If you want to make your retro sun look more unique, try adding
         * reflections and stars. See RetroSun.html in this folder for some
         * example classes
         */

    }


    static public void main(String[] passedArgs) {
        PApplet.main(RetroSun.class.getName());
    }

    /*********************** DO NOT MODIFY THE CODE BELOW ********************/

    // Placed here so it can be used by all classes
    // Variable step should be between 0 and 1, inclusive
    int interpolateColor(int[] arr, float step) {
        int sz = arr.length;

        if (sz == 1 || step <= 0.0) {
            return arr[0];
        } else if (step >= 1.0) {
            return arr[sz - 1];
        }

        float scl = step * (sz - 1);
        int i = (int) scl;

        return lerpColor(arr[i], arr[i + 1], scl - i);
    }

    // Feel free to use this class to create a list of missing
    // sections in the sun, for example:
    // ArrayList<Rectangle> sections = new ArrayList<Rectangle>();
    class Rectangle {
        float x, y, w, h;

        Rectangle(float x, float y, float w, float h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
    }
}
