import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * 
 */

/**
 * Note: There's a potential bug in the partition method. There's a definite bug in
 * private helper method logOldState, as well as in the public method hasChanged.
 * We're working on fixing these methods as soon as possible.
 *
 * @author admin
 *
 */
public class Voronoi 
{
	private final int dimension;
	private HashSet<KdVector> vectors;
	private HashSet<Centroid> centroids;
	
	private HashSet<Centroid> lastState;
	
	/**
	 * 
	 */
	public Voronoi(int dim) 
	{
		dimension = dim;
		vectors = new HashSet<KdVector>();
		centroids = new HashSet<Centroid>();
	}
	
	public Voronoi(int dim, Collection<KdVector> vs, Collection<Centroid> cs)
	{
		this(dim);
		vectors.addAll(vs);
		centroids.addAll(cs);
	}
	
	public Collection<KdVector> getVectors()
	{
		return vectors;
	}
	
	public Collection<Centroid> getCentroids()
	{
		return centroids;
	}
	
	public void addVector(KdVector v)
	{
		vectors.add(v);
	}
	
	public boolean removeVector(KdVector v)
	{
		return vectors.remove(v);
	}
	
	public void clearVectors()
	{
		vectors.clear();
	}
	
	public void addCentroid(Centroid c)
	{
		centroids.add(c);
	}
	
	public boolean removeCentroid(Centroid c)
	{
		return centroids.remove(c);
	}
	
	public Centroid removeCentroid()
	{
		Iterator<Centroid> iter = centroids.iterator();
		
		if (!iter.hasNext())
			return null;
		
		Centroid c = iter.next();
		removeCentroid(c);
		
		return c;
	}
	
	public void clearCentroids()
	{
		centroids.clear();
	}
	
	public void setCentroids(Collection<Centroid> cs)
	{
		centroids.addAll(cs);
	}
	
	public void setVectors(Collection<KdVector> vs)
	{
		vectors.addAll(vs);
	}
	
	/**
	 * 
	 * @param n - number of centroids to be randomly generated in the voronoi
	 * @throws Exception 
	 */
	public void setCentroids(int n, double lower, double upper) throws Exception
	{
		HashSet<Centroid> randCentroids = new HashSet<Centroid>(n);
		
		double[] randArr;
		Random generator = new Random();
		Iterator<Centroid> iter;
		Centroid temp;
		
		cents:
		for (int i = 0; i < n; i++)
		{
			randArr = new double[dimension];
			for (int j = 0; j < dimension; j++)
			{
				randArr[j] = generator.nextDouble() * (upper - lower) + lower;
			}
			
			temp = new Centroid(dimension, randArr);
			
			iter = randCentroids.iterator();
			
			while(iter.hasNext())
			{
				if ((iter.next()).equals(temp))
				{
					i--;
					continue cents;
				}
			}
			
			randCentroids.add(temp);
		}
		centroids = randCentroids;
	}
	
	public boolean hasVectors()
	{
		return !vectors.isEmpty();
	}
	
	public boolean hasCentroids()
	{
		return !centroids.isEmpty();
	}
	
	public void clearCentroidVectors()
	{
		Iterator<Centroid> iter = centroids.iterator();
		while (iter.hasNext())
		{
			(iter.next()).clearVectors();
		}
	}
	
	public void partition() throws Exception 
	{
		if (!hasCentroids() || !hasVectors())
			return;
		
		logOldState();
		
		clearCentroidVectors();
		
		Iterator<KdVector> iterVect = vectors.iterator();
		Iterator<Centroid> iterCent;
		KdVector v;
		Centroid c, smallest;
		double distance, smallestDistance;
		
		while (iterVect.hasNext())
		{
			v = iterVect.next();
			
			iterCent = centroids.iterator();
			
			c = iterCent.next();
			smallest = c;
			smallestDistance = KdVector.distance(smallest, v);
			/*
			System.out.println(v);
			System.out.println(c);
			System.out.println(smallestDistance);
			*/
			while(iterCent.hasNext())
			{
				c = iterCent.next();
				distance = KdVector.distance(c, v);
				
				/*
				System.out.println(v);
				System.out.println(c);
				System.out.println(distance);
				System.out.println(smallestDistance);
				*/
				
				if (distance < smallestDistance)
				{
					smallest = c;
					smallestDistance = distance;
				}
			} 
			
			smallest.addVector(v);
		}
	}
	
	public void setCentroidsAsMeans() throws Exception
	{
		Iterator<Centroid> iter = centroids.iterator();
		Centroid cent;
		while (iter.hasNext())
		{
			cent = iter.next();
			cent.setAsMeans();
		}
	}
	
	private void logOldState()
	{
		lastState = (HashSet<Centroid>) centroids.clone();
	}
	
	public boolean hasChanged()
	{
		return !lastState.equals(centroids);
	}
	
	public void lloydsAlgorithm() throws Exception
	{
		do {
			partition();
			setCentroidsAsMeans();
		} while (!hasChanged());
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		double[] a1 = {-1, -3};
		KdVector v1 = new KdVector(2, a1);
		double[] a2 = {1, 3};
		KdVector v2 = new KdVector(2, a2);
		double[] a3 = {-4, 6};
		KdVector v3 = new KdVector(2, a3);
		double[] a4 = {0, 0};
		KdVector v4 = new KdVector(2, a4);
		double[] a5 = {8, -3};
		KdVector v5 = new KdVector(2, a5);
		Voronoi vor = new Voronoi(2);
		vor.addVector(v1);
		vor.addVector(v2);
		vor.addVector(v3);
		vor.addVector(v4);
		vor.addVector(v5);
		vor.setCentroids(3, -3, 3);
		
		Iterator<Centroid> it1 = vor.getCentroids().iterator();
		while (it1.hasNext())
		{
			System.out.println(it1.next());
		}
		
		do {
			vor.partition();
			vor.setCentroidsAsMeans();
			System.out.println("Partition:");
			Iterator<Centroid> it2 = vor.getCentroids().iterator();
			while (it2.hasNext())
			{
				System.out.println(it2.next());
			}
		} while (vor.hasChanged());
		
	}

}
