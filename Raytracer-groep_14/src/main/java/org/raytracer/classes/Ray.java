package org.raytracer.classes;

public class Ray {

	private Vector3 origin;
	private Vector3 direction;
	private float t; // Distance scalar


	//projection(tscalar) = origen + tScalar + direction, where t > 0
	public Ray(Vector3 origin, Vector3 direction){
		this.origin = origin;
		if (direction.length() != 1){
			direction = direction.normalize();
		}
		this.direction = direction;
	}

	/**
	 * Move the point alongside the direction from the raycast
	 * @param t distance along the line between the start and end point
	 * @return
	 */
	public Vector3 rayPoint(float t){
		return new Vector3(origin.add(direction.multiply(t)));
	}



}
