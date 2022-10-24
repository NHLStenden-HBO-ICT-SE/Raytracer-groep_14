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
    public Intersection calculateIntersection(Ray ray) {return null;}
    @Override
    public Vector3 GetNormalAt(Vector3 point) {
        return null;
    }
}
