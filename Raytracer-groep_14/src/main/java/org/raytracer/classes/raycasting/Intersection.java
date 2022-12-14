package org.raytracer.classes.raycasting;

import org.raytracer.classes.objects.Camera;
import org.raytracer.classes.objects.Color;
import org.raytracer.classes.objects.Light;
import org.raytracer.classes.objects.SolidObject;
import org.raytracer.classes.vectors.Vector3;

import java.util.ArrayList;

public class Intersection {
    private float distanceToCameraOrigin;
    private Vector3 startPosition;
    private Vector3 lightPosition = null;
    private int amountOfBounces;
    private Color color;
    private float distanceToLightSource = 0;

    public Intersection(Vector3 startingPosition,  Color objectColor) {
        this.startPosition = startingPosition;
        this.color = objectColor;
        amountOfBounces = 5;
    }
    public void incrementAmountOfBounces() {
        amountOfBounces++;
    }

    public float getDistanceToCameraOrigin(Camera cam) {
        if (distanceToCameraOrigin == 0){
            distanceToCameraOrigin = startPosition.distanceBetweenPoints(cam.GetPosition());
        }
        return distanceToCameraOrigin;
    }
    public void setLightPosition(Vector3 lightPosition) {
        this.lightPosition = lightPosition;
        distanceToLightSource = lightPosition.distanceBetweenPoints(startPosition);
    }

    public void setColor(Color color){
        this.color = color;
    }
    public Color getColor() {
        return color;
    }

    public float getDistanceToLightSource(){
        return distanceToLightSource;
    }
    public float getDistanceToLightSource(Light light){
        distanceToLightSource = light.GetPosition().distanceBetweenPoints(startPosition);
        return distanceToLightSource;
    }

    public void calculateColor(Color lightColor, float distanceToLightSource) {
        Color objectColor = color;

        objectColor.lightReflection(lightColor, distanceToLightSource);

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
