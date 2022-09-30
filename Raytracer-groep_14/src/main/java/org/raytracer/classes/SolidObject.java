package org.raytracer.classes;

public abstract class SolidObject extends SceneObject{
    protected float reflection;

    protected float emmision;

    protected Color color;
    protected Vector3 position;


    public SolidObject(Vector3 position, Color color, float reflection, float emmesion)
    {
        this.position= position;
        this.color=color;
        this.reflection=reflection;
        this.emmision=emmesion;
    }

    public abstract Vector3 CalculaterIntersection(Ray ray);

    public  abstract Vector3 GetNormalAt(Vector3 point);

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
        return emmision;
    }
}
