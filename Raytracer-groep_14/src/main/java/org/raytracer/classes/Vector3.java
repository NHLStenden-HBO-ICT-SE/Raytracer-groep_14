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
	//Get and return the xyz
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public float getZ() {
		return z;
	}

	//*** Get the current xyz
	public Vector3 GetVector3() {
		return new Vector3(x,y,z);
	}

	public void SetVector(float x, float y, float z) {
	}

	//temp rotation calculations

	/**
	 * Rotate the vector3 up by the given degrees
	 * @param degrees
	 * @return
	 */

	public Vector3 RotateUpByDegrees(int degrees){

		//pitch roll yaw ref chart https://th.bing.com/th/id/R.7581eb166c78861e1717f4dcb58c600f?rik=p02JZWulLC%2f6NA&riu=http%3a%2f%2fi.stack.imgur.com%2f8IuOw.png&ehk=pGMPZxyK9kOd1EoPH5K3L%2fGfDjyVcXCzixV8IOZ7L%2fE%3d&risl=&pid=ImgRaw&r=0
		//rotation based on https://github.com/carl-vbn/pure-java-raytracer/blob/master/src/carlvbn/raytracing/math/Vector3.java
		//use the x axis so the 1st vector
		//- is clockwise while + is counterclockwise
		double yawDegrees = Math.toRadians(degrees);
		//Rotate around the y axis
		//Rotate the x axis first
		float _x = (float) (x*Math.cos(yawDegrees) + z*Math.sin(yawDegrees));
		//Rotate the z axis second
		float _z = (float) (-x*Math.sin(yawDegrees) + z*Math.cos(yawDegrees));
		//calculate the y axis called Yaw
		return new Vector3(_x, y, _z);
	}
	/**
	 *Rotate up with the given vector by the given degrees
	 * @param vector
	 * @param degrees
	 * @return
	 */
	public Vector3 RotateUpByDegrees(Vector3 vector, int degrees){

		//use the x axis so the 1st vector
		//- is clockwise while + is counterclockwise
		double yawDegrees = Math.toRadians(degrees);
		//Rotate around the y axis
		//Rotate the x axis first
		float _x = (float) (vector.x*Math.cos(yawDegrees) + vector.z*Math.sin(yawDegrees));
		//Rotate the z axis second
		float _z = (float) (-vector.x*Math.sin(yawDegrees) + vector.z*Math.cos(yawDegrees));
		//calculate the y axis called Yaw
		return new Vector3(_x, vector.y, _z);
	}

	/**
	 * Rotate to the left with the given Degrees
	 * @param degrees
	 * @return
	 */
	public Vector3 RotateLeftByDegrees(int degrees){
		double pitchDegrees = Math.toRadians(degrees);
		float _y = (float) (y*Math.cos(pitchDegrees) - z * Math.sin(pitchDegrees));
		float _z = (float) (y*Math.sin(pitchDegrees) + z*Math.cos(pitchDegrees));
		return  new Vector3(x, _y, _z);
	}


}
