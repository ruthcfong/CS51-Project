import java.util.HashSet;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author admin
 *
 */
public class Centroid extends KdVector
{
	private HashSet<KdVector> vectors;
	
	public Centroid(int dim) throws Exception
	{
		super(dim);
		vectors = new HashSet<KdVector>();
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	public Centroid(int dim, Collection<KdVector> vs) throws Exception 
	{
		this(dim);
		vectors.addAll(vs);
	}
	
	public Centroid (int dim, double[] vec) throws Exception
	{
		super(dim, vec);
		vectors = new HashSet<KdVector>();
	}
	
	public Centroid(int dim, double[] vec, Collection<KdVector> vs) throws Exception 
	{
		this(dim, vec);
		vectors.addAll(vs);
	}
	
	/**
	 * Return the collection of vectors in the centroid.
	 * 
	 * @return
	 */
	public Collection<KdVector> getVectors()
	{
		return vectors;
	}
	
	/**
	 * Adds a vector to the centroid's collection of vectors.
	 * @param v - vector to be added
	 */
	public void addVector(KdVector v)
	{
		vectors.add(v);
	}
	
	/**
	 * Removes a vector from the centroid's collection. If the vector
	 * is in the collection, it returns true; otherwise, returns false.
	 * 
	 * @param v - the vector to be removed
	 * @return
	 */
	public boolean removeVector(KdVector v)
	{
		return vectors.remove(v);
	}
	
	/**
	 * Removes all the vectors in the centroid's collection of vectors.
	 */
	public void clearVectors()
	{
		vectors.clear();
	}
	
	/**
	 * Sets the coordinates of the centroid to the mean of
	 * the vectors that it contains.
	 * 
	 * @throws Exception
	 */
	public void setAsMeans() throws Exception
	{
		// instantiate a accumulator vector to sum up all the vectors
		KdVector vectorSum = new KdVector(this.getDimension());
		
		// initialize variables
		Iterator<KdVector> iter = vectors.iterator();
		int count = 0;
		
		// add up all the centroid's vectors
		while (iter.hasNext())
		{
			vectorSum = KdVector.add(vectorSum, iter.next());
			count++;
		}
		
		// checks that there are vectors contained in the centroid
		if (count == 0)
			return;
		
		// scale accumulator vector by 1/n, where n equals the number of 
		// vectors contained in this centroid
		vectorSum.scale(1.0/count);
		
		// resets the coordinates of the centroid based on the mean of
		// its vectors
		this.setComponents(vectorSum.getComponents());
	}

	/**
	 * Returns a string detailing the centroid's coordinates and the vectors
	 * that it contains.
	 */
	public String toString()
	{
		String str = "Coordinate: " + super.toString() + "\n";
		str += "Vectors: \n";
		Iterator<KdVector> iter = vectors.iterator();
		
		while (iter.hasNext())
		{
			str += (iter.next()).toString() + "\n";
		}
		
		return str;
	}
	
	/*
	public static void main(String[] args) throws Exception 
	{
		double[] arr = {1.0, 2.0, 3.0, -5.0};
		double[] arr2 = {4.0, -5.0, 6.0, 1.0};
		Centroid c1 = new Centroid(4);
		c1.addVector(new KdVector(4,arr));
		c1.addVector(new KdVector(4,arr2));
		c1.setAsMeans();
		System.out.println(c1);
		Centroid c2 = new Centroid(4);
		c2.setAsMeans();
		System.out.println(c2);
		c1.clearVectors();
		System.out.println(c1);
	}*/

}
