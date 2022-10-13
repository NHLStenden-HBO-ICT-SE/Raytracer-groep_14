package org.raytracer.classes;


public abstract class SceneObject {
    
    protected Vector3 position;
    
    private Vector3 rotation;
    
    private Vector3 scale;
    
    public void SetPosition(Vector3 position) {
        
        this.position = position;
    }
    
    public void SetPosition(float x, float y, float z) {
    
    }
    
    public Vector3 GetPosition() {
        return position;
    }
    
    public void Rotate(Vector3 direction) {
    
    }
    
    public void Rotate(float x, float y, float z) {
    
    }
    
    public void SetScale(Vector3 scale) {
    
    }
    
}
