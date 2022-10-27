package org.raytracer.classes.raycasting;

import org.raytracer.classes.objects.Color;
import org.raytracer.classes.vectors.Vector3;


public class Intersection {
    private float distanceToCameraOrigin;
    private Vector3 startPosition;
    private int amountOfBounces;
    private Vector3 lightPosition = null;
    private Color color;
    private float distanceToLightSource = 0;
    
    
    /**
     *
     * @param startingPosition location of
     */
    public Intersection(Vector3 startingPosition, Color objectColor) {
        this.startPosition = startingPosition;
        this.color = objectColor;
        
        amountOfBounces = 1;
    }
    public void calculateColor(Color lightColor, float distanceToLightSource) {
        Color objectColor = color;

        objectColor.lightReflection(lightColor, distanceToLightSource);

        color.setColor(objectColor);
    }
    
    private void avoidShadowAcne() {
    
    }
    public Color getColor() {
        return color;
    }
    public void setLightPosition(Vector3 lightPosition) {
        this.lightPosition = lightPosition;
        distanceToLightSource = lightPosition.distanceBetweenPoints(startPosition);
    }
    public float getDistanceToLightSource(){
        return distanceToLightSource;
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
