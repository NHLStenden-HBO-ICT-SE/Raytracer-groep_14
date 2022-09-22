package org.raytracer.classes;

public class RayHit {

    private Ray ray;
    private Vector3 hitPos;
    private Vector3 normal;


    public Ray getRay() {
        return ray;
    }

    public Vector3 getHitPos() {
        return hitPos;
    }

    public Vector3 getNormal() {
        return normal;
    }
}
