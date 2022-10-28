package org.raytracer.classes.vectors;

//<<<<<<< HEAD
//=======
////import sun.net.www.content.text.Generic;
//>>>>>>> 19d756d81073be1f5baf99a227437090257b03a3

public class Vector3 {
    
    private float x;
    private float y;
    private float z;
    
    public Vector3() {
        this(0, 0, 0);
    }
    
    public Vector3(Vector3 vec) {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
    }
    
    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Dot point between two vectores
     *
     * @param b Vector that is used to calculate dot
     * @return <a href="https://www.khanacademy.org/math/multivariable-calculus/thinking-about-multivariable-function/x786f2022:vectors-and-matrices/a/dot-products-mvc">...</a>
     */
    public float dot(Vector3 b) {
        return x * b.x + y * b.y + z * b.z;
    }
    
    //Get and return the xyz
    public float getX() {
        return x;
    }
    
    public void setX(float x) {
        this.x = x;
    }
    
    public float getY() {
        return y;
    }
    
    public void setY(float y) {
        this.y = y;
    }
    
    public float getZ() {
        return z;
    }
    
    public void setZ(float z) {
        this.z = z;
    }
    
    public float[] returnASArry() {
        return new float[]{this.x, this.y, this.z};
    }
    
    //*** Get the current xyz
    public Vector3 GetVector3() {
        return new Vector3(x, y, z);
    }
    
    public void SetVector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    
    //todo make a multiply method using a float and vector
    
    /**
     * Subtract the local vector by the given one
     *
     * @param vector to subtract with
     * @return
     */
    public Vector3 subtract(Vector3 vector) {
        return new Vector3(this.x - vector.x, this.y - vector.y, this.z - vector.z);
    }
    
    /**
     * add one vector 3 to the current one
     *
     * @param vec1 vector 3 to add
     * @return
     */
    public Vector3 add(Vector3 vec1) {
        
        return new Vector3((vec1.x + this.x), (vec1.y + this.y), (vec1.z + this.z));
    }
    
    /**
     * Multiply the local vector 3 with a given float called the scalar
     *
     * @param scalar float to multiply with
     * @return scaled Vector 3
     */
    public Vector3 multiply(float scalar) {
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }
    //temp rotation calculations
    
    /**
     * Multiply the local vector 3 with another vector 3
     *
     * @param vec Vector 3 to scale with
     * @return scaled Vector 3
     */
    public Vector3 multiply(Vector3 vec) {
        return new Vector3(this.x * vec.x, this.y * vec.y, this.z * vec.z);
    }
    
    public Vector3 divide(int value) {
        return new Vector3(x / value, y / value, z / value);
    }
    

