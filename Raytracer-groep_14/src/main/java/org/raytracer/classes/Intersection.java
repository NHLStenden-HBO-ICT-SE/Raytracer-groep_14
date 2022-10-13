package org.raytracer.classes;

public class Intersection {
    private float distanceToCameraOrigin;
    private Vector3 startPosition;
    private int amountOfBounces;

    private SolidObject solidObject;
    
    public Intersection(Vector3 startingPosition, float distanceToCameraOrigin) {
        this.startPosition = startingPosition;
        this.distanceToCameraOrigin = distanceToCameraOrigin;
        amountOfBounces = 1;
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

    public SolidObject getSolidObject() {
        return solidObject;
    }

    public void setSolidObject(SolidObject solidObject) {
        this.solidObject = solidObject;
    }

    public void setPosition(float x, float y, float z) {
        startPosition = new Vector3(x, y, z);
    }
}
