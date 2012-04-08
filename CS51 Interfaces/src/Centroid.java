/**
 * 
 */

/**
 * Centroid represents a point in n-dimensional Euclidean space 
 * associated with a set of vectors (i.e. KdVector).
 * 
 * @ruthor Ruth Fong, Neil Gat, Yaniv Yacoby, Sarah Rosenthal
 *
 */

public interface Centroid extends KdVector
{
	/**
	 * Constructors - instantiates a Centroid
	 * 1) Specifying a dimension (creating a centroid at the origin with
	 * 	  no vectors associated to it)
	 * 2) Specifying a dimension and a set of associated vectors (creating
	 * 	  a centroid at the origin)
	 * 3) Specifying a dimension and a set of components representing the
	 * 	  point in Euclidean space that the Centroid is located at (creating
	 * 	  a centroid with no vectors associated to it)
	 * 4) Specifying a dimension, a set of components representing the point
	 * 	  in Euclidean space that the Centroid is located at, and a set of
	 * 	  associated vectors
	 */
	public Centroid(int dim)
	
	public Centroid(int dim, Collection<KdVector> vs)
	
	public Centroid (int dim, double[] vec)
	
	public Centroid(int dim, double[] vec, Collection<KdVector> vs)
	
	/**
	 * Return the collection of vectors in the centroid.
	 * 
	 * @return
	 */
	public Collection<KdVector> getVectors();
	
	/**
	 * Adds a vector to the centroid's collection of vectors.
	 * @param v - vector to be added
	 */
	public void addVector(KdVector v);
	
	/**
	 * Removes a vector from the centroid's collection. If the vector
	 * is in the collection, it returns true; otherwise, returns false.
	 * 
	 * @param v - the vector to be removed
	 * @return
	 */
	public boolean removeVector(KdVector v);
	
	/**
	 * Removes all the vectors in the centroid's collection of vectors.
	 */
	public void clearVectors();
	
	/**
	 * Sets the coordinates of the centroid to the mean of
	 * the vectors that it contains.
	 */
	public void setAsMeans();

	/**
	 * Returns a string detailing the centroid's coordinates and the vectors
	 * that it contains.
	 */
	public String toString();

}

