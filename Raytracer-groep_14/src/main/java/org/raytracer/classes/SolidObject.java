package org.raytracer.classes;

public abstract class SolidObject{
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

    //todo Calculate distance between object that intersects and the camera
    public abstract Vector3 CalculaterIntersection(Ray ray);

    public abstract Vector3 GetNormalAt(Vector3 point);

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
}
