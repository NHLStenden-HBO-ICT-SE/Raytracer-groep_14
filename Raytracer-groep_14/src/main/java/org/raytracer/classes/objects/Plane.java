package org.raytracer.classes.objects;

import org.raytracer.classes.raycasting.Intersection;
import org.raytracer.classes.raycasting.Ray;
import org.raytracer.classes.vectors.Vector3;

public class Plane extends SolidObject {


    public Plane(Vector3 position, Color color, float reflection, float emission) {
        super(position, color, emission, reflection);
    }


    /***
     * calculate the intersection between ray and plane
     * @param ray
     * @return
     */
    @Override
    public Intersection calculateIntersection(Ray ray) {
        float intersectionPoint = -(ray.getOrigin().getY() - position.getY()) / ray.getDirection().getY();
        if (intersectionPoint > 0 && Float.isFinite(intersectionPoint))//otherwise is the ray perpendicular to plane which does not intersect
        {
            return new Intersection(ray.getOrigin().add(ray.getDirection().multiply(intersectionPoint)), intersectionPoint);
        }
        return null;
    }



    @Override
    public Vector3 GetNormalAt(Vector3 point) {
        return new Vector3(0, 1, 0);
    }
}
