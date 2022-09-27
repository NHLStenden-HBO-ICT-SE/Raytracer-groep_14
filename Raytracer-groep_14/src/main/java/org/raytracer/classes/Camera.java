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
        this.center = position.add(direction.multiply(fieldOfView));
        this.topLeft = center.add(new Vector3(-1,1,0));
        this.bottomLeft = center.add(new Vector3(-1,-1,0));
        this.topRight = center.add(new Vector3(1,1,0));
    }

    public float getFieldOfView() {
        return fieldOfView;
    }

    public Vector3 getBottomLeft() {
        return bottomLeft;
    }

    public Vector3 getCenter() {
        return center;
    }

    public Vector3 getDirection() {
        return direction;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getTopLeft() {
        return topLeft;
    }

    public Vector3 getTopRight() {
        return topRight;
    }

    public void setFieldOfView(float fieldOfView) {
        this.fieldOfView = fieldOfView;
        this.center = position.add(direction.multiply(fieldOfView));
    }

    public Vector3 getHorizontalPosition(){
        
    }
}

