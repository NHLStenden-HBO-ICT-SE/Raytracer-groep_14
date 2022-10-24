package org.raytracer.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
//this class will be used to create and manage threads
 */
public class ThreadManager extends Thread{


    ExecutorService executorService = Executors.newCachedThreadPool();
    public int ThreadCount;

    public List<ThreadWorker> threadWorkerList;
    private RenderPixelColors pixelRenderer;

    public ThreadManager(int threadCount, RenderPixelColors pixelRenderer){
        this.ThreadCount = threadCount;
        this.pixelRenderer = pixelRenderer;
        threadWorkerList = new ArrayList<>();
        for (int i = 0; i < threadCount; i++){
            threadWorkerList.add(createNewThread());
        }
    }

    //the first setup for a thread
    public void sendParametersAndRun(SolidObject object, Ray ray, int x, int y){
        Future<Color> result = executorService.submit(new CallableThread(object, ray));
        try {
            pixelRenderer.writeFramePixel(x, y, (Color) result);
        }
        finally {
            executorService.shutdown();
        }
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
            if (!thread.isRunning()){
                return true;
            }
        }
        return false;
    }

    /**
     * return a workerThread that is idle and not doing anything
     * @return
     */
    private ThreadWorker grabAvailableThread(){
        for (ThreadWorker thread : threadWorkerList) {
            if (!thread.isRunning()){
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
        while (!checkThreads()){
        }
        return pixelRenderer;
    }


}