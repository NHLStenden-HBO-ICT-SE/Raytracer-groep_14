package org.raytracer.classes;

public class Camera {
    private float fieldOfView;
    private Vector3 position; //ToDo add position of (0,0,0)
    private float pitch;
    private float yaw;
    private Vector3 center;
    private Vector3 topLeft, topRight, bottomLeft;

    public Camera() {
        this.position = new Vector3(0,0,0);
    }
}
