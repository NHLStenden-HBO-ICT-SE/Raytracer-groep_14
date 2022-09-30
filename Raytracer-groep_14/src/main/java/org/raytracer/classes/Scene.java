package org.raytracer.classes;

public class Scene {

	private SceneObject[] objectList;
	private Skybox image;
	private Camera camera;
	private Light light;



	public Scene(SceneObject [] objectList ){
		this.objectList = objectList;
	}

	public void AddSceneObject() {



	}

	public void RemoveSceneObject() {

	}

	public SceneObject[] GetSceneObjectList() {
		return null;
	}

}
