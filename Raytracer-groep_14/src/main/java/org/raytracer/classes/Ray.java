package org.raytracer.classes;

public class Ray {
    
    private Vector3 origin;
    private Vector3 direction = new Vector3();
    private float t; // Distance scalar
    private Color color;

    /**
     * Creates ray with direction calculated from camera
     * @param camera for origin and calculating direction
     * @param x Horizontal pixel of screen
     * @param y Vertical pixel of screen
     */
    public Ray(Camera camera, int x, int y) {
        this.origin = camera.getPosition();
        this.direction = calculateDirection(camera, x, y);
        
        // todo Is deze nog nodig?
        if (direction.length() != 1) {
            direction = direction.normalize();
        }
        
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
    
    /**
     * calculates direction of ray
     *
     * Formula used: 𝐷=𝑃(𝑢,𝑣) − 𝐸
     * @param camera for position (E)
     * @param x Horizontal pixel of screen
     * @param y Vertical pixel of screen
     * @return
     */
	public Vector3 calculateDirection(Camera camera, int x, int y){
		return camera.getPointOnScreen(x,y).subtract(camera.getPosition());
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
