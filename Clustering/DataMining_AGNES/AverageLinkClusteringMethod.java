
public class AverageLinkClusteringMethod extends ClusteringMethodStrategy {

	public double calculateDistance(double... distances) {
		
		double dResult = 0;
		
		for (double dDistance : distances) {
			dResult += dDistance;
		}
		
		dResult = dResult / (double) distances.length;
		return dResult;
	}
	

	
}
