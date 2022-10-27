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
        redPart = (redPart << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        greenPart = (greenPart << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        bluePart = bluePart & 0x000000FF; //Mask out anything not blue.
        
        return 0xFF000000 | redPart | greenPart | bluePart; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
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
    
    // calculate the reflection
    //reflectie = kleurlicht * kleurobject
    public void lightReflection(Color lightColor) {
        
        red = red * lightColor.getRed();
        blue = blue * lightColor.getBlue();
        green = green * lightColor.getGreen();
    }
    
}
