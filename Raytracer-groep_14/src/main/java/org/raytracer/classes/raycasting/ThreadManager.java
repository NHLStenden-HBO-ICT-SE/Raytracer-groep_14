package org.raytracer.classes.raycasting;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
//this class will be used to create and manage threads
 */
public class ThreadManager{
    public static ExecutorService executerService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    public static void restartExecuter(){
        if (ThreadManager.executerService.isShutdown()){
            executerService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        }
    }
    public static void runExecuter(){
        if (!getExecuterStatus()){
            restartExecuter();
        }
    }
    public static boolean getExecuterStatus(){
        if (ThreadManager.executerService.isShutdown()){
            return false;
        }
        return true;
    }
}
