package org.raytracer.classes;

public class Raycast {


    /**
     * Cast multiple rays and return colours or a default if the ray goes out of range
     */
    public void castLine(int rayReach, Camera camera, int width, int height){

        Color pixDotCol = new Color();

        PixelData[][] pixelData = new PixelData[width][height];
        for (int i = 0; i < width; i++) {

            Ray tempray = new Ray(camera.getPosition(), camera.getDirection());

            movePoint(rayReach ,tempray);


            for (int j = 0; j < height; j++) {

            }
        }



    }

    public boolean movePoint(int distance, Ray ray){
        Vector3 pointIn = new Vector3();
        for (int i = 0; i < distance; i++) {
            Vector3 tempPoint = ray.rayPoint(i);
            System.out.println(tempPoint);
        }
        return true;
    }
}
