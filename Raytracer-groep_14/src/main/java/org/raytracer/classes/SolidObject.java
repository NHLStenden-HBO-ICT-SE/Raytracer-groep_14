package org.raytracer.classes;

public abstract class SolidObject extends SceneObject {
    protected float reflection;

    protected float emission;

    protected Color color;
    protected Vector3 position;


    public SolidObject(Vector3 position, Color color, float reflection, float emission) {
        this.position = position;
        this.color = color;
        this.reflection = reflection;
        this.emission = emission;
    }

    public abstract Vector3 CalculaterIntersection(Ray ray); //todo why abstract? Now it doesn't do anything

    public abstract Vector3 GetNormalAt(Vector3 point);//todo why abstract? Now it doesn't do anything

    public Vector3 getPosition() {
        return position;
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
}
