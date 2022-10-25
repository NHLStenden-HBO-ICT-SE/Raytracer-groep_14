package org.raytracer.classes.raycasting;

import org.raytracer.classes.objects.Color;

public class drawData {

    public drawData(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }
    public int x,y;
    public Color color;
}
