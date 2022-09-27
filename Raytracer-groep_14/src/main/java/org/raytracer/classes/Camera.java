package org.raytracer.classes;

public class Camera {
    private float fieldOfView;
    private Vector3 position;
    private Vector3 direction;
    private Vector3 center;
    private Vector3 topLeft, topRight, bottomLeft;

    public Camera() {
        this.position = new Vector3(0, 0, 0);
        this.direction = new Vector3(1, 0, 0);
        this.fieldOfView = 50;
        //this.center = position.add(direction.)
    }
}
