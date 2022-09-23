package org.raytracer.classes;

public class Light {
    private Color colour;

    private Vector3 position;

    private float Intecity;
    public Light(Vector3 position){
        this.position = position;
    }

    public void SetIntencity(int Brightness){

    }

    public Color getColour() {
        return colour;
    }

    public Vector3 getPosition() {
        return position;
    }

    public float getIntecity() {
        return Intecity;
    }

}
