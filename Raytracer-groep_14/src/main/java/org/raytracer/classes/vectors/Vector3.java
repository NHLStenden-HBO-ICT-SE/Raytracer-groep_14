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


    /**
     * Calculate and return the distance between this point and the given vector3
     * @param b
     * @return
     */
    public float distanceBetweenPoints(Vector3 b) {
        return (float) Math.sqrt(Math.pow((x - b.x), 2) + Math.pow((y - b.y), 2) + Math.pow((z - b.z), 2));
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
}

