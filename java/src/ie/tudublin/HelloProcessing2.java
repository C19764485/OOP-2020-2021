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
		background(255, 0, 0);
		
		point(200, 60);
		fill(255, 255, 0);
		ellipse(250, 280, 460, 460);
		fill(0, 255, 255);
		triangle(250, 50, 40, 460, 460, 460);
		fill(255, 255, 255);
		ellipse(250, 250, 200, 100);
		fill(0, 0, 0);
		ellipse(250, 250, 50, 50);
		text("Panos", 250, 400); 	
	
	}
}
