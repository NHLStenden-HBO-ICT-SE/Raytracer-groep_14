package org.raytracer.classes;

public class Rectangle extends SolidObject{
    float length;
    float width;

    public Rectangle(Vector3 position, Color color, float length, float width, float reflection, float emmision)
    {
        super(position, color, emmision, reflection);
        this.length= length;
        this.width= width;

    }
}
