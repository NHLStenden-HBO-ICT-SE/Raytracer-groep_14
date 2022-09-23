package org.raytracer.classes;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Scene {

	private SceneObject[] objectList;
	private List<SolidObject> solidObjectList = new List<SolidObject>() {
		@Override
		public int size() {
			return 0;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public boolean contains(Object o) {
			return false;
		}

		@Override
		public Iterator<SolidObject> iterator() {
			return null;
		}

		@Override
		public Object[] toArray() {
			return new Object[0];
		}

		@Override
		public <T> T[] toArray(T[] ts) {
			return null;
		}

		@Override
		public boolean add(SolidObject solidObject) {
			return false;
		}

		@Override
		public boolean remove(Object o) {
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> collection) {
			return false;
		}

		@Override
		public boolean addAll(Collection<? extends SolidObject> collection) {
			return false;
		}

		@Override
		public boolean addAll(int i, Collection<? extends SolidObject> collection) {
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> collection) {
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> collection) {
			return false;
		}

		@Override
		public void clear() {

		}

		@Override
		public SolidObject get(int i) {
			return null;
		}

		@Override
		public SolidObject set(int i, SolidObject solidObject) {
			return null;
		}

		@Override
		public void add(int i, SolidObject solidObject) {

		}

		@Override
		public SolidObject remove(int i) {
			return null;
		}

		@Override
		public int indexOf(Object o) {
			return 0;
		}

		@Override
		public int lastIndexOf(Object o) {
			return 0;
		}

		@Override
		public ListIterator<SolidObject> listIterator() {
			return null;
		}

		@Override
		public ListIterator<SolidObject> listIterator(int i) {
			return null;
		}

		@Override
		public List<SolidObject> subList(int i, int i1) {
			return null;
		}
	};
	private CopyOnWriteArrayList<SolidObject> solidObjects;
	private Camera mainViewPoint;
	private Light lPoint;
	private Skybox skybox;

	/**
	 * scene constructor
	 */
	public Scene(Camera camera, Light light){
		this.mainViewPoint = camera;
		this.lPoint = light;
	}

	public void addObects(SolidObject object){

		solidObjectList.add(object);
	}
	public List<SolidObject> getSolidObjectList() {
		return solidObjectList;
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
