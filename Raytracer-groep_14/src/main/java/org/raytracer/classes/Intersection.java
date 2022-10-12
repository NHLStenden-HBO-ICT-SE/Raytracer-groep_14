package org.raytracer.classes;

public class Intersection extends Ray {
    
    
    /**
     * Creates ray with direction calculated from camera
     *
     * @param camera for origin and calculating direction
     * @param x      Horizontal pixel of screen
     * @param y      Vertical pixel of screen
     */
    public Intersection(Camera camera, int x, int y) {
        super(camera, x, y);
    }
}
