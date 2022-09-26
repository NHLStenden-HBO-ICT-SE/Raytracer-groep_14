package org.raytracer.classes;

public class Camera {
    private float fieldOfView = 50;
    private Vector3 position; //ToDo add position of (0,0,0)
    private Vector3 direction = new Vector3(0,0,0);
    private Vector3 center;
    private Vector3 topLeft, topRight, bottomLeft;
    private float yaw, pitch;

    public Camera(Vector3 position) {

        this.position = position;
    }
    public Camera(Vector3 position, Vector3 direction){
        this.position = position;
        this.direction = direction;
    }

    public float getFieldOfView() {
        return fieldOfView;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }


    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }
}
