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
    public void runThread(int x, int y){
        onThreadEnd(x, y,grabAvailableThread().runCast() );
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


}
