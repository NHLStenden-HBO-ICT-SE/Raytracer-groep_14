package org.raytracer.classes.objects;

import org.raytracer.classes.vectors.Vector3;

public class Light extends SceneObject {
    private Color color;
    
    private float intensity;
    
    
    /**
     * Standaard licht kleur is wit
     */
    public Light(Vector3 position, float intensity) {
        this.intensity = intensity;
        this.position = position;
        this.color = new Color(1, 0, 0.1f);
    }
    
    
    /**
     * kleur van het licht moet worden meegegeven
     */
    public Light(Vector3 position, float intencity, float red, float green, float blue) {
        this.intensity = intencity;
        this.position = position;
        this.color = new Color(red, green, blue);
    }
    
    
    /**
     * Berekenen lichtintensiteit
     */
    public float CalcLightIntencity(float objectDistance) {
        
        double distance = Math.pow(objectDistance, 2);
        
        double lightIntensity = 1 / distance;
        
        return (float) lightIntensity;
        
    }
    
    // calculate the reflection
    //reflectie = kleurlicht * kleurobject
    public Color Lightreflection(Color objectColor) {
        
        float colorred = color.getRed();
        float colorblue = color.getBlue();
        float colorgreen = color.getGreen();
        
        float lightred = objectColor.getRed();
        float lightblue = objectColor.getBlue();
        float lightgreen = objectColor.getGreen();
        
        float reflectred = colorred * lightred;
        float reflectblue = Math.min(colorblue * lightblue, 1);
        float reflectgreen = colorgreen * lightgreen;
        
        return new Color(reflectred, reflectgreen, reflectblue);
    }
    
    //intensiteit licht invals hoek is cos a oftewel
    //dot product van de normaal en licht vector
    public float AngleOfView(Vector3 light, Vector3 normalVector) {
        
        normalVector = normalVector.normalize();
    
        return light.dot(normalVector);
    }
    
    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }
}
