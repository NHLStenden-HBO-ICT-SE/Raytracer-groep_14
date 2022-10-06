package org.raytracer.classes;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Scene {

	//comment
	private ArrayList<SolidObject> objectList;
	private Skybox image; // todo add skybox
	private Camera camera;
	private Light light;



	public Scene(){
		this.objectList = new ArrayList<>();
		this.camera=new Camera();
		this.light= new Light();
	}


	/**
	 * add the object in to the scene
	 * @param solidObject
	 */
	public void AddSceneObject(SolidObject solidObject) {

		this.objectList.add(solidObject);
	}

	/**
	 * remove objects
	 */
	public void RemoveSceneObject() {
		this.objectList.clear();

	}

	/**
	 * returns the object of given index
	 * @param index
	 * @return
	 */
	public SceneObject GetSceneObjectList(int index) {
		try {
			return this.objectList.get(index);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		}
	}

}
