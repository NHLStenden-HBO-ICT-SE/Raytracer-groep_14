package org.raytracer.classes.objects;

import org.raytracer.classes.raycasting.Intersection;
import org.raytracer.classes.raycasting.Ray;
import org.raytracer.classes.vectors.Vector3;

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
        Vector3 rayDirectionDistance = ray.getDirection().multiply(projectionSphereCenterToRay);
        Vector3 projectionOfRayToCenter =
                rayOriginToSphereCenter.subtract(rayDirectionDistance);
        float shortestDistanceRayToCenter = projectionOfRayToCenter.dot(projectionOfRayToCenter);
        
        if (rayGoesThroughSphere(shortestDistanceRayToCenter)) {
            double f = (projectionSphereCenterToRay - Math.sqrt(radius * radius - shortestDistanceRayToCenter));
            float distanceToIntersection = (float) f;
            //distanceToIntersection = getRidOfShadowAcne(distanceToIntersection);
            
            boolean cameraIsOutsideOfSphere =
                    distanceToIntersection < projectionSphereCenterToRay && projectionSphereCenterToRay > 0;
            
            if (cameraIsOutsideOfSphere) {
                return new Intersection(ray.getPointOnRay(distanceToIntersection), color);
            }
        }
        return null;
    }
    
    /**
     * checks if a ray hits an SolidObject
     * @param ray
     * @return boolean
     */
    @Override
    public boolean getsHitByRay(Ray ray) {
        Vector3 rayOriginToSphereCenter = calculateRayOriginToSphereCenter(ray);
        float projectionSphereCenterToRay = rayOriginToSphereCenter.dot(ray.getNormalizedDirection());
        Vector3 rayDirectionDistance = ray.getDirection().multiply(projectionSphereCenterToRay);
        Vector3 projectionOfRayToCenter =
                rayOriginToSphereCenter.subtract(rayDirectionDistance);
        float shortestDistanceRayToCenter = projectionOfRayToCenter.dot(projectionOfRayToCenter);
        
        return rayGoesThroughSphere(shortestDistanceRayToCenter);
    }
    
    //todo
    @Override
    public float distanceToIntersection(Ray ray) {
        return 0;
    }
    
    /**
     * shortens the distance by a fraction to make sure the point is just outside the object. This solves shadow acne
     * bug.
     *
     * @param distanceToIntersection the distance from origin to the
     * @return
     */
    private static float getRidOfShadowAcne(float distanceToIntersection) {
        return distanceToIntersection -= 0.0001;
    }
    
    private boolean rayGoesThroughSphere(float shortestDistanceRayToCenter) {
        boolean rayGoesThroughSphere = shortestDistanceRayToCenter < (radius * 2);
        return rayGoesThroughSphere;
    }
    
    private Vector3 calculateRayOriginToSphereCenter(Ray ray) {
        return position.subtract(ray.getOrigin());
    }
    
    @Override
    public Vector3 GetNormalAtIntersection(Vector3 point) {
        return point.subtract(position).normalize();
    }
    
}