package org.raytracer.classes;

public class Camera {
    private float fieldOfView;
    private Vector3 position;
    private Vector3 direction;
    private Vector3 center;
    private Vector3 topLeft, topRight, bottomLeft;
    
    /**
     * Initialises camera with direction in positive z-axis. When you place object, initialise them in this direction too.
     */
    public Camera() {
        this.position = new Vector3(0, 0, 0);
        this.direction = new Vector3(0, 0, 1);
        this.fieldOfView = 50;
        this.center = position.add(direction.multiply(fieldOfView));
        this.topLeft = center.add(new Vector3(-1, 1, 0));
        this.bottomLeft = center.add(new Vector3(-1, -1, 0));
        this.topRight = center.add(new Vector3(1, 1, 0));
    }
    
    public float getFieldOfView() {
        return fieldOfView;
    }
    
    public Vector3 getBottomLeft() {
        return bottomLeft;
    }
    
    public Vector3 getCenter() {
        return center;
    }
    
    public Vector3 getDirection() {
        return direction;
    }
    
    public Vector3 getPosition() {
        return position;
    }
    
    public Vector3 getTopLeft() {
        return topLeft;
    }
    
    public Vector3 getTopRight() {
        return topRight;
    }
    
    public void setFieldOfView(float fieldOfView) {
        this.fieldOfView = fieldOfView;
        this.center = position.add(direction.multiply(fieldOfView));
    }
    
    /**
     * determine width of screen
     *
     * @return Vector3
     */
    public Vector3 getHorizontalVector3() {
        return topRight.subtract(topLeft);
    }
    
    /**
     * Determine the height of screen
     *
     * @return Vector3
     */
    public Vector3 getVerticalVector3() {
        return bottomLeft.subtract(topLeft);
    }
    
    /**
     * Gets point on virtual screen of camera. With this point you can calculate the direction of a ray.
     *
     * @param horizontal a float that should be between 1 and 0
     * @param vertical   a float that should be between 1 and 0
     * @return Vector3 location on screen.
     */
    public Vector3 getPointOnScreen(float horizontal, float vertical) {
        /*
        Checks horizontal and vertical and keeps them between expected values.
        Otherwise, the point will be outside the screen.
         */
        if (horizontal > 1)
            horizontal = 1;
        else if (horizontal < 0)
            horizontal = 0;
        if (vertical > 1)
            vertical = 1;
        else if (vertical < 0)
            vertical = 0;
        
        
        Vector3 horizontalPoint = getHorizontalVector3().multiply(horizontal); // Calculates point on x-axis
        Vector3 verticalPoint = getVerticalVector3().multiply(vertical); // Calculates point on y-axis
        
        return topLeft.add(horizontalPoint).add(verticalPoint); // Calculates point on screen and returns it as a Vector3
    }
}

