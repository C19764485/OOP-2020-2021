package ie.tudublin;

import processing.core.PApplet;

public class Arrays extends PApplet {

    public float map1(float from, float start1, float stop1, float start2, float stop2) {
        float range1 = stop1 - start1;
        float range2 = stop2 - start2;
        float howFar = from - start1;

        return start2 + (howFar / range1) * range2;
    }

    public void drawGrid() {
        stroke(0, 255, 0);
        float border = width * .05f;
        for (int i = -5; i <=5; i++) {
            float x = map1(i, -5, 5, border, width - border);
            line(x, border, x, height - border);
            line(border, x, width - border, x);
            fill(255, 255, 255);
            text(i, x, border * 0.5f);
            text(i, border * 0.5f, x);
        }
    }

    public void settings() {
        size(500, 500);

        float f = map(2, 0, 10, 0, width);                
        println(f); //Print 100

        f = map1(9, 0, 1, 0, 10);
        println(f); //Print 90

        f = map1(250, 200, 300, 400, 500);
        println(f); //Print 450

        f = map1(5, 0, 10, 1000, 2000);
        println(f); //Print 1500
    }

    int mode = 0;

    float[] rainfall = {45, 37, 55, 27, 30, 50, 79, 48, 104, 31, 100, 58};
    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    float[] arr = new float[100]; //100 float array

    public void keyPressed() {
        // the value of mode will be the number of the 
        // number key pressed
        if (keyCode >= '0' && keyCode <= '9')
            mode = keyCode - '0';
    }

    public void setup() {
        colorMode(RGB);
        
        for(int i = 0; i < rainfall.length; i++) {
            System.out.println(months[i] + "\t" + rainfall[i]);
        }

        //Enhanced for loop
        for(float f:rainfall) {
            println(f);
        }

        // Find the maximum element
        float max = Float.MIN_VALUE;
        int maxIndex = -1;
        for(int i = 0 ; i < rainfall.length ; i ++)
        {
            if (rainfall[i] > max)
            {
                max = rainfall[i];
                maxIndex = i;
            }
        }

        //find minimum element
        float min = Float.MAX_VALUE;
        int minIndex = -1;
        for(int i = 0 ; i < rainfall.length ; i ++)
        {
            if (rainfall[i] < min)
            {
                min = rainfall[i];
                minIndex = i;
            }
        }
        System.out.println("\nThe least rainfall in " + months[minIndex] + " with " + rainfall[minIndex]);

        // Calculate the total
        float total = 0;
        for(float f:rainfall)
        {
            total += f;
        }
        System.out.println("Total: " + total);

        // The same, but with a for loop
        total = 0;
        for(int i = 0 ; i < rainfall.length ; i ++)
        {
            total += rainfall[i];
        }

        float average = total / (float) rainfall.length;
        System.out.println("Average is " + (int) average);
    }

    float offset = 0;

    public void draw() {
        //background(0);
        //drawGrid();
        //colorMode(HSB);
        drawbar();
        //float c = map(mouseX, 0, width, 0, 255);
        //background(c, 255, 255);
    }

    public void drawbar()
    {
        float heig = height / (float) rainfall.length;        
        for(int i = 0 ; i < rainfall.length; i++) {
            fill(124, 50, 48);
            rect(0, map(i, 0, rainfall.length, 0, height), rainfall[i] * 2, heig);
            fill(255, 255, 0);
            float text = map(i, 0, rainfall.length, heig * 0.5f, height + (heig * 0.5f));
            text(months[i], 10, text);
        }       
    }
}
