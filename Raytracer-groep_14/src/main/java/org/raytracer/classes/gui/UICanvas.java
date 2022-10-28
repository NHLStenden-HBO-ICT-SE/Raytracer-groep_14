package org.raytracer.classes.gui;

import org.raytracer.classes.objects.Color;
import org.raytracer.classes.objects.SolidObject;
import org.raytracer.classes.raycasting.Raycast;
import org.raytracer.classes.scenes.Scene;
import org.raytracer.classes.vectors.Vector3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UICanvas {
    JFrame canvasFrame = new JFrame("best frame ever");
    JLabel label = new JLabel();
    private Container contentPanelContainer;
    private final BufferedImage bufferedImage;
    public Scene activeScene = new Scene();
    
    /**
     * Create frame on screen where bufferedImages can be shown to user.
     * <br>
     * It creates a new Scene() that will be used throughout the project
     */
    public UICanvas() {
        int widthAndHeight = activeScene.GetCamera().getWidthAndHeight();
        
        if (canvasFrame != null)
            createNewFrame();
        
        canvasFrame.setSize(widthAndHeight, widthAndHeight);
        canvasFrame.setVisible(true);
        
        bufferedImage = new BufferedImage(widthAndHeight, widthAndHeight, BufferedImage.TYPE_INT_RGB);
    }
    
    /**
     *
     */
    public void createNewFrame() {
        label = new JLabel(); //JLabel Creation
        label.setIcon(new ImageIcon("rame.png")); //Sets the image to be displayed as an icon
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(50, 30, size.width, size.height); //Sets the location of the image
        contentPanelContainer = canvasFrame.getContentPane();
        contentPanelContainer.add(label); //Adds objects to the container
        canvasFrame.setVisible(true); // Exhibit the frame
        
    }
    
    /**
     * Update the current frame with the added buffered image
     *
     * @param bufferedImage
     */
    public void updateFrame(BufferedImage bufferedImage) {
        JLabel label = new JLabel("frame"); //JLabel Creation
        label.setIcon(new ImageIcon(bufferedImage));
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(50, 30, size.width, size.height); //Sets the location of the image
        contentPanelContainer.remove(0);
        contentPanelContainer.add(label);
        swingFramer();
    }
    
    /**
     * Call this method to update the gui when using multithreading
     */
    public void swingFramer() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                canvasFrame.setVisible(true);
            }
        });
    }
    
    /**
     * Add any kind of solid object to the scene
     *
     * @param solidObject
     */
    public void addObjectToScene(SolidObject solidObject) {
        activeScene.addObjectToScene(solidObject);
    }
    
    
    public void startRaytracer() {
        // Movement of Objects
        moveObject(); //move the first object in a scene
        moveObject(activeScene.GetSceneObject(1), 0.3f);
        moveObject(activeScene.GetSceneObject(2), 0.575f);
        moveObject(activeScene.GetSceneObject(3), 0.175f);
        moveObject(activeScene.GetSceneObject(4), 0.03f);
        moveObject(activeScene.GetSceneObject(5), -0.0575f);
        activeScene.MainLight.SetPosition(activeScene.MainLight.GetPosition().add(new Vector3(0, 1f, 0)));

        
        Raycast rayCaster = new Raycast();
        
        updateFrame(rayCaster.castNormalForNow(10, activeScene));
    }
    
    public void moveObject(Vector3 vector3) {
        activeScene.getFirstSolidObject().setPosition(new Vector3(activeScene.getFirstSolidObject().getPosition().add(vector3)));
    }
    
    public void moveObject() { //todo maak movement class voor opbjects
        activeScene.getFirstSolidObject().setPosition(new Vector3(activeScene.getFirstSolidObject().getPosition().add(new Vector3(0, 0, 0.1f))));
        
    }
    
    public void moveObject(SolidObject object) {
        object.setPosition(new Vector3(object.getPosition().add(new Vector3(0, 0, 0.5f))));
    }
    
    public void moveObject(SolidObject object, float speed) {
        object.setPosition(new Vector3(object.getPosition().add(new Vector3(0, 0, speed))));
    }
}
