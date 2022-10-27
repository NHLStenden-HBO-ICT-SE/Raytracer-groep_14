package org.raytracer.classes.objects;


public class Color {
    
    private float red = 1;
    
    private float green = 1;
    
    private float blue = 1;
    
    /**
     * Standard color is white
     */
    public Color() {
        setColor(Color.White);
    }
    
    /**
     * @param red   number between 0 and 1
     * @param green number between 0 and 1
     * @param blue  number between 0 and 1
     */
    public Color(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    
    public float getRed() {
        return red;
    }
    
    public float getBlue() {
        return blue;
    }
    
    public float getGreen() {
        return green;
    }
    
    
    public void setColor(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    public void setColor(Color color) {
        this.red = color.red;
        this.green = color.green;
        this.blue = color.blue;
    }
    
    /**
     * Gets rgb values and converts it to a hex number
     *
     * @return hex code
     */
    public int getRGB() {
        int redPart = (int) (red * 255);
        int greenPart = (int) (green * 255);
        int bluePart = (int) (blue * 255);
        
        // Shift bits to right place
        //redPart = (redPart << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        //greenPart = (greenPart << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        //bluePart = bluePart & 0x000000FF; //Mask out anything not blue.

        //return 0xFF000000 | redPart | greenPart | bluePart; //0xFF000000 for 100% Alpha. Bitwise OR everything together.


        int rgb = redPart;
        rgb = (rgb << 8) + greenPart;
        rgb = (rgb << 8) + bluePart;
        return rgb;


    }
    
    public void nerfColor() {
        if (red > 1)
            red = 1;
        if (blue > 1)
            blue = 1;
        if (green > 1)
            green = 1;
        if (red < 0)
            red = 0;
        if (blue < 0)
            blue = 0;
        if (green < 0)
            green = 0;
    }
    
    public static Color fromInt(int argb) {
        int b = (argb) & 0xFF;
        int g = (argb >> 8) & 0xFF;
        int r = (argb >> 16) & 0xFF;
        
        return new Color(r / 255F, g / 255F, b / 255F);
    }
    
    public java.awt.Color toAWTColor() {
        return new java.awt.Color(red, green, blue);
    }
    
    public static final Color White = new Color(1f, 1f, 1f);
    public static final Color Black = new Color(0, 0, 0);
    public static final Color Blue = new Color(0, 0, 1f);
    
    //public static final Color
    
    public Color multiply(float value) {
        return new Color(this.red * value, this.green * value, this.blue * value);
    }


    /**
     * Berekenen lichtintensiteit
     */
    
    // calculate the reflection
    //reflectie = kleurlicht * kleurobject
    public Color lightReflection(Color lightColor, float lightDistance) {
        lightColor.lightIntensityColorOverDistance(lightDistance);
        
        float red = this.red * lightColor.getRed();
        float blue = this.blue * lightColor.getBlue();
        float green = this.green * lightColor.getGreen();
        
        return new Color(red, green, blue);
    }
    
    /**
     * Berekenen lichtintensiteit
     */
    public Color lightIntensityColorOverDistance(float objectDistance) {
        
        double distance = Math.pow(objectDistance, 2);
        
        double lightIntensity = 1 / distance;
    
        float red = this.red * (float) lightIntensity;
        float green = this.green * (float) lightIntensity;
        float blue = this.blue * (float) lightIntensity;
        
        return new Color(red, green, blue);
    }
    
}
