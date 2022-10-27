package org.raytracer.classes.objects;

import org.raytracer.classes.vectors.Vector3;

public class Light extends SceneObject {
    private Color color = new Color();
    
    private float intensity;
    
    
    /**
     * Standaard licht kleur is wit
     */
    public Light(Vector3 position, float intensity) {
        this.intensity = intensity;
        this.position = position;
        this.color = color.multiply(intensity);
    }
    
    
    /**
     * kleur van het licht moet worden meegegeven
     */
    public Light(Vector3 position, float intensity, float red, float green, float blue) {
        this.intensity = intensity;
        this.position = position;
        this.color = new Color(red, green, blue).multiply(this.intensity);
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
