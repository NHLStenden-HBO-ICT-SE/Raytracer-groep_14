package org.raytracer.classes;

public class RayHit {

    private Ray ray;
    private Vector3 hitPos;
    private Vector3 normal;
    private SolidObject solidObject;

    public RayHit(Ray ray, SolidObject solidObject, Vector3 hitPos){

        this.ray = ray;
        this.solidObject = solidObject;
        this.hitPos = hitPos;
        this.normal = solidObject.getNormalAt(hitPos);
    }

    public Ray getRay() {
        return ray;
    }

    public Vector3 getHitPos() {
        return hitPos;
    }

    public Vector3 getNormal() {
        return normal;
    }
    public SolidObject getSolidObject() {
        return solidObject;
    }
}
