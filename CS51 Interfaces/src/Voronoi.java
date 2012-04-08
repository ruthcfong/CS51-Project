/**
 * Voronoi represents a voronoi diagram, an n-dimensional Euclidean space 
 * that contains a series of vectors (i.e. KdVector) and centroids
 * (i.e. Centroid).
 * 
 * @ruthor Ruth Fong, Neil Gat, Yaniv Yacoby, Sarah Rosenthal
 *
 */
public interface Voronoi 
{
	
	/**
	 * Constructors - instantiates a Voronoi diagram in two ways
	 * 1) Specifying a dimension (creating an empty Voronoi diagram)
	 * 2) Specifying a dimension, a set of associated vectors, and
	 * 	  a set of associated centroids
	 */
	public Voronoi(int dim);
	
	public Voronoi(int dim, Collection<KdVector> vs, Collection<Centroid> cs);
	
	public Collection<KdVector> getVectors();
	
	public Collection<Centroid> getCentroids();
	
	public void addVector(KdVector v);
	
	public boolean removeVector(KdVector v);
	
	/**
	 * Clears the associated vectors from the voronoi diagram.
	 */
	public void clearVectors();
	
	public void addCentroid(Centroid c);
	
	public boolean removeCentroid(Centroid c);
	
	/**
	 * Removes a random centroid from the voronoi diagram.
	 * @return
	 */
	public Centroid removeCentroid();
	
	/**
	 * Clears the associated centroids from the voronoi diagram.
	 */
	public void clearCentroids();
	
	public void setCentroids(Collection<Centroid> cs);
	
	public void setVectors(Collection<KdVector> vs);
	
	/**
	 * Sets the set of n randomly placed centroids in the voronoi diagram
	 * (their coordinates along each dimensions between the specified range)
	 * 
	 * @param n - number of centroids to be randomly generated in the voronoi
	 */
	public void setCentroids(int n, double lower, double upper);
	
	public boolean hasVectors();
	
	public boolean hasCentroids();
	
	/**
	 * Clears the vectors associated to each centroid in the
	 * voronoi diagram from each centroid.
	 */
	public void clearCentroidVectors();
	
	/**
	 * First, clears the vectors in each associated centroid using the
	 * method clearCentroidVectors. Then,re-associates each vector in the 
	 * voronoi diagram with a centroid.
	 */
	public void partition();
	
	/**
	 * Goes through each centroid and sets its coordinate to the mean of 
	 * its associated vectors.
	 */
	public void setCentroidsAsMeans();
	
	/**
	 * Returns true if the state of the voronoi diagram has changed; otherwise
	 * returns false.
	 * @return
	 */
	public boolean hasChanged();
	
	/**
	 * Runs Lloyd's algorithm by partitioning the voronoi and resetting the
	 * associated centroids until the voronoi becomes stable (i.e. the 
	 * locations of the associated centroids become stable/converges)
	 */
	public void lloydsAlgorithm();
}