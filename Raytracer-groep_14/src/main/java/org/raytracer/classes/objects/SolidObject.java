package org.raytracer.classes.objects;

import org.raytracer.classes.raycasting.Intersection;
import org.raytracer.classes.raycasting.Ray;
import org.raytracer.classes.vectors.Vector3;

public abstract class SolidObject extends SceneObject implements Material {
    protected float reflection;
    
    protected float emission;
    
    protected Color color;
    //protected Vector3 position;
    
    
    public SolidObject(Vector3 position, Color color, float reflection, float emission) {
        this.position = position;
        this.color = color;
        this.reflection = reflection;
        this.emission = emission;
    }
    
    public abstract Intersection calculateIntersection(Ray ray);
    
    public abstract Vector3 GetNormalAt(Vector3 point); //todo why abstract? Now it doesn't do anything
    
    public Vector3 getPosition() {
        return position;
    }
    
    public void setPosition(Vector3 position) {
        this.position = position;
    }
    
    public Color getColor() {
        return color;
    }
    
    public Color getTextureColor(Vector3 point) {
        return getColor();
    }
    
    public float getReflectivity() {
        return reflection;
    }
    
    public float getEmission() {
        return emission;
    }
    
    public SolidObject getObject() {
        return this;
    }
}