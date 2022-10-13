package org.raytracer.classes;

public class Cone extends SolidObject {
    private float height;
    private float radius;
    
    public Cone(Vector3 position, Color color, float radius, float height, float reflection, float emission, Material material) {
        super(position, color, reflection, emission, material);
        this.radius = radius;
        this.height = height;
    }
    
    @Override
    public Vector3 calculateIntersection(Ray ray) {
        return null;
    }


    @Override
    public Intersection CalculaterIntersectionTemp(Ray ray) {return null;}
    @Override
    public Vector3 GetNormalAt(Vector3 point) {
        return null;
    }
}
