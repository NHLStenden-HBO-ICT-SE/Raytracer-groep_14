package org.raytracer.classes;

import java.util.Objects;

public class Raycast {
    
    public Color[][] castLine(int rayReach, Camera camera, int width, int height, Scene scene) {
        
        SolidObject object = scene.GetSolidSceneObject();
        Color[][] pixelColor = new Color[width][height];
        pixelColor[0][0] = new Color(); //todo change to use color()
        for (int i = 0; i < width; i++) {
            
            for (int j = 0; j < height; j++) {
                Ray tempray = new Ray(camera, i, j);
                pixelColor[i][j] = Color.White; //Default background color
                if (!Objects.equals(object.calculateIntersection(tempray), new Vector3())) {
                    pixelColor[i][j].setColor(tempray.getColor());
                }
            }
        }
        return pixelColor;
    }
    
    public boolean movePoint(int distance, Ray ray, String name) {
        Vector3 pointIn = new Vector3();
        for (int i = 0; i < distance; i++) {
            Vector3 tempPoint = ray.rayPoint(i);
            //System.out.print(name + "  x =" +tempPoint.getX() + " y=" +tempPoint.getY() +  " z="+ tempPoint.getZ());
        }
        System.out.println("end of cast" + name);
        
        return true;
    }
}
