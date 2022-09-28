package org.raytracer.classes;

public abstract class SolidObject extends SceneObject{
    protected float reflection;

    protected float emmesion;

    protected Color color;
    protected Vector3 position;


    public SolidObject(Vector3 position, Color color, float reflection, float emmesion)
    {
        this.position= position;
        this.color=color;
        this.reflection=reflection;
        this.emmesion=emmesion;
    }

    public Vector3 CalculaterIntersection(Ray ray) { return null; }

    public Vector3 GetNormalAt(Vector3 point) {return null; }
}
