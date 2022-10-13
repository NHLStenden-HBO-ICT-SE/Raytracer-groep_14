package org.raytracer.classes;

public class Plane extends SolidObject {
    
    
    public Plane(Vector3 position, Color color, float reflection, float emission, Material material) {
        super(position, color, emission, reflection, material);
    }
    
    @Override
    public Vector3 calculateIntersection(Ray ray) {
        float t = -(ray.getOrigin().getY() - position.getY()) / ray.getDirection().getY();
        if (t > 0)//in front of the ray
        {
            return ray.getOrigin().add(ray.getDirection().multiply(t)); // get far from the object and multiply the view
        }
        return null;
    }

    @Override
    public Intersection CalculaterIntersectionTemp(Ray ray) {
        return null;
    }



    @Override
    public Vector3 GetNormalAt(Vector3 point) {
        return new Vector3(0, 1, 0);
    }
}
