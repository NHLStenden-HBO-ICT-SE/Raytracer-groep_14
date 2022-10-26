package org.raytracer.classes.raycasting;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
//this class will be used to create and manage threads
 */
public class ThreadManager{
    static ExecutorService executerService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
}