package org.raytracer.classes;

public class Sphere extends SolidObject {
    private float radius;
    
    public Sphere(Vector3 position, float radius, Color color, float reflection, float emission) {
        super(position, color, reflection, emission);
        this.radius = radius;
    }
    
    /**
     * @param ray
     * @return
     */
    @Override
    public Vector3 CalculaterIntersection(Ray ray) {
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
                //ray.setColor(getColor());
                //float intence = Scene.MainLight.CalcLightIntencity(p.distanceBetweenPoints(getPosition(), Scene.MainLight.GetPosition()) / 1000);
                //ray.setColor(Scene.MainLight.Lightreflection(getColor().multiply(intence)));
                ray.setColor(getColor());
                return ray.getOrigin().add(ray.getDirection().multiply(t1));
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