package org.raytracer.classes;

public class Light extends SceneObject {
    private Color color;
    private Vector3 position;
    private float intencity;


    public void SetIntencity(int Brightness) {

    }

    public Light(Vector3 position, float intencity) {
        this.intencity = intencity;
        this.position = position;
        this.color = new Color(1, 1, 1);//todo hier moet een waarde aangegeven
    }

    public Light(Vector3 position, float intencity, float red, float green, float blue) {
        this.intencity = intencity;
        this.position = position;
        this.color = new Color(red, green, blue);
    }

    public float CalcLightIntencity(float objectDistance) {

        double distance = Math.pow(objectDistance, 2);

        double lightIntencity = 1 / distance;

        float lightIntencity1 = (float) lightIntencity;// cast double to float for returntype

        return lightIntencity1;

    }

    // calculate the reflection
    //reflectie = kleurlicht * kleurobject
    public Color Lightreflection(Color lightsource) {

        float colorred = color.getRed();
        float colorblue = color.getBlue();
        float colorgreen = color.getGreen();

        float lightred = color.getRed();
        float lightblue = color.getBlue();
        float lightgreen = color.getGreen();

        float reflectred = colorred * lightred;
        float reflectblue = colorblue * lightblue;
        float reflectgreen = colorgreen * lightgreen;

        return new Color(reflectred, reflectblue, reflectgreen);
    }


    /*public float AngleOfView(Vector3 light, Vector3) {

        //interciteit licht invals hoek is cos a oftewel
        //dot product van de normaal en licht vector


        return 1;
    }*/


}
