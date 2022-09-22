package org.raytracer.classes.RenderSupport;

import org.raytracer.classes.Color;

/**
 * this class contains and stores data needed for rendering
 * Based on https://github.com/carl-vbn/pure-java-raytracer/blob/master/src/carlvbn/raytracing/pixeldata/PixelData.java
 */
public class PixelData {

    private Color color;
    private float depht;
    private float emmison;

    public PixelData(Color color, float depht, float emmison){
        this.color = color;
        this.depht = depht;
        this.emmison = emmison;
    }

    public Color getColor(){
        return color;
    }

    public float getDepht() {
        return depht;
    }

    public float getEmmison() {
        return emmison;
    }
    public void add(PixelData otherData){

    }
}
