package org.raytracer.classes;

public class Plane extends SolidObject{


    public Plane(Vector3 position, Color color, float reflection, float emmision)
    {
        super(position, color, emmision, reflection);
    }

    @Override
    public Vector3 CalculaterIntersection(Ray ray)
    {
        float t = -(ray.getOrigin().getY() - position.getY()) / ray.getDirection().getY();
        if (t > 0)//infront of the ray
        {
            return  ray.getOrigin().add(ray.getDirection().multiply(t)); // get far from the object and multiply the view
        }
        return null;
    }

    @Override
    public Vector3 GetNormalAt(Vector3 point) {
        return new Vector3(0, 1, 0);
    }
}