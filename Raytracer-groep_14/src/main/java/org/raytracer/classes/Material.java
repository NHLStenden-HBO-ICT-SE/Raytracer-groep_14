package org.raytracer.classes;

//import UCD.SolidObject;

public class Material {
    private int glossiness;
    
    private Material(int glossiness){
        this.glossiness = glossiness;
        }

    public static final Material superGlossy = new Material(200);
}
