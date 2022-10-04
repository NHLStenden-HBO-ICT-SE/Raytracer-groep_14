package org.raytracer.classes;

public class Raycast {


    /**
     * Cast multiple rays and return colours or a default if the ray goes out of range
     */
    public void castLine(int rayReach, Camera camera, int width, int height){

        Color pixDotCol = new Color();

        PixelData[][] pixelData = new PixelData[width][height];
        for (int i = 0; i < width; i++) {

            String name = new String("cast" + i).toString();
            Ray tempray = new Ray(camera.getPosition(), camera.getDirection());

            Vector3 screenPoint = new Vector3(tempray.getOrigin().getX() + i, tempray.getOrigin().getY(), tempray.getOrigin().getZ());
            movePoint(rayReach ,new Ray(screenPoint, camera.getDirection()), name);

            for (int j = 0; j < height; j++) {
                Vector3 screenPoint2 = new Vector3(tempray.getOrigin().getX() + i, tempray.getOrigin().getY() + j, tempray.getOrigin().getZ());
                movePoint(rayReach ,new Ray(screenPoint2, camera.getDirection()), name);

            }
        }



    }

    public boolean movePoint(int distance, Ray ray, String name){
        Vector3 pointIn = new Vector3();
        for (int i = 0; i < distance; i++) {
            Vector3 tempPoint = ray.rayPoint(i);
            System.out.print(name + "  x =" +tempPoint.getX() + " y=" +tempPoint.getY() +  " z="+ tempPoint.getZ());
        }
        System.out.println("end of cast" + name);

        return true;
    }
}
