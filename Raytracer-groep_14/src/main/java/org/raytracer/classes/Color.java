package org.raytracer.classes;



public class Color {


	private float red = 1;

	private float green = 1;

	private float blue = 1;

	public Color(float red, float green, float blue){

		if(red > 1)
		{this.red = 1;}
		else if(red < 0)
		{this.red = 0;}

		if(green > 1)
		{this.green = 1;}
		else if(green < 0)
		{this.green= 0;}

		if(blue > 1)
		{this.blue = 1;}
		else if(blue < 0)
		{this.blue = 0;}



		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public float getRed() {
		return red;
	}

	public float getBlue() {
		return blue;
	}

	public float getGreen() {
		return green;
	}


	public Color multiply(float value){
		return new Color(this.red * value, this.green * value, this.blue * value);
	}
}
