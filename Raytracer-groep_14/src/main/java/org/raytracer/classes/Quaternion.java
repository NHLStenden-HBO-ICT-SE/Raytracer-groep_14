package org.raytracer.classes;


/**
 * just admit you don't understand it too
 */
public class Quaternion {

    public float x;
    public float y;
    public float z;
    public float w;


    public Quaternion(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
    }
    public Quaternion(float x, float y, float z, float w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public float length() {
        return (float) Math.sqrt(x*x+y*y+z*z+w*w);
    }

    public Quaternion normalize(){

        float length = length();
        return new Quaternion(this.x / length, this.y / length, this.z / length, this.w / length);
    }

}
