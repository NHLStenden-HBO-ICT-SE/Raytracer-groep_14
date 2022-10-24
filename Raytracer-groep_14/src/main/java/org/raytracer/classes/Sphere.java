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
    public Intersection calculateIntersection(Ray ray) {
        Vector3 rayOriginToSphereCenter = calculateRayOriginToSphereCenter(ray);
        float projectionSphereCenterToRay = rayOriginToSphereCenter.dot(ray.getNormalizedDirection());
        Vector3 projectionOfRayToCenter =
                rayOriginToSphereCenter.subtract(ray.getOrigin().multiply(projectionSphereCenterToRay));
        float shortestDistanceRayToCenter = projectionOfRayToCenter.dot(projectionOfRayToCenter);
        
        if (rayGoesThroughSphere(shortestDistanceRayToCenter)) {
            float distanceToIntersection =
                    (float) (projectionSphereCenterToRay - Math.sqrt(radius * radius - shortestDistanceRayToCenter));
            if (distanceToIntersection < projectionSphereCenterToRay && projectionSphereCenterToRay > 0) {
                return new Intersection(ray.getPointOnRay(distanceToIntersection), distanceToIntersection);
            }
        }
        return null;
    }
    
    private boolean rayGoesThroughSphere(float shortestDistanceRayToCenter) {
        return shortestDistanceRayToCenter < radius * 2;
    }
    
    
    private Vector3 calculateRayOriginToSphereCenter(Ray ray) {
        return position.subtract(ray.getOrigin());
    }
    
    @Override
    public Vector3 GetNormalAt(Vector3 point) {
        return point.subtract(position).normalize();
    }
    
}