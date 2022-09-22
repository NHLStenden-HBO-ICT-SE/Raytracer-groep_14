package org.raytracer.classes;

public class Ray {

	private Vector3 origin;
	private Vector3 direction;
	private float t; // Distance scalar

	public void setDirection(Vector3 direction) {
		this.direction = direction;
	}
}
