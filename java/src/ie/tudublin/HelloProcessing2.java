package ie.tudublin;

import processing.core.PApplet;

public class HelloProcessing2 extends PApplet
{

	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		
	}
	
	public void draw()
	{	
		background(0);
		stroke(255);
		line(10, 10, 200, 200);
		ellipse(200, 200, 100, 50);
		rect(20, 100, 70, 90);
	}
}
