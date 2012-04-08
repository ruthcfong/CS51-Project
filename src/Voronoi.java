import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * 
 */

/**
 * @author admin
 *
 */
public class Voronoi 
{
	private int dimension;
	private HashSet<KdVector> vectors;
	private HashSet<Centroid> centroids;
	
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
		
		double[] randArr = new double[dimension];
		Random generator = new Random();
		Iterator<Centroid> iter;
		Centroid temp;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < dimension; j++)
			{
				randArr[j] = generator.nextDouble()*(upper-lower) + lower;
			}
			
			temp = new Centroid(dimension, randArr);
			
			iter = randCentroids.iterator();
			
			while(iter.hasNext())
			{
				if ((iter.next()).equals(temp))
				{
					i--;
					continue;
				}
			}
			
			randCentroids.add(temp);
		}
		
	}
	/*
	 * Voronoi class - a Voronoi diagram splits a Euclidean space into sub-spaces
	Initializer:
	Set vectors 
	Set dimensions - sets the parameters of our recommendation system (i.e. which qualities/aspects/interests weÕre using to base/influence our recommendations)
	Each vector represents a personÕs interest in every parameter
	Properties:
	A sequence of dimensions (each dimension is associated with a parameter that influences our recommendation system, i.e. movies, books, etc.)
	A set of centroids
	Operations:
	Distance between a centroid and a vector
	Set centroids method - accepts an array of centroids as a parameter
	Create a specific number of centroids - accepts the number of desired centroids as a parameter
	Add, remove, and get centroids methods
	an isEmpty method that checks if this Voronoi space has any centroids
	Run LloydÕs algorithm method - run partitions until the configuration converges (i.e. two consecutive partitions do not change the board/location of centroids)
	Partition - a function that executes one iteration of the LloydÕs algorithm
	a method that checks if the set of centroids have stabilized
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
