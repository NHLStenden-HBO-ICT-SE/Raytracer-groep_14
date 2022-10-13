package org.raytracer.classes;

public class Sphere extends SolidObject {
    private float radius;
    
    public Sphere(Vector3 position, float radius, Color color, float reflection, float emission) {
        super(position, color, reflection, emission);
        this.radius = radius;
    }
    

    /**
     *
     * @param ray
     * @return
     */
    @Override
    public Intersection calculateIntersection(Ray ray) {
        float scalar = calculateScalar(ray); // position - origin and the dot product between t direction
        Vector3 objectCenter = ray.getOrigin().add(ray.getDirection().multiply(scalar)); //gets the center of the
        // object where ray is intersected with
        float y = position.subtract(position).length();
        
        if (y < radius)// only if the ray hits
        {
            float sphereSize = (float) Math.sqrt(radius * radius - y * y);
            float t1 = scalar - sphereSize;
            if (t1 > 0) {
                //todo Intersection intersect = new Intersection();
               // return intersect;

            }
            return null;
        } else {
            return null;
        }
    }
    
    public Intersection intersectionWithRay(Ray ray) {
     return null; //todo
    }
    
    private Vector3 pointClosestToCenterOfRay(Ray ray){
        return null;
    }
    
    private Vector3 pointOnRay (Ray ray, float distance) {
        return ray.getOrigin().add(ray.getDirection().multiply(distance));
    }
    
    /**
     *
     * @param ray
     * @return
     */
    private float calculateScalar(Ray ray){
        return Vector3.dot(distanceBetweenCenterAndRayOrigin(ray), ray.getDirection());
    }
    
    private Vector3 distanceBetweenCenterAndRayOrigin(Ray ray){
        return position.subtract(ray.getOrigin());
    }
    
    @Override
    public Vector3 GetNormalAt(Vector3 point) {
        return point.subtract(position).normalize();
    }
    
}