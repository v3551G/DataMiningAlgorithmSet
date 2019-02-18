
public class DistanceCalculator {
	
	private DistanceMetricStrategy m_distanceMetric;
	
	public DistanceCalculator(DistanceMetricStrategy distanceMetric) {
		m_distanceMetric = distanceMetric;
	}
	
	public double calculate(ClusterObject obj1, ClusterObject obj2) {
		return m_distanceMetric.calculateDistance(obj1, obj2);
	}
	

}
