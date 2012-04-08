/**
 * KdVector represents a Euclidean vector that has n dimensions and n components.
 * 
 * @ruthor Ruth Fong, Neil Gat, Yaniv Yacoby, Sarah Rosenthal
 *
 */
public interface KdVector
{	
	/**
	 *  Constructors - to instantiate vectors
	 *  The client can instantiate a KdVector in two ways
	 * 1) Specifying a dimension (creating a default zero vector)
	 * 2) Specifying a dimension and array of components of the vector
	 */
	
	public KdVector(int dim);
	
	public KdVector(int dim, double[] vec);


	public void setComponents(double[] components);
	
	public double[] getComponents();
	
	public void setComponent(int dim, double comp);
	
	/**
	 * Calculates and returns the magnitude of the vector.
	 * 
	 * @return magnitude of vector
	 */
	public double magnitude();
	
	/**
	 * Returns the component of the vector at the specified index unless it is
	 * out of bounds; otherwise, native methods throw ArrayOutOfBounds exception
	 * 
	 * @param index
	 * @return the component of the vector at the specified index
	 */
	public double getComponent(int dim);
	
	/**
	 * 
	 * @return dimension of the vector
	 */
	public int getDimension();
	
	/**
	 * Scales the vector by the given factor
	 * 
	 * @param factor
	 */
	public void scale(double factor);
	
	/**
	 * Returns a string representation of a vector, consisting of the
	 * vector's dimension and its components
	 * 
	 * @return
	 */
	public String toString();

	/**
	 * Checks if v1 and v2 have the same dimensions; if not, throws an 
	 * exception.
	 * 
	 * @param v1 - first vector
	 * @param v2 - second vector
	 */
	private static void dimensionMismatch(KdVector v1, KdVector v2);
	
	/**
	 * Returns the dot product of the given two KdVectors.
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double dotProduct(KdVector v1, KdVector v2);
	
	/**
	 * Calculates and returns the cross product of v1 and v2, unless one or 
	 * both of them aren't 3-dimensional vectors; otherwise, an exception is
	 * thrown.
	 * 
	 * @param v1 - first vector
	 * @param v2 - second vector
	 * @return cross product of KdVector v1 and KdVector v2
	 */
	public static KdVector crossProduct(KdVector v1, KdVector v2);
		
	/**
	 * Returns a vector that is the sum of the two given KdVectors.
	 * 
	 * @param v1 - first vector
	 * @param v2 - second vector
	 * @return a KdVector representing v1 + v2
	 */
	public static KdVector add(KdVector v1, KdVector v2);
	
	/**
	 * Returns a vector that is the difference between the two given KdVectors.
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static KdVector subtract(KdVector v1, KdVector v2);
	
	/**
	 * Returns the Euclidean distance between the two given KdVectors.
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double distance(KdVector v1, KdVector v2);
	
}
