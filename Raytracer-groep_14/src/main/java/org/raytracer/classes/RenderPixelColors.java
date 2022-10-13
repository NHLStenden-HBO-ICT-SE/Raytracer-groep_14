package org.raytracer.classes;


import java.awt.image.BufferedImage;

//Todo Add raycast method
public class RenderPixelColors {



    public void drawRay() {
        //moveObject(); //todo haal dit er uit, heeft niks met deze method te maken

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
}
