
public class CompleteLinkClusteringMethod extends ClusteringMethodStrategy {

	public double calculateDistance(double... distances) {
		
		double dResult = Double.MIN_VALUE;
		
		for (double dDistance : distances) {
			if (dDistance > dResult) {
				dResult = dDistance;
			}
		}
		
		return dResult;
	}
	

}
