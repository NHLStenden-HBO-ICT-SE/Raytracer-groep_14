package org.raytracer.classes;

public class Ray {
    
    private Vector3 origin;
    private Vector3 direction = new Vector3();
    private float t; // Distance scalar
    private Color color;

    /**
     * contruct a rayclass,
     *
     * @param origin
     * @param direction
     */
    public Ray(Vector3 origin, Vector3 direction) {
        this.origin = origin;
        if (direction.length() != 1) {
            direction = direction.normalize();
        }
        this.direction = direction;
    }


    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Vector3 getDirection() {
        return direction;
    }
    
    public void setDirection(Vector3 direction) {
        this.direction = direction;
    }
	
	public Vector3 calculateDirection(Camera camera){
		return null; //todo gebruik camera.getPointOnScreen en camera.position om direction van ray uit te rekenen.
	}
    
    public Vector3 getOrigin() {
        return origin;
    }
    
    public void setOrigin(Vector3 origin) {
        this.origin = origin;
    }
    
    //projection(tscalar) = origen + tScalar + direction, where t > 0
    
    
    /**
     * Move the point alongside the direction from the raycast
     *
     * @param t distance along the line between the start and end point
     * @return
     */
    public Vector3 rayPoint(float t) {
        return new Vector3(origin.add(direction.multiply(t)));
    }
    
    
}
