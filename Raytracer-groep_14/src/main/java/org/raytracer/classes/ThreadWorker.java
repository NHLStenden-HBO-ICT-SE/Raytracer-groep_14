package org.raytracer.classes;

public class ThreadWorker extends Thread{

    public boolean isWorking = false;
    public void run(){
        System.out.println("The thread is active");
    }


    /*
    Let the thread handle the raycast method and return a color
     */
    public Color runCast(SolidObject object, Ray tempRay){

        isWorking = true;
        Intersection intersection = object.CalculaterIntersectionTemp(tempRay);

        Color threadColor = Color.White;
        if(intersection != null){
            threadColor = intersection.getSolidObject().getColor();
        }
        isWorking = false;
        return threadColor;
    }

    /**
     * returns the status of the thread.
     * returns true if it is working
     * returns false if it is available
     * @return
     */
    public boolean isRunning(){
        return isWorking;
    }
}
