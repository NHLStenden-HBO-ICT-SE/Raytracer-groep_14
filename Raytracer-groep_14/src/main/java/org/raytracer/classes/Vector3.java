package org.raytracer.classes;

public class Vector3 {

	public float x;
	public float y;
	public float z;

	private SceneObject sceneObject;

	public Vector3(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	//*** Get the current xyz
	public Vector3 GetVector3() {
		return new Vector3(x,y,z);
	}

	public void SetVector(float x, float y, float z) {
	}

	//temp rotation calculations
	public Vector3 RotateVector3(Vector3 vector, Vector3 axis, float degrees){

		return new Vector3(0,0,0);
	}
	public Vector3 RotateUpByDegrees(Vector3 vector, int degrees){

		//max rotation is 360


		return new Vector3(0,0,0);
	}


}
