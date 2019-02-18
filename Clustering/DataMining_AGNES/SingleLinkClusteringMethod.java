
public class SingleLinkClusteringMethod extends ClusteringMethodStrategy {
	
	public double calculateDistance(double... distances) {
		
		double dResult = Double.MAX_VALUE;
		
		for (double dDistance : distances) {
			if (dDistance < dResult) {
				dResult = dDistance;
			}
		}
		
		return dResult;
	}
	

	
}
