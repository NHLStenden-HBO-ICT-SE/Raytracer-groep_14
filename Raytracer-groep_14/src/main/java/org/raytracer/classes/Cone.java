package org.raytracer.classes;

public class Cone extends SolidObject {
    private float height;
    private float radius;

    public Cone(Vector3 position, Color color, float radius, float height, float reflection, float emmision)
    {
        super(position, color, reflection, emmision);
        this.radius= radius;
        this.height=height;
    }
}
