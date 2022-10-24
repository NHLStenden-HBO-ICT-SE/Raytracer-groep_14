package org.raytracer.classes.scenes;

import org.raytracer.classes.objects.Camera;
import org.raytracer.classes.objects.Light;
import org.raytracer.classes.objects.SolidObject;
import org.raytracer.classes.vectors.Vector3;

import java.util.ArrayList;

public class Scene {
    
    private ArrayList<SolidObject> objectList;
    private Camera camera;
    private Light light;
    static Light MainLight;
    
    
    /**
     * adds
     */
    public Scene() {
        this.objectList = new ArrayList<>();
        this.camera = new Camera(400);
        MainLight = new Light(new Vector3(0, 10, 1), 50);
    }
    
    public void addObjectToScene(SolidObject object) {
        this.objectList.add(object);
    }
    
    /**
     * add the object in to the scene
     *
     * @param solidObject
     */
    public void AddSceneObject(SolidObject solidObject) {
        
        this.objectList.add(solidObject);
    }
    
    public void RemoveSceneObjects() {
        this.objectList.clear();
        
    }
    
    //todo niet gebruiken
    public SolidObject getFirstSolidObject() {
        return objectList.get(0);
    }

    public ArrayList<SolidObject> getObjectList() {
        return objectList;
    }

    public int GetObjectListLength() {
        return objectList.size();
    }
    
    
    /**
     * returns the object of given index
     *
     * @param index
     * @return
     */
    public SolidObject GetSceneObject(int index) {
        try {
            return this.objectList.get(index);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public Camera GetCamera(){
        return camera;
    }

    public int getWidthAndHeight(){
        return camera.getWidthAndHeight();
    }
    
}
