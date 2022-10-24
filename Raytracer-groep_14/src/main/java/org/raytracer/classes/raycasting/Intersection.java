package org.raytracer.classes.raycasting;

import org.raytracer.classes.vectors.Vector3;

public class Intersection {
    private float distanceToCameraOrigin;
    private Vector3 startPosition;
    private int amountOfBounces;
    
    
    /**
     *
     * @param startingPosition location of
     * @param distanceToCameraOrigin
     */
    public Intersection(Vector3 startingPosition, float distanceToCameraOrigin) {
        this.startPosition = startingPosition;
        this.distanceToCameraOrigin = distanceToCameraOrigin;
        
        amountOfBounces = 1;
    }
    
    private void avoidShadowAcne() {
    
    }
    
    public void incrementAmountOfBounces() {
        amountOfBounces++;
    }
    
    public float getDistanceToCameraOrigin() {
        return distanceToCameraOrigin;
    }
    
    public int getAmountOfBounces() {
        return amountOfBounces;
    }
    
    public Vector3 getStartPosition() {
        return startPosition;
    }
    
    public void setPosition(float x, float y, float z) {
        startPosition = new Vector3(x, y, z);
    }
}
