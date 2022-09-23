package org.raytracer.classes;
import java.util.Collection;
import java.util.List;
import javax.swing.*;
public class SceneManager {

	private Scene[] scenes;
	private List<Scene> sceneList;
	JFrame frame;

	public SceneManager(){

		frame = testFrame("mainframe");
	}
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
		Camera camera = new Camera();
		Light light = new Light(new Vector3(0,0,10));
		Scene nScene = new Scene(camera, light);
		BoxObject firstBox = new BoxObject(new Vector3(0,0,10), new Vector3(1,1,1), Color.RED, 1, 1);
		nScene.addObects(firstBox);
	}

	public JFrame testFrame(String name){

		JFrame frame1 = new JFrame(name);
		frame1.setSize(900,900);
		frame1.setVisible(true);
		return  frame1;

	}



}
