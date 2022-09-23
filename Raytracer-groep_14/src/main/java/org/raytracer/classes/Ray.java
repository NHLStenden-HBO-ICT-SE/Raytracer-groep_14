package org.raytracer.classes;

public class Ray {

	private Vector3 origin;
	private Vector3 direction;
	private float t; // Distance scalar

	public Ray(Vector3 origin, Vector3 direction){
		this.origin = origin;
		if(direction.length() != 1){
			direction = direction.normalize();
		}
		this.direction = direction;
	}
	public void setDirection(Vector3 direction) {
		this.direction = direction;
	}

	public Vector3 getDirection() {
		return direction;
	}

	public Vector3 getOrigin() {
		return origin;
	}
}