	/*
	public float distanceBetweenPoints(Vector3 vector1, Vector3 vector2){
		vector1 = vector1.subtract(vector1, vector2);
		float distance = (float) Math.sqrt((vector1.x * vector1.x) + (vector1.y * vector1.y) + (vector1.z + vector1.z));
		return distance;
	}
	 */
    public float distanceBetweenPoints(Vector3 b) {
        return (float) Math.sqrt(Math.pow((x - b.x), 2) + Math.pow((y - b.y), 2) + Math.pow((z - b.z), 2));
    }
    public float distanceBetweenPoints(Vector3 a, Vector3 b) {
        return (float) Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2) + Math.pow(a.z - b.z, 2));
    }
    
    /**
     * get the length of the current vector3
     *
     * @return the length of the current vector3
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }
    
    /**
     * Normalize the current vector, this is also a unit vector
     *
     * @return a normalized vector 3
     */
    public Vector3 normalize() {
        float length = length();
        return new Vector3(this.x / length, this.y / length, this.z / length);
    }
    
    /**
     * @param target
     * @return
     */
    public Vector3 getDirection(Vector3 target) {
        return (new Vector3(target.getX() - this.x, target.getY() - this.y, target.getZ() - this.z));
    }
    
    /**
     * @param target
     * @param position
     * @return
     */
    public Vector3 getDirection(Vector3 target, Vector3 position) {
        return (new Vector3(target.getX() - position.getX(), target.getY() - position.getY(), target.getZ() - position.getZ()));
    }
    
    
    public float dotProduct(float dot1[], float dot2[]) {
        float dotSum = 0;
        
        for (int i = 0; i < dot1.length; i++) {
            dotSum += dot1[i] * dot2[i];
        }
        return dotSum;
    }
    
    /**
     * code based on <a href="https://stackoverflow.com/questions/48265646/rotation-of-a-vector-python">...</a>
     *
     * @param v1
     * @param v2
     * @return
     */
    public float getAngle(Vector3 v1, Vector3 v2) {
        
        v1 = v1.normalize();        //convert vector 3 to unit vector
        v2 = v2.normalize();            //convert vector 3 to unit vector
        
        //use the inverse cosines called arccos
        //float zeAngle = Math.acos(Math.min())
        //float roundOff = (float) Math.acos(Math.min(1, Math.max(v1.dotProduct(v1.returnASArry(), v2.returnASArry()), -1)));
        return new Float(Math.acos(Math.min(1, Math.max(v1.dotProduct(v1.returnASArry(), v2.returnASArry()), -1))));
        //return np.arccos(np.clip(np.dot(v1_u, v2_u), -1.0, 1.0))
        
    }
    
    /**
     * Rotate the vector3 up by the given degrees
     *
     * @param degrees
     * @return
     */
    
    
    public Vector3 RotateUpByDegrees(int degrees) {
        
        //pitch roll yaw ref chart https://th.bing.com/th/id/R.7581eb166c78861e1717f4dcb58c600f?rik=p02JZWulLC%2f6NA&riu=http%3a%2f%2fi.stack.imgur.com%2f8IuOw.png&ehk=pGMPZxyK9kOd1EoPH5K3L%2fGfDjyVcXCzixV8IOZ7L%2fE%3d&risl=&pid=ImgRaw&r=0
        //rotation based on https://github.com/carl-vbn/pure-java-raytracer/blob/master/src/carlvbn/raytracing/math/Vector3.java
        //use the x-axis so the 1st vector
        //- is clockwise while + is counterclockwise
        double yawDegrees = Math.toRadians(degrees);
        //Rotate around the y-axis
        //Rotate the x-axis first
        float _x = (float) (x * Math.cos(yawDegrees) + z * Math.sin(yawDegrees));
        //Rotate the z axis second
        float _z = (float) (-x * Math.sin(yawDegrees) + z * Math.cos(yawDegrees));
        //calculate the y axis called Yaw
        return new Vector3(_x, y, _z);
    }
    
    /**
     * Rotate up with the given vector by the given degrees
     *
     * @param vector
     * @param degrees
     * @return
     */
    public Vector3 RotateUpByDegrees(Vector3 vector, int degrees) {
        
        //use the x-axis so the 1st vector
        //- is clockwise while + is counterclockwise
        double yawDegrees = Math.toRadians(degrees);
        //Rotate around the y-axis
        //Rotate the x-axis first
        float _x = (float) (vector.x * Math.cos(yawDegrees) + vector.z * Math.sin(yawDegrees));
        //Rotate the z axis second
        float _z = (float) (-vector.x * Math.sin(yawDegrees) + vector.z * Math.cos(yawDegrees));
        //calculate the y axis called Yaw
        return new Vector3(_x, vector.y, _z);
    }
    
    /**
     * Rotate to the left with the given Degrees
     *
     * @param degrees
     * @return
     */
    public Vector3 RotateLeftByDegrees(int degrees) {
        double pitchDegrees = Math.toRadians(degrees);
        float _y = (float) (y * Math.cos(pitchDegrees) - z * Math.sin(pitchDegrees));
        float _z = (float) (y * Math.sin(pitchDegrees) + z * Math.cos(pitchDegrees));
        return new Vector3(x, _y, _z);
    }
    
    
}

