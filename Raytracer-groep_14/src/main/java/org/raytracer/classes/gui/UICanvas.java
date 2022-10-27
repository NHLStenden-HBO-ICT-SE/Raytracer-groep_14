package org.raytracer.classes.gui;

import org.raytracer.classes.objects.Color;
import org.raytracer.classes.objects.Plane;
import org.raytracer.classes.objects.SolidObject;
import org.raytracer.classes.objects.Sphere;
import org.raytracer.classes.raycasting.Raycast;
import org.raytracer.classes.raycasting.ThreadManager;
import org.raytracer.classes.scenes.Scene;
import org.raytracer.classes.vectors.Vector3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UICanvas{
    JFrame canvasFrame = new JFrame("best frame ever");
    JLabel label = new JLabel();
    private int Height, Width;
    org.raytracer.classes.objects.Color[][] pixelColor;

    private Container contentPanelContainer;
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
        JButton quitButton = new JButton("Quit");
        label = new JLabel(); //JLabel Creation
        //todo use a bufferedimage to display, instead of a saved image
        label.setIcon(new ImageIcon("rame.png")); //Sets the image to be displayed as an icon
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(50, 30, size.width, size.height); //Sets the location of the image
        contentPanelContainer = canvasFrame.getContentPane();
        contentPanelContainer.add(label); //Adds objects to the container
        //contentPanelContainer.add(quitButton);
        quitButton.setBounds(100, 100, 115, 55);
        canvasFrame.setVisible(true); // Exhibit the frame
        return true;
    }
    
    public void updateFrame(BufferedImage bufferedImage) {
        JLabel label = new JLabel("frame"); //JLabel Creation
        /*
        if (!ThreadManager.getExecuterStatus()){

            ThreadManager.restartExecuter();
        }

         */

        label.setIcon(new ImageIcon(bufferedImage));
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(50, 30, size.width, size.height); //Sets the location of the image
        contentPanelContainer.remove(0);
        contentPanelContainer.add(label);
        swingFramer();
        /*
        ThreadManager.executerService.execute(new Runnable() {
            @Override
            public void run() {
                label.setIcon(new ImageIcon(bufferedImage));
                Dimension size = label.getPreferredSize(); //Gets the size of the image
                label.setBounds(50, 30, size.width, size.height); //Sets the location of the image
                contentPanelContainer.remove(1);
                contentPanelContainer.add(label);
                canvasFrame.repaint();
            }
        });

         */
    }
    public void swingFramer(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                canvasFrame.setVisible(true);
            }
        });
    }
    public void addObjectToScene(SolidObject solidObject){
        activeScene.addObjectToScene(solidObject);
    }
    
    public void addSphereToScene(Vector3 position, Color color, float radius) {
        activeScene.addObjectToScene(new Sphere(position, radius, color, 0, 1));
    }


    public void startRaytracer(){
        //moveObject(); //move the first object in a scene
        //moveObject(activeScene.GetSceneObject(1), 0.3f);
        //moveObject(activeScene.GetSceneObject(2), 0.575f);
        //moveObject(activeScene.GetSceneObject(3), 0.175f);
        //moveObject(activeScene.GetSceneObject(4), 0.03f);
        //moveObject(activeScene.GetSceneObject(5), -0.0575f);
        activeScene.MainLight.SetPosition(activeScene.MainLight.GetPosition().add(new Vector3(0,-0.1f,0.1f)));
        Raycast raycaster = new Raycast();
        updateFrame(raycaster.castNormalForNow(10,activeScene));
    }

    public void moveObject(Vector3 vector3){
        activeScene.getFirstSolidObject().setPosition(new Vector3(activeScene.getFirstSolidObject().getPosition().add(vector3)));
    }
    public void moveObject() { //todo maak movement class voor opbjects
        activeScene.getFirstSolidObject().setPosition(new Vector3(activeScene.getFirstSolidObject().getPosition().add(new Vector3(0, 0, 0.1f))));
        
    }
    public void moveObject(SolidObject object){
        object.setPosition(new Vector3(object.getPosition().add(new Vector3(0, 0, 0.5f))));
    }
    public void moveObject(SolidObject object, float speed){
        object.setPosition(new Vector3(object.getPosition().add(new Vector3(0, 0, speed))));
    }
}
