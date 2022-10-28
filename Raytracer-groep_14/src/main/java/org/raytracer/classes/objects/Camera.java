package org.raytracer.classes.objects;

import org.raytracer.classes.vectors.Vector3;

public class Camera extends SceneObject {
    private float fieldOfView;
    private Vector3 direction;
    private Vector3 center;
    private Vector3 topLeft, topRight, bottomLeft;
    private int screenHeightAndWidth;
    
    public Camera() {
        this.position = new Vector3(0, 0, 0);
        this.direction = new Vector3(0, 0, 1);
        this.fieldOfView = 50;
        this.center = position.add(direction.multiply(fieldOfView));
        this.topLeft = center.add(new Vector3(-1, 1, 0));
        this.bottomLeft = center.add(new Vector3(-1, -1, 0));
        this.topRight = center.add(new Vector3(1, 1, 0));
        this.screenHeightAndWidth = 200;
    }
    
    public Camera(int heightAndWidth) {
        this.position = new Vector3(0, 0, 0);
        this.direction = new Vector3(0, 0, 1);
        this.fieldOfView = 50;
        this.center = position.add(direction.multiply(fieldOfView));
        this.topLeft = center.add(new Vector3(-1, 1, 0));
        this.bottomLeft = center.add(new Vector3(-1, -1, 0));
        this.topRight = center.add(new Vector3(1, 1, 0));
        this.screenHeightAndWidth = heightAndWidth;
    }
    
    public Vector3 getDirection() {
        return direction;
    }
    
    public int getWidthAndHeight() {
        return screenHeightAndWidth;
    }
    
    /**
     * executes this formula 𝑃(𝑢,𝑣) = 𝑝0 + 𝑢(𝑝1−𝑝0) + 𝑣(𝑝2−𝑝0) to calculate a point on the screen.
     *
     * @return Vector3 of point on screen.
     */
    public Vector3 getPointOnScreen(int x, int y) {
        float deltaHorizontal = topRight.getX() - topLeft.getX(); // Width of screen
        float deltaVertical = bottomLeft.getY() - topLeft.getY(); // Height of screen
        float pixelWidth = deltaHorizontal / screenHeightAndWidth; // Width of pixel
        float pixelHeight = deltaVertical / screenHeightAndWidth; // Height of pixel
        
        float xPosition = pixelWidth * x; // x position of pixel in 3d world
        float yPosition = pixelHeight * y; // y position of pixel in 3d world
        
        // Set coordinates to new vector
        return new Vector3(topLeft.getX() + xPosition, topLeft.getY() + yPosition, topLeft.getZ());
    }
}

