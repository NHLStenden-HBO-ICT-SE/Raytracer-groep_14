package org.raytracer.classes;

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
