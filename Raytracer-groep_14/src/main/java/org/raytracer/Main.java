package org.raytracer;

import org.raytracer.classes.Quaternion;
import org.raytracer.classes.Vector3;

public class Main {
    public static void main(String[] args) {

        Vector3 vec = new Vector3(1,5,5);
        System.out.println(vec.getAngle(vec, new Vector3(2,2,2)));
        Quaternion angle = new Quaternion();
        System.out.println(angle.getLocalRotation(vec, 0));
        System.out.println(angle.rotateUp(10));
        System.out.println("Hello world!");
    }
}