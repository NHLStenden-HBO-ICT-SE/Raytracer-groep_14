package org.raytracer.classes;

public class Sphere extends SolidObject {
    private float radius;
    
    public Sphere(Vector3 position, float radius, Color color, float reflection, float emission, Material material) {
        super(position, color, reflection, emission, material);
        this.radius = radius;
    }
    

    /**
     * Calculate the intersection and return a intersection class
     * @param ray
     * @return
     */
    @Override
    public Intersection calculateIntersection(Ray ray) {

        float t = Vector3.dot(position.subtract(ray.getOrigin()), ray.getDirection()); // position - origin and the dot product between t direction
        Vector3 p = ray.getOrigin().add(ray.getDirection().multiply(t)); //gets the center of the object where ray is intersected with

        float y = position.subtract(p).length();
        if (y < radius)// only if the ray hits
        {
            float sphereSize = (float) Math.sqrt(radius * radius - y * y);
            float t1 = t - sphereSize;
            //System.out.println("The ray hit");
            //System.out.println("the colour is" + ray.getColor().getBlue());
            if (t1 > 0) {
                Intersection intersect = new Intersection(ray.getOrigin(), p.distanceBetweenPoints(getPosition(), Scene.MainLight.GetPosition()));
                intersect.setSolidObject(getObject());
                return intersect;
            }
            return null;
        } else {
            return null;
        }
    }
    
    @Override
    public Vector3 GetNormalAt(Vector3 point) {
        return point.subtract(position).normalize();
    }
    
}