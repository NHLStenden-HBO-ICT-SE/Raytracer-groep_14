package org.raytracer.classes.raycasting;

import org.raytracer.classes.objects.Color;
import org.raytracer.classes.vectors.Vector3;

public class Intersection {
    private Vector3 startPosition;
    private int amountOfBounces;
    
    private Color color;
    
    public Intersection(Vector3 startingPosition, Color objectColor) {
        this.startPosition = startingPosition;
        this.color = objectColor;
        
        amountOfBounces = 1;
    }
    
    public void incrementAmountOfBounces() {
        amountOfBounces++;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void calculateColor(Color lightColor){
        Color objectColor = color;
        
        objectColor.lightReflection(lightColor);
        
        color.setColor(objectColor);
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
