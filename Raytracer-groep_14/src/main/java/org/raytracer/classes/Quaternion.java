package org.raytracer.classes;


/**
 * just admit you don't understand it too
 */
public class Quaternion {

    public float x; //w
    public float y; //i
    public float z; //j
    /**
     * Fake dimension
     */
    public float w; // the amount to rotate over the axis, shouldn't be able to turn more then 180


    /*
        Let Q1 and Q2 be two quaternions, which are defined, respectively, as (w1, x1, y1, z1) and (w2, x2, y2, z2).
        (Q1 * Q2).w = (w1w2 - x1x2 - y1y2 - z1z2)
        (Q1 * Q2).x = (w1x2 + x1w2 + y1z2 - z1y2)
        (Q1 * Q2).y = (w1y2 - x1z2 + y1w2 + z1x2)
        (Q1 * Q2).z = (w1z2 + x1y2 - y1x2 + z1w2
     */

    //q = w + xi + yj + zk
    //w should be rotation
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
