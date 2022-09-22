package org.raytracer.classes;

import org.raytracer.classes.RenderSupport.PixelBuffer;
import org.raytracer.classes.RenderSupport.PixelData;

public class Renderer {

	private boolean showSkyBox;

	public static PixelBuffer RenderScene(Scene scene, int screenWidth, int screenHeight){

		PixelBuffer pixelBuffer = new PixelBuffer(screenWidth, screenHeight);

		for (int x = 0; x< screenWidth; x++){

			for (int y = 0; y<screenHeight; y++){

				float[] screenUV = getNormalizedScreenCoordinates(x,y,screenWidth,screenHeight);

			}
		}
		return pixelBuffer;
	}

	/**
	 * Render the scene by using raytracing
	 * @param scene
	 * @param u
	 * @param v
	 * @return
	 */
	public static PixelData ComputePixelData(Scene scene, float u, float v){
		Camera camera = scene.getMainViewPoint();
		Vector3 Eyeposition = new Vector3(0,0, (float) (-1/Math.tan(Math.toRadians(camera.getFieldOfView()/2))));

		Ray ray = new Ray();
		ray.setDirection(new Vector3(u,v,0).subtract(Eyeposition).normalize().rotateYP(camera.getYaw(), camera.getPitch()));

		return new PixelData();
	}


	/** Get OpenGL style screen coordinates where (0,0) is the center of the screen from x, y coordinates that start from the top-left corner of the screen */
	public static float[] getNormalizedScreenCoordinates(int x, int y, int width, int height) {
		float u = 0, v = 0;
		if (width > height) {
			u = (float)(x - width/2+height/2) / height * 2 - 1;
			v =  -((float) y / height * 2 - 1);
		} else {
			u = (float)x / width * 2 - 1;
			v =  -((float) (y - height/2+width/2) / width * 2 - 1);
		}

		return new float[]{u, v};
	}



}
