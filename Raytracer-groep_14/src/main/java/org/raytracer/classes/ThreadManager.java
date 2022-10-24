package org.raytracer.classes;

import java.util.List;

/*
//this class will be used to create and manage threads
 */
public class ThreadManager extends Thread{


    public int ThreadCount;

    public List<ThreadWorker> threadWorkerList;
    private RenderPixelColors pixelRenderer;

    public ThreadManager(int threadCount, RenderPixelColors pixelRenderer){
        this.ThreadCount = threadCount;
        this.pixelRenderer = pixelRenderer;
    }

    public boolean reserveThread(){
        return false;
    }

    /**
     * Run the prebuild castray method on this thread
     * @param x
     * @param y
     * @param object
     * @param ray
     * @return the status of the threads, will return false if they are all busy
     */
    public boolean runThread(int x, int y, SolidObject object, Ray ray){
        if (checkThreads()){
            onThreadEnd(x, y,grabAvailableThread().runCast(object, ray));
            return true;
        }
        return false;
    }

    private boolean checkThreads(){
        for (ThreadWorker thread : threadWorkerList) {
            if (!thread.isWorking){
                return true;
            }
        }
        return false;
    }
    private ThreadWorker grabAvailableThread(){
        for (ThreadWorker thread : threadWorkerList) {
            if (!thread.isWorking){
                return thread;
            }
        }
        return null;
    }

    private ThreadWorker createNewThread(){
        return new ThreadWorker();
    }

    //Thread returns a colour with coordinate, write it to an image once it gets in
    public void onThreadEnd(int x, int y, Color color){
        pixelRenderer.writeFramePixel(x, y, color);
    }

    /**
     * Return the image that has been coloured by the threads
     * will loop until all threads are done
     * @return
     */
    public RenderPixelColors getThreadedImage(){
        while (checkThreads()){
        }
        return pixelRenderer;
    }


}
