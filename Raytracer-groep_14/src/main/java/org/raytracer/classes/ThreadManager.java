package org.raytracer.classes;

/*
//this class will be used to create and manage threads
 */
public class ThreadManager extends Thread{


    public int ThreadCount;

    public ThreadManager(int threadCount){
        this.ThreadCount = threadCount;
    }

    public boolean reserveThread(){
        return false;
    }

    private ThreadWorker createNewThread(){
        return null;
    }


}
