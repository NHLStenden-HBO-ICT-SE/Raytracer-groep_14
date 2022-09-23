package org.raytracer.classes;

import java.util.concurrent.CopyOnWriteArrayList;

public class Scene {

	private SceneObject[] objectList;
	private CopyOnWriteArrayList<SolidObject> solidObjects;
	private Camera mainViewPoint;
	private Light lPoint;
	private Skybox skybox;

	/**
	 * scene constructor
	 */
	public Scene(){

	}





	public Camera getMainViewPoint() {
		return mainViewPoint;
	}

	/**
	 * A special cast forged in the pits of mount github, forged by the evil and the wicked trolls that inhabit the deepest and darkest pits of the great internets.
	 * @param ray
	 * @return
	 */
	public RayHit raycast(Ray ray) {
		RayHit closestHit = null;
		for (SolidObject solids : solidObjects) {
			if (solids == null)
				continue;

			Vector3 hitPos = solids.calculateIntersection(ray);
			if (hitPos != null && (closestHit == null || Vector3.distance(closestHit.getHitPos(), ray.getOrigin()) > Vector3.distance(hitPos, ray.getOrigin()))) {
				closestHit = new RayHit(ray, solids, hitPos);
			}
		}
		return closestHit;
	}

	public void AddSceneObject() {

	}

	public void RemoveSceneObject() {

	}

	public Skybox getSkybox() {
		return skybox;
	}

	public Light getlPoint() {
		return lPoint;
	}

	public void setlPoint(Light lPoint) {
		this.lPoint = lPoint;
	}

	public SceneObject[] GetSceneObjectList() {
		return null;
	}

}
