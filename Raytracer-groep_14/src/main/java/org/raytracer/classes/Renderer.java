package org.raytracer.classes;

import org.raytracer.classes.RenderSupport.PixelBuffer;
import org.raytracer.classes.RenderSupport.PixelData;

public class Renderer {

	private int maxBounces = 5;
	private static final float GLOBAL_ILLUMINATION = 0.3F;
	private static final float SKY_EMISSION = 0.5F;
	private static final boolean SHOW_SKYBOX = true;

	public static float bloomIntensity = 0.5F;
	public static int bloomRadius = 10;

	/**
	 * Use this if you want to reduce the amount of ray that will be cast
	 */
	private float CompressionRayts = 0;
	private boolean showSkyBox;

	/**
	 *Create a new pixelmap for the screen that will be used to render everything
	 * @param scene the current scene that is being used
	 * @param screenWidth
	 * @param screenHeight
	 * @return
	 */
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
	 * @param u the x coordinates of the screen
	 * @param v the y coordinates
	 * @return
	 */
	public static PixelData ComputePixelData(Scene scene, float u, float v){
		Camera camera = scene.getMainViewPoint();
		Vector3 Eyeposition = new Vector3(0,0, (float) (-1/Math.tan(Math.toRadians(camera.getFieldOfView()/2))));

		Vector3 raydir = new Vector3(u,v,0).subtract(Eyeposition).normalize().rotateYP(camera.getYaw(), camera.getPitch());
		RayHit hit = scene.raycast(new Ray(Eyeposition.add(camera.getPosition()),new Vector3(u,v,0).subtract(Eyeposition).normalize().rotateYP(camera.getYaw(), camera.getPitch())));

		if (hit != null) {
			return computePixelInfoAtHit(scene, hit, 5);
		} else if (SHOW_SKYBOX) {
			Color sbColor = scene.getSkybox().getColor(raydir);
			return new PixelData(sbColor, Float.POSITIVE_INFINITY, sbColor.getLuminance()*SKY_EMISSION);
		} else {
			return new PixelData(Color.BLACK, Float.POSITIVE_INFINITY, 0);
		}
	}

	/**
	 *calculate the hitpoint of the ray
	 * @param scene
	 * @param hit
	 * @param recursionLimit the limit of bounces for the ray
	 * @return
	 */
	private static PixelData computePixelInfoAtHit(Scene scene, RayHit hit, int recursionLimit) {
		Vector3 hitPos = hit.getHitPos();
		Vector3 rayDir = hit.getRay().getDirection();
		SolidObject hitSolid = hit.getSolidObject();
		Color hitColor = hitSolid.getTextureColor(hitPos.subtract(hitSolid.getPosition()));
		float brightness = getDiffuseBrightness(scene, hit);
		float specularBrightness = getSpecularBrightness(scene, hit);
		float reflectivity = hitSolid.getReflectivity();
		float emission = hitSolid.getEmission();

		PixelData reflection;
		Vector3 reflectionVector = rayDir.subtract(hit.getNormal().multiply(2*Vector3.dot(rayDir, hit.getNormal())));
		Vector3 reflectionRayOrigin = hitPos.add(reflectionVector.multiply(0.001F)); // Add a little to avoid hitting the same solid again
		RayHit reflectionHit = recursionLimit > 0 ? scene.raycast(new Ray(reflectionRayOrigin, reflectionVector)) : null;
		if (reflectionHit != null) {
			reflection = computePixelInfoAtHit(scene, reflectionHit, recursionLimit-1);
		} else {
			Color sbColor = scene.getSkybox().getColor(reflectionVector);
			reflection = new PixelData(sbColor, Float.POSITIVE_INFINITY, sbColor.getLuminance()*SKY_EMISSION);
		}

		Color pixelColor = Color.lerp(hitColor, reflection.getColor(), reflectivity) // Reflected color
				.multiply(brightness) // Diffuse lighting
				.add(specularBrightness) // Specular lighting
				.add(hitColor.multiply(emission)) // Object emission
				.add(reflection.getColor().multiply(reflection.getEmmison()*reflectivity)); // Indirect illumination

		return new PixelData(pixelColor, Vector3.distance(scene.getMainViewPoint().getPosition(), hitPos), Math.min(1, emission+reflection.getEmmison()*reflectivity+specularBrightness));
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
	private static float getDiffuseBrightness(Scene scene, RayHit hit) {
		Light sceneLight = scene.getlPoint();

		// Raytrace to light to check if something blocks the light
		RayHit lightBlocker = scene.raycast(new Ray(sceneLight.getPosition(), hit.getHitPos().subtract(sceneLight.getPosition()).normalize()));
		if (lightBlocker != null && lightBlocker.getSolidObject() != hit.getSolidObject()) {
			return GLOBAL_ILLUMINATION; // GOBAL_ILLUMINATION = Minimum brightness
		} else {
			return Math.max(GLOBAL_ILLUMINATION, Math.min(1, Vector3.dot(hit.getNormal(), sceneLight.getPosition().subtract(hit.getHitPos()))));
		}
	}

	private static float getSpecularBrightness(Scene scene, RayHit hit) {
		Vector3 hitPos = hit.getHitPos();
		Vector3 cameraDirection = scene.getMainViewPoint().getPosition().subtract(hitPos).normalize();
		Vector3 lightDirection = hitPos.subtract(scene.getlPoint().getPosition()).normalize();
		Vector3 lightReflectionVector = lightDirection.subtract(hit.getNormal().multiply(2*Vector3.dot(lightDirection, hit.getNormal())));

		float specularFactor = Math.max(0, Math.min(1, Vector3.dot(lightReflectionVector, cameraDirection)));
		return (float) Math.pow(specularFactor, 2)*hit.getSolidObject().getReflectivity();
	}



}
