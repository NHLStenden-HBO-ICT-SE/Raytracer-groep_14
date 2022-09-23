package org.raytracer.classes;

public class Vector3 {

	private SceneObject sceneObject;
	public float x;
	public float y;
	public float z;

	private SceneObject _sceneObject;

	public Vector3(){
		this(0,0,0);
	}
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

	public float length() {
		return (float) Math.sqrt(x*x+y*y+z*z);
	}
	public Vector3 add(Vector3 vec) {
		return new Vector3(this.x + vec.x, this.y + vec.y, this.z + vec.z);
	}

	public Vector3 subtract(Vector3 vector) {
		return new Vector3(this.x - vector.x, this.y - vector.y, this.z - vector.z);
	}
	public Vector3 normalize() {
		float length = length();
		return new Vector3(this.x / length, this.y / length, this.z / length);
	}
	public static float dot(Vector3 a, Vector3 b) {
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}
	public void SetVector(float x, float y, float z) {
	}

	public Vector3 multiply(float scalar) {
		return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
	}

	public Vector3 multiply(Vector3 vec) {
		return new Vector3(this.x * vec.x, this.y * vec.y, this.z * vec.z);
	}
	public static float distance(Vector3 a, Vector3 b) {
		return (float) Math.sqrt(Math.pow(a.x - b.x, 2)+Math.pow(a.y - b.y, 2)+Math.pow(a.z - b.z, 2));
	}

	//temp rotation calculations

	/**
	 *
	 * @param yaw
	 * @param pitch
	 * @return
	 */

	public Vector3 rotateYP(float yaw, float pitch) {
		// Convert to radians
		double yawRads = Math.toRadians(yaw);
		double pitchRads = Math.toRadians(pitch);

		// Step one: Rotate around X axis (pitch)
		float _y = (float) (y*Math.cos(pitchRads) - z*Math.sin(pitchRads));
		float _z = (float) (y*Math.sin(pitchRads) + z*Math.cos(pitchRads));

		// Step two: Rotate around the Y axis (yaw)
		float _x = (float) (x*Math.cos(yawRads) + _z*Math.sin(yawRads));
		_z = (float) (-x*Math.sin(yawRads) + _z*Math.cos(yawRads));

		return new Vector3(_x, _y, _z);
	}
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

