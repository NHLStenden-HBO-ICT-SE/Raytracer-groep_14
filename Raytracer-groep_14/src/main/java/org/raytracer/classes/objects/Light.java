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
        this.color = new Color().multiply(intensity);
    }
    
    
    /**
     * kleur van het licht moet worden meegegeven
     */
    public Light(Vector3 position, float intensity, float red, float green, float blue) {
        this.intensity = intensity;
        this.position = position;
        this.color = new Color(red, green, blue).multiply(this.intensity);
    }
    
    
    /**
     * Berekenen lichtintensiteit
     */
    public float CalcLightIntencity(float objectDistance) {
        
        double distance = Math.pow(objectDistance, 2);
        
        double lightIntensity = 1 / distance;
        
        return (float) lightIntensity;
        
    }
    
    public Color getColor(){
        return color;
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
