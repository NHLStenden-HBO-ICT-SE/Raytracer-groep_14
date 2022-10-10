package org.raytracer.classes;

public class Camera {
    private float fieldOfView;
    private Vector3 position;
    private Vector3 direction;
    private Vector3 center;
    private Vector3 topLeft, topRight, bottomLeft;
    
    private int screenWidth;
    private int screenHeight;
    
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
        this.screenHeight = 200;
        this.screenWidth = 400;
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

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
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
     * executes this formula 𝑃(𝑢,𝑣) = 𝑝0 + 𝑢(𝑝1−𝑝0) + 𝑣(𝑝2−𝑝0) to calculate a point on the screen.
     *
     * @return Vector3 of point on screen.
     */
    public Vector3 getPointOnScreen(int x, int y) {
        float deltaHorizontal = topRight.getX() - topLeft.getX(); // Width of screen
        float deltaVertical = bottomLeft.getY() - topLeft.getY(); // Height of screen
        float pixelWidth = deltaHorizontal / screenWidth; // Width of pixel
        float pixelHeight = deltaVertical / screenHeight; // Height of pixel
        
        float xPosition = pixelWidth * x; // x position of pixel in 3d world
        float yPosition = pixelHeight * y; // y position of pixel in 3d world
        
        // Set coordinates to new vector
        return new Vector3(topLeft.getX() + xPosition, topLeft.getY() + yPosition, topLeft.getZ());
    }
}

