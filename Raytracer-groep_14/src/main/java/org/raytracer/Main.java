package org.raytracer;

import org.raytracer.classes.SceneManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SceneManager manager = new SceneManager();
        manager.SetupScene();
    }
}