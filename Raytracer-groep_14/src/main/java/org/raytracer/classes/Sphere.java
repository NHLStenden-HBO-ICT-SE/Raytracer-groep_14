package org.raytracer.classes;

public class Sphere extends SolidObject{
    private float radius;

    public Sphere(Vector3 position, float radius, Color color, float reflection, float emmision)
    {
        super(position, color, reflection, emmision);
        this.radius = radius;
    }



    public Vector3 IntersectRay (Ray ray)
    {
        float t = Vector3.dot(position.subtract(ray.getOrigin()), ray.getDirection()); // position - origin and the the dot product between t  direction
        Vector3 p = ray.getOrigin().add(ray.getDirection().multiply(t));

        float y = position.subtract(p).length();
        if (y < radius)// if the ray does not hit anything
        {
            float x = (float) Math.sqrt(radius*radius - y*y);
            float t1 = t - x;
            if (t1 > 0)
                return ray.getOrigin().add(ray.getDirection().multiply(t1));
            else return null;
        }
        else
        {return null;}
    }
}
// todo make Intersect for object and then work on scene