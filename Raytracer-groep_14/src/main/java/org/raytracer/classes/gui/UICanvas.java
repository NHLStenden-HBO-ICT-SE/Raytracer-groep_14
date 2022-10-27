package org.raytracer.classes.gui;

import org.raytracer.classes.objects.Color;
import org.raytracer.classes.objects.Sphere;
import org.raytracer.classes.raycasting.Raycast;
import org.raytracer.classes.scenes.Scene;
import org.raytracer.classes.vectors.Vector3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UICanvas{
    JFrame canvasFrame = new JFrame("best frame ever");
    
    private int Height, Width;
    org.raytracer.classes.objects.Color[][] pixelColor;
    
    private BufferedImage bufferedImage;
    
    public Scene activeScene = new Scene();
    

    //todo set the pixels from the screen
    //todo try to save the pixels inside some class containing a multidemensional array
    //todo try to create a few empty raycasts
    //todo make a mockup pixelloop that will try to call raycasts through the camera, looping with x and y or u and v
    
    public UICanvas(int widthAndHeight) { //todo get height and width from camera
        
        pixelColor = new org.raytracer.classes.objects.Color[widthAndHeight][widthAndHeight];
        
        this.Height = widthAndHeight;
        this.Width = widthAndHeight;
        
        if (canvasFrame != null) {
            createNewFrame();
        }
        canvasFrame.setSize(widthAndHeight, widthAndHeight);
        canvasFrame.setVisible(true);
        this.Width = activeScene.GetCamera().getScreenWidth();
        this.Height = activeScene.GetCamera().getScreenHeight();
        bufferedImage = new BufferedImage(this.Width, this.Height, BufferedImage.TYPE_INT_RGB);
    }
    
    public boolean createNewFrame() {
        
        JLabel label = new JLabel(); //JLabel Creation
        //todo use a bufferedimage to display, instead of a saved image
        label.setIcon(new ImageIcon("rame.png")); //Sets the image to be displayed as an icon
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(50, 30, size.width, size.height); //Sets the location of the image
        
        Container c = canvasFrame.getContentPane();
        c.add(label); //Adds objects to the container
        canvasFrame.setVisible(true); // Exhibit the frame
        return true;
    }
    
    public void updateFrame(BufferedImage bufferedImage) {
        JLabel label = new JLabel("frame"); //JLabel Creation
        label.setIcon(new ImageIcon(bufferedImage));
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(50, 30, size.width, size.height); //Sets the location of the image
        Container c = canvasFrame.getContentPane();
        c.remove(0);
        c.add(label); //Adds objects to the container
        canvasFrame.setVisible(true); // Exhibit the frame
    }
    
    public void addSphereToScene(Vector3 position, Color color) {
        activeScene.addObjectToScene(new Sphere(position, 2, color, 0, 1));
    }

    public void startRaytracer(){
        //moveObject(); //todo haal dit er uit, heeft niks met deze method te maken
        Raycast raycaster = new Raycast();
        updateFrame(raycaster.castRays(10, activeScene));
    }
    
    public void moveObject() { //todo maak movement class voor objects
        activeScene.getFirstSolidObject().setPosition(new Vector3(activeScene.getFirstSolidObject().getPosition().add(new Vector3(0, 0, 0.1f))));
        
    }
}
