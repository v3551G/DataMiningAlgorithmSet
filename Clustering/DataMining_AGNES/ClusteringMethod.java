
public class ClusteringMethod {

	private ClusteringMethodStrategy m_clusteringMethod;
	
	public ClusteringMethod(ClusteringMethodStrategy clusteringMethod) {
		m_clusteringMethod = clusteringMethod;
	}
		
	public double calculateDistance(double distance1, double distance2) {
		return m_clusteringMethod.calculateDistance(distance1, distance2);
	}
}
