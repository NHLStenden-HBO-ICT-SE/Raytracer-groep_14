package org.raytracer.classes;
import java.util.Collection;
import java.util.List;
public class SceneManager {

	private Scene[] scenes;
	private List<Scene> sceneList;

	public void AddScene(Scene scene) {


	}

	public List<Scene> getSceneList() {
		return sceneList;
	}
	public void addScene(Scene scene){
		sceneList.add(scene);
	}

	public void setSceneList(List<Scene> sceneList) {
		this.sceneList = sceneList;
	}

	/**
	 * create a scene with an enviremont that will be rendered
	 */
	public void SetupScene(){
		Scene nScene = new Scene();


	}



}
