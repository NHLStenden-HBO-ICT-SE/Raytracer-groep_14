package org.raytracer.classes.objects;

import org.raytracer.classes.raycasting.Intersection;
import org.raytracer.classes.raycasting.Ray;
import org.raytracer.classes.vectors.Vector3;

public class Cone extends SolidObject {
    private float height;
    private float radius;
    
    public Cone(Vector3 position, Color color, float radius, float height, float reflection, float emission) {
        super(position, color, reflection, emission);
        this.radius = radius;
        this.height = height;
    }
    
    @Override
    public Intersection calculateIntersection(Ray ray) {
        return null;
    }
    
    //todo
    @Override
    public boolean getsHitByRay(Ray ray) {
        return false;
    }
    
    //todo
    @Override
    public float distanceToIntersection(Ray ray) {
        return 0;
    }
    
    @Override
    public Vector3 GetNormalAtIntersection(Vector3 point) {
        return null;
    }
}
