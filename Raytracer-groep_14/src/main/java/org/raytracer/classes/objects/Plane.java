package org.raytracer.classes.objects;

import org.raytracer.classes.raycasting.Intersection;
import org.raytracer.classes.raycasting.Ray;
import org.raytracer.classes.vectors.Vector3;

public class Plane extends SolidObject {
    
    
    public Plane(Vector3 position, Color color, float reflection, float emission) {
        super(position, color, emission, reflection);
    }
    
    @Override
    public Intersection calculateIntersection(Ray ray) {
        return null;
    }



    @Override
    public Vector3 GetNormalAt(Vector3 point) {
        return new Vector3(0, 1, 0);
    }
}
