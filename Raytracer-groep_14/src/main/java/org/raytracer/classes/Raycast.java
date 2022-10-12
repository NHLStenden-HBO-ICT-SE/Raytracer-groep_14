package org.raytracer.classes;

import java.util.Objects;

public class Raycast {

    public PixelData[][] castLine(int rayReach, Camera camera, int width, int height, Scene scene) {

        SolidObject object = scene.GetSolidSceneObject();
        PixelData[][] pixelData = new PixelData[width][height];
        pixelData[0][0] = new PixelData();
        for (int i = 0; i < width; i++) {

            for (int j = 0; j < height; j++) {
                Ray tempray = new Ray(camera, i,j);
                pixelData[i][j] = new PixelData(Color.White, 0, 0);
                if (!Objects.equals(object.CalculaterIntersection(tempray), new Vector3())) {
                    pixelData[i][j].setColor(tempray.getColor());
                }
                else {
                    pixelData[i][j] = new PixelData(Color.White, 0, 0);
                }
            }
        }
        return pixelData;
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
