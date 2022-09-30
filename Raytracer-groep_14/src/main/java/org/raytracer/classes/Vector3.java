package org.raytracer.classes;

import sun.net.www.content.text.Generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Vector3 {

	private SceneObject sceneObject;
	private float x;
	private float y;
	private float z;

	private SceneObject _sceneObject;

	public Vector3(){
		this(0,0,0);
	}
	public Vector3(Vector3 vec){
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
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

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setZ(float z) {
		this.z = z;
	}

	//*** Get the current xyz
	public Vector3 GetVector3() {
		return new Vector3(x,y,z);
	}

	public void SetVector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}



	/**
	 * Subtract the local vector by the given one
	 * @param vector to subtract with
	 * @return
	 */
	public Vector3 subtract(Vector3 vector) {
		return new Vector3(this.x - vector.x, this.y - vector.y, this.z - vector.z);
	}


	//todo make a multiply method using a float and vector
	/**
	 * add one vector 3 to the current one
	 * @param vec1 vector 3 to add
	 * @return
	 */
	public Vector3 add(Vector3 vec1){

		return new Vector3((vec1.x + this.x), (vec1.y + this.y), (vec1.z + this.z));
	}
	/**
	 * Add two vector 3's together
	 * @param vec1 the first vector 3
	 * @param vec2 the second vector 3
	 * @return
	 */
	public static Vector3 add(Vector3 vec1, Vector3 vec2){

		return new Vector3((vec1.x + vec2.x), (vec1.y + vec2.y), (vec1.z + vec2.z));
	}

	/**
	 * Multiply the local vector 3 with a given float called the scalar
	 * @param scalar float to multiply with
	 * @return scaled Vector 3
	 */
	public Vector3 multiply(float scalar) {
		return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
	}

	/**
	 * Multiply the local vector 3 with another vector 3
	 * @param vec Vector 3 to scale with
	 * @return scaled Vector 3
	 */
	public Vector3 multiply(Vector3 vec) {
		return new Vector3(this.x * vec.x, this.y * vec.y, this.z * vec.z);
	}
	//temp rotation calculations

	public Vector3 divide(int value) {
		return new Vector3(x / value, y/value, z/value);
	}
	
	/**
	 * get the length of the current vector3
	 * @return the length of the current vector3
	 */
	public float length() {
		return (float) Math.sqrt(x*x+y*y+z*z);
	}

	/**
	 * Normalize the current vector
	 * @return a normilized vector 3
	 */
	public Vector3 normalize() {
		float length = length();
		return new Vector3(this.x / length, this.y / length, this.z / length);
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

