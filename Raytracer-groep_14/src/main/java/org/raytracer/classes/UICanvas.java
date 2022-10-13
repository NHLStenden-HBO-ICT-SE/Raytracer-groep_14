package org.raytracer.classes;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UICanvas{
    
    
    JFrame canvasFrame = new JFrame("best frame ever");
    
    private int Height, Width;
    Color[][] pixelColor;
    
    private BufferedImage bufferedImage;
    
    public Scene activeScene = new Scene();
    
    //todo create a camera object
    //todo set the pixels from the screen
    //todo try to save the pixels inside some class containing a multidemensional array
    //todo try to create a few empty raycasts
    //todo make a mockup pixelloop that will try to call raycasts through the camera, looping with x and y or u and v
    
    public UICanvas(int widthAndHeight) { //todo get height and width from camera
        
        pixelColor = new Color[widthAndHeight][widthAndHeight];
        
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
    
    public void setupScenes(Vector3 position, Color color) {
        
        activeScene.setupScene(new Sphere(position, 2, color, 0, 1, Material.superGlossy));
    }
    
    public void castRays() {
        moveObject(); //todo haal dit er uit, heeft niks met deze method te maken
        
        Raycast raycaster = new Raycast();
        pixelColor = raycaster.castLine(10, activeScene.GetCamera(), Width, Height, activeScene);
        BufferedImage image;
        image = new BufferedImage(this.Width, this.Height, BufferedImage.TYPE_INT_RGB);
        
        for (int i = 0; i < this.Width; i++) {
            for (int j = 0; j < this.Height; j++) {
                try {
                    //Color.fromInt(pixelData[i][j].getColor().getRGB());
                    //Color kleur = Color.fromInt(pixelData[i][j].getColor().getRGB());
                    //image.setRGB(i,j,(kleur.getRGB()));
                    image.setRGB(i, j, pixelColor[i][j].getRGB());
                } catch (Exception e) {
                    image.setRGB(i, j, Color.White.getRGB());
                }
            }
        }
        updateFrame(image);
    }
    
    public void moveObject() { //todo maak movement class voor opbjects
        activeScene.GetSolidSceneObject().setPosition(new Vector3(activeScene.GetSolidSceneObject().getPosition().add(new Vector3(0, 0, 0.1f))));
        
    }
}
