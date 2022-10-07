package org.raytracer.classes;

public class Color {

	private float red;

	private float green;

	private float blue;


	public Color(){

		setColor(Color.White);
	}
	public Color(float red, float green, float blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public void setColor(float red, float green, float blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	public void setColor(Color color){
		this.red = color.red;
		this.green = color.green;
		this.blue = color.blue;
	}
	public static final Color White = new Color(1f,1f,1f);
	public static final Color Blue = new Color(0,0,1f);

}
