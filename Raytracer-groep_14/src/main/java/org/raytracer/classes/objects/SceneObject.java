package org.raytracer.classes.objects;


import org.raytracer.classes.vectors.Vector3;

public abstract class SceneObject {
    
    protected Vector3 position;
    
    private Vector3 rotation;
    
    private Vector3 scale;
    
    public void SetPosition(Vector3 position) {
        this.position = position;
    }
    
    public Vector3 GetPosition() {
        return position;
    }
    
    
}
