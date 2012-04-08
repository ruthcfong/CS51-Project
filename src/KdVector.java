public class KdVector
{
	// Properties
	private double vector[];
	private final int dimension;
	
	public KdVector(int dim) throws Exception
	{
		// check that valid arguments are passed in
		if (dim < 0) 
			throw new Exception("Specified dimension is smaller than zero: " + dim); // over 80
		
		// initialize private variables
		dimension = dim;
		vector = new double[dim];
	}
	
	public KdVector(int dim, double[] vec) throws Exception
	{
		// check that valid arguments are passed in
		if (dim < 0) 
			throw new Exception("Specified dimension is smaller than zero: " + dim); // over 80
		else if (vec.length != dim)
			throw new Exception("The size of the given vector array does not equal the given dimension: " + dim); // over 80
		
		// initialize private variables
		dimension = dim;
		vector = vec;	
	}
	
	
	public void setComponents(double[] components) 
	{
		vector = components;
	}
	
	public double[] getComponents() 
	{
		return vector;
	}
	
	public void setComponent(int dim, double comp)
	{
		vector[dim] = comp;
	}
	
	/**
	 * Calculates and returns the magnitude of the vector.
	 * 
	 * @return magnitude of vector
	 */
	public double magnitude()
	{
		double sum = 0.0;
		for (int i = 0; i < dimension; i++)
		{
			sum += Math.pow(vector[i], 2);
		}
		return Math.sqrt(sum);
	}
	
	/**
	 * Returns the component of the vector at the specified index unless it is
	 * out of bounds; otherwise, native methods throw ArrayOutOfBounds exception
	 * 
	 * @param index
	 * @return the component of the vector at the specified index
	 */
	public double getComponent(int dim)
	{
		return vector[dim];
	}
	
	/**
	 * 
	 * @return dimension of the vector
	 */
	public int getDimension()
	{
		return dimension;
	}
	
	/**
	 * Checks if v1 and v2 have the same dimensions; if not, throws an 
	 * exception.
	 * 
	 * @param v1 - first vector
	 * @param v2 - second vector
	 * @throws Exception
	 */
	private static void dimensionMismatch(KdVector v1, KdVector v2) throws Exception 
	{
		if (v1.dimension != v2.dimension)
			throw new Exception("Dimensions of v1 and v2 don't equal each other."); // over 80
	}
	
	/**
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double dotProduct(KdVector v1, KdVector v2) throws Exception
	{
		dimensionMismatch(v1, v2);
		double sum = 0.0;
		
		for (int i = 0; i < v1.dimension; i++)
		{
			sum += v1.vector[i] * v2.vector[i];
		}
		
		return sum;
	}
	
	/**
	 * Calculates and returns the cross product of v1 and v2, unless one or 
	 * both of them aren't 3-dimensional vectors; otherwise, an exception is
	 * thrown.
	 * 
	 * @param v1 - first vector
	 * @param v2 - second vector
	 * @return cross product of KdVector v1 and KdVector v2
	 * @throws Exception "Cross product is only defined for three-dimensional vectors"
	 */
	public static KdVector crossProduct(KdVector v1, KdVector v2) throws Exception
	{
		if (v1.dimension != 3 || v2.dimension != 3)
			throw new Exception("Cross product is only defined for three-dimensional vectors."); // over 80
		
		/* calculate cross product based on formula described here: 
		 * http://en.wikipedia.org/wiki/Cross_product#Matrix_notation 
		 */
		double v3arr[] = {v1.vector[1]*v2.vector[2] - v1.vector[2]*v2.vector[1],
				v1.vector[2]*v2.vector[0] - v1.vector[0]*v2.vector[2],
				v1.vector[0]*v2.vector[1] - v1.vector[1]*v2.vector[0]};
		
		return new KdVector (3, v3arr);
	}
	
	/**
	 * 
	 * @param v1
	 * @param v2
	 * @param factor
	 * @return
	 * @throws Exception
	 */
	private static KdVector arithHelper(KdVector v1, KdVector v2, double factor) throws Exception
	{
		dimensionMismatch(v1, v2);
		double v3arr[] = new double[v1.dimension];
		
		for (int i = 0; i < v1.dimension; i++)
		{
			v3arr[i] = v1.vector[i] + factor * v2.vector[i];
		}
		return new KdVector(v1.dimension, v3arr);
	}
	
	/**
	 * 
	 * @param v1 - first vector
	 * @param v2 - second vector
	 * @return a KdVector representing v1 + v2
	 * @throws Exception
	 */
	public static KdVector add(KdVector v1, KdVector v2) throws Exception
	{
		return arithHelper(v1, v2, 1);
	}
	
	/**
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @throws Exception
	 */
	public static KdVector subtract(KdVector v1, KdVector v2) throws Exception
	{
		return arithHelper(v1, v2, -1);
	}
	
	/**
	 * 
	 * @param factor
	 */
	public void scale(double factor)
	{
		for(int i = 0; i < dimension; i++)
		{
			vector[i] *= factor;
		}
	}
	
	/**
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 * @throws Exception
	 */
	public static double distance(KdVector v1, KdVector v2) throws Exception
	{
		return (subtract(v1, v2)).magnitude();
	}
		
	/*
	 * Implement a vector class (as required by LloydÕs algorithm)
	Properties:
	- Magnitude, (Direction), 1-form, dimension
	Operations
	Dot Product, Cross Product, ***?Comparison operation, ***Distance between vectors, Vector Addition and Subtraction, Scaling
	 * 
	 */

	public String toString()
	{	
		String str = "Dimension = " + dimension + ": {";
		for (int i = 0; i < dimension - 1; i++)
		{
			str += vector[i] + ", ";
		}
		return str + vector[dimension - 1] + "}";
	}
	/*
	public static void main(String[] args) throws Exception 
	{
		double[] arr = {1.0, 2.0, 3.0};
		KdVector v1 = new KdVector(3, arr);
		System.out.println(v1.magnitude());
		double[] arr2 = {4.0, 5.0, 6.0};
		KdVector v2 = new KdVector(3, arr2);
		KdVector v3 = KdVector.add(v1, v2);
		System.out.println(v3);
		System.out.println(KdVector.distance(v1, v2));
	}*/

}
