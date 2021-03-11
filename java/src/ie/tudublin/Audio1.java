package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet {

    Minim minim; // Connect to minim
    AudioInput ai; // How to connect to mic
    AudioPlayer ap;
    AudioBuffer ab; // Samples

    float[] lerpedBuffer;

    public void settings() {
        size(900, 900);
        // fullScreen(P3D, SPAN); // Try this for full screen multiple monitor support :-) Be careful of exceptions!
    }

    float y = 200;
    float lerpedY = y;
    int colArrayCounter;
    int which = 0;
    int[] colArray = {
        color(25, 165, 190),
        color(95, 170, 200),
        color(120, 190, 210),
        color(170, 210, 230),
        color(205, 225, 245),
        color(220, 240, 250)
    };

    public void setup() {
        minim = new Minim(this);
        ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        ap = minim.loadFile("music.mp3", width); 
        //ab = ai.mix; // Connect the buffer to the mic
        ab = ap.mix; // Connect the buffer to the mp3 file
        colorMode(HSB);
        lerpedBuffer = new float[width];
        mouseX = 10;
    }

    public void keyPressed() {
        if (keyCode >= '0' && keyCode <= '6') {
            which = keyCode - '0';
        }
        if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
    }

    float lerpedAverage = 0;

    public void draw() {
        background(0);
        stroke(255);
        float halfHeight = height / 2;
        float average = 0;
        float sum = 0;

        // Calculate the average of the buffer
        for (int i = 0; i < ab.size(); i ++)
        {
            sum += abs(ab.get(i));
        }
        average = sum / ab.size();
        // Move lerpedAverage 10% closer to average every frame
        lerpedAverage = lerp(lerpedAverage, average, 0.1f);

        switch (which)
        {
            case 0:
            {
                // Iterate over all the elements in the audio buffer
                for (int i = 0; i < ab.size(); i++) {

                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        
                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 4, halfHeight + lerpedBuffer[i] * halfHeight * 4, i);
                }

                // See the difference lerping makes? It smooths out the jitteryness of average, so the visual looks smoother
                ellipse(width / 4, 100, average * 500, average * 500);
                ellipse(width / 2, 100, 50 + (lerpedAverage * 500), 50 + (lerpedAverage * 500));
        
                // This is another example of how lerping works
                ellipse(200, y, 30, 30);
                ellipse(300, lerpedY, 30, 30);
                y += random(-10, 10);
                lerpedY = lerp(lerpedY, y, 0.1f);
                break;
            }   
            case 1:
            {
                // Iterate over all the elements in the audio buffer
                for (int i = 0; i < ab.size(); i++) {

                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
                    line(i, halfHeight - lerpedBuffer[i] * halfHeight * 3, i, halfHeight + lerpedBuffer[i] * halfHeight * 3 );
                }
                break;
            }
            case 2:
            {
                for (int i = 0; i < ab.size(); i++) 
                {                    
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);        
                    line(i, height - lerpedBuffer[i] * height * 2, i, height + lerpedBuffer[i] * height * 2);

                    //2nd horizontal line
                    line(i, halfHeight/height - lerpedBuffer[i] * height * 2, i, halfHeight/height + lerpedBuffer[i] * height * 2);

                    //1st vertical line
                    line(height + lerpedBuffer[i] * height * 2, i, height - lerpedBuffer[i] * height * 2, i);

                    //2st vertical line
                    line(halfHeight/height + lerpedBuffer[i] * height * 2, i, halfHeight/height - lerpedBuffer[i] * height * 2, i);
                }
                break;
            }
            case 3:
            {

                for (int i = 0; i < ab.size(); i++) 
                {                    
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    noFill();
                    // ellipse(300, lerpedY, 30, 30);
                    ellipse(width / 2, height /2, 50 + (lerpedAverage * 1200), 50 + (lerpedAverage * 1200));
                }
                break;
            }
            case 4:
            {
                for (int i = 0; i < ab.size(); i++) 
                {                    
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    strokeWeight(2);
                    noFill();
                    rectMode(CENTER);
                    float size = 50 + (lerpedAverage * 1200);
                    rect(height / 2, width / 2, size, size);
                }
                break;
            }
            case 5:
            {
                translate(width / 2, height / 2);
                float rStep = 30;
                float rMax = 400;
                float rMin = mouseX;
                
                for (float r = rMin; r < rMax; r+=rStep)
                {
                    float c = 2*PI*r;
                    float cSegement = map(r, 0, rMax, rStep*3/4, rStep/2);
                    float aSegement = floor(c/cSegement);
                    float ellipseSize = map(r, 0, rMax, rStep*3/4-1, rStep/4);
                    for (int i = 0; i < 360; i+=360/aSegement) 
                    {                  
                        noStroke();
                        colArrayCounter++;
                        if(colArrayCounter > 5) colArrayCounter = 0;
                        fill(colArray[colArrayCounter]);
                        pushMatrix();
                        rotate(radians(i));
                        ellipse(r, 0, ellipseSize, ellipseSize);
                        popMatrix();
                    }         
                }
                       
                break;
            }
            case 6:
            {
                float r = 1f;
                int numPoints = 5;
                float thetaInc = TWO_PI / (float) numPoints;
                strokeWeight(2);
                stroke(255);
                float lastX = width / 2, lastY = height /2 ;
                for(int i = 0; i < 1000; i++)            
                {
                    float c = map(i, 0, 300, 0, 255) % 255.0f;
                    stroke(c, 255, 255);
                    // lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);        
                    float theta = i * (thetaInc * lerpedAverage * 5);
                    float x = width / 2 + sin(theta) * r;
                    float y = height / 2 - cos(theta) * r;
                    r += 0.5f + lerpedAverage;
                    // point(x, y);         
                    line(lastX, lastY, x, y);
                    lastX = x;
                    lastY = y;
                }
                break;
            }
        }        
    }
}