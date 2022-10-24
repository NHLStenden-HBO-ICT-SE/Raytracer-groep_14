package org.raytracer.classes;

import java.util.concurrent.Callable;

public class CallableThread implements Callable<Color> {

    //todo return integer that represents the color
    private SolidObject object;
    private  Ray tempRay;


    //constructor
    public CallableThread(SolidObject object, Ray tempray){
        this.object = object;
        this.tempRay = tempray;
    }
    @Override
    public Color call() throws Exception {

        Intersection intersection = object.calculateIntersection(tempRay);

        Color threadColor = Color.White;
        if(intersection != null){
            threadColor = intersection.getSolidObject().getColor();
        }
        return threadColor;
    }
}
