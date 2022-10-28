package org.raytracer.classes.objects;

import org.raytracer.classes.vectors.Vector3;

public class Light extends SceneObject {
    private Color color = new Color();
    private float intensity;
    
    /**
     * Standard color for a light source is White
     */
    public Light(Vector3 position, float intensity) {
        this.intensity = intensity;
        this.position = position;
        this.color = color.multiply(intensity);
    }
    
    @Override
    public void SetPosition(Vector3 position) {
        super.SetPosition(position);
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }
    
    public void addIntensity(float intensity) {
        this.intensity += this.intensity + intensity;
    }
}
