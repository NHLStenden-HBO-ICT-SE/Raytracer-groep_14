package org.raytracer.classes;

public class Sphere extends SolidObject{
    private float radius;

    public Sphere(Vector3 position, float radius, Color color, float reflection, float emmision)
    {
        super(position, color, reflection, emmision);
        this.radius= radius;
    }
}
