
public class JacquardTanimotoDistanceMetric extends DistanceMetricStrategy{
	
	public double calculateDistance(ClusterObject obj1, ClusterObject obj2) {
		
		double dResult = 0; 
		
		int nLength1 = obj1.getSize();
		int nLength2 = obj2.getSize();
		
		if (nLength1 != nLength2) {
			throw new ApplicationException("Distance of objects with different lengths cannot be calculated.");
		}
		
		int nXorTrueCount = 0;
		int nOrTrueCount = 0;
		for (int i = 0; i < nLength1; i++) {
			
			if (obj1.getVector().get(i) ^ obj2.getVector().get(i)) {
				nXorTrueCount++;				
			}
			
			if (obj1.getVector().get(i) | obj2.getVector().get(i)) {
				nOrTrueCount++;				
			}
		}
		
		dResult = (double)nXorTrueCount / (double)nOrTrueCount;
		
		return dResult;
	}
	
}
