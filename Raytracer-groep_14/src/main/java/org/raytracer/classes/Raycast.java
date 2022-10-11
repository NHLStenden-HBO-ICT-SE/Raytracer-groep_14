package org.raytracer.classes;

public class Raycast {


    /**
     * Cast multiple rays and return colours or a default if the ray goes out of range
     */
    public void castLine(int rayReach, Camera camera, int width, int height) {

        Color pixDotCol = new Color();

        PixelData[][] pixelData = new PixelData[width][height];
        for (int i = 0; i < width; i++) {

            Vector3 dummy = new Vector3();
            String name = new String("cast" + i).toString();
            Ray tempray = new Ray(camera, i, 0);
            Vector3 screenPoint = new Vector3(tempray.getOrigin().getX() + i, tempray.getOrigin().getY(), tempray.getOrigin().getZ());
            //movePoint(rayReach, new Ray(camera, dummy.getDirection(camera.getPosition(), screenPoint)), name);
            for (int j = 0; j < height; j++) {
                Vector3 screenPoint2 = new Vector3(tempray.getOrigin().getX() + i, tempray.getOrigin().getY() + j, tempray.getOrigin().getZ());
                //movePoint(rayReach, new Ray(camera.getDirection(), dummy.getDirection(camera.getPosition(), screenPoint2)), name);
            }
        }
    }

    public PixelData[][] castLine(int rayReach, Camera camera, int width, int height, Scene scene) {

        Color pixDotCol = new Color();

        SolidObject object = scene.GetSolidSceneObject();
        PixelData[][] pixelData = new PixelData[width][height];
        pixelData[0][0] = new PixelData();
        for (int i = 0; i < width; i++) {

            Vector3 dummy = new Vector3();
            String name = new String("cast" + i).toString();
            Ray tempray = new Ray(camera, i, 0);
            Vector3 screenPoint = new Vector3(tempray.getOrigin().getX() + i, tempray.getOrigin().getY(), tempray.getOrigin().getZ());
            //Ray intersectionRay = new Ray(camera.getPosition(), dummy.getDirection(camera.getPosition(), screenPoint));
            tempray.setColor(Color.White);
            //movePoint(rayReach ,intersectionRay, name);
            //object.CalculaterIntersection(tempray);
            //pixelData[i][0].setColor(intersectionRay.getColor());
            for (int j = 0; j < height; j++) {
                Vector3 screenPoint2 = new Vector3(tempray.getOrigin().getX() + i, tempray.getOrigin().getY() + j, tempray.getOrigin().getZ());
                tempray = new Ray(camera, i,j);
                //movePoint(rayReach ,intersectionRay, name);
                object.CalculaterIntersection(tempray);
                pixelData[i][j] = new PixelData(Color.Black, 0, 0);
                if (object.CalculaterIntersection(tempray) != new Vector3()) {
                    pixelData[i][j].setColor(tempray.getColor());
                }
                else {
                    pixelData[i][j] = new PixelData(Color.Black, 0, 0);
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
