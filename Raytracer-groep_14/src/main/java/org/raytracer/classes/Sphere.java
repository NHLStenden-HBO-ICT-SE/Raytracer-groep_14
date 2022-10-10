package org.raytracer.classes;

public class Sphere extends SolidObject{
    private float radius;

    public Sphere(Vector3 position, float radius, Color color, float reflection, float emission)
    {
        super(position, color, reflection, emission);
        this.radius = radius;
    }



    @Override
    public Vector3 CalculaterIntersection (Ray ray)
    {
        float t = Vector3.dot(position.subtract(ray.getOrigin()), ray.getDirection()); // position - origin and the dot product between t direction
        Vector3 p = ray.getOrigin().add(ray.getDirection().multiply(t)); //gets the center of the object where ray is intersected with

        float y = position.subtract(p).length();
        if (y < radius)// only if the ray hits
        {
            float x = (float) Math.sqrt(radius*radius - y*y);
            float t1 = t - x;
            //System.out.println("The ray hit");
            ray.setColor(getColor());
            //System.out.println("the colour is" + ray.getColor().getBlue());
            if (t1 > 0)
                return ray.getOrigin().add(ray.getDirection().multiply(t1));
            else return null;
        }
        else
        {return null;}
    }

    @Override
    public Vector3 GetNormalAt(Vector3 point) {
        return point.subtract(position).normalize();
    }

}