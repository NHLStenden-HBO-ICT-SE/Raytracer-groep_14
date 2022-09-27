package org.raytracer.classes;

public class Color extends SolidObject{

	private float red;

	private float green;

	private float blue;


	public Color(Vector3 position, Color color, float reflection, float emmesion) {
		super(position, color, reflection, emmesion);
	}
}
