package org.raytracer.classes;

/**
 * Solidobject class used for rendering and collison
 * uses code from https://github.com/carl-vbn/pure-java-raytracer/blob/master/src/carlvbn/raytracing/solids/Solid.java
 */
public abstract class SolidObject {

    protected Vector3 position;
    protected Color color;
    protected float reflectivity;
    protected float emission;

    public SolidObject(Vector3 position, Color color, float reflectivity, float emission){
        this.position = position;
        this.color = color;
        this.reflectivity = reflectivity;
        this.emission = emission;
    }

    public abstract Vector3 calculateIntersection(Ray ray);
    public abstract Vector3 getNormalAt(Vector3 point);

    public Vector3 getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public float getEmission() {
        return emission;
    }

    public float getReflectivity() {
        return reflectivity;
    }
    public Color getTextureColor(Vector3 point){
        return getColor();
    }
}
