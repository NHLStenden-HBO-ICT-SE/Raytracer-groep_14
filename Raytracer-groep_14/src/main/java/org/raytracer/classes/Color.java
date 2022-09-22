package org.raytracer.classes;

import static java.lang.Math.*;

/**
 * Code based on and uses https://github.com/carl-vbn/pure-java-raytracer/blob/23300fca6e9cb6eb0a830c0cd875bdae56734eb7/src/carlvbn/raytracing/pixeldata/Color.java#L48
 */
public class Color {

	private float red;

	private float green;

	private float blue;

	public Color(float red, float green, float blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	/**
	 * create and mix the given colour
	 * @param other
	 * @return
	 */
	public Color add(Color other) {
		return new Color(min(1, this.red+other.red), min(1, this.green+other.green), min(1, this.blue+other.blue));
	}

	/**
	 * mix the given colour to the current colour
	 * @param other
	 */
	public void addSelf(Color other) {
		this.red = min(1, this.red+other.red);
		this.green = min(1, this.green+other.green);
		this.blue = min(1, this.blue+other.blue);
	}

	/**
	 * increase the brightness of the curren colour
	 * @param brightness
	 * @return
	 */
	public Color add(float brightness) {
		return new Color(min(1, red+brightness), min(1, green+brightness), min(1, blue+brightness));
	}

}
