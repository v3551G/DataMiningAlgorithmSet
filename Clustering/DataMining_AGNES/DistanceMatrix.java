import java.util.Hashtable;
import java.util.Enumeration;


public class DistanceMatrix {
	
	private Hashtable<String, Double> m_htDistanceMatrix;
	private Hashtable<String, Double> m_htInitialDistanceMatrix; // Needed for average link method only
	private ClusteringMethod m_clusteringMethod;
	
	
	
	public DistanceMatrix(ClusteringMethod clusteringMethod) {
		m_clusteringMethod = clusteringMethod;
		m_htDistanceMatrix = new Hashtable<String, Double>();
	}
	
	public void setValue(int clusertId1, int clusterId2, double value) {
		m_htDistanceMatrix.put(prepareKey(clusertId1, clusterId2), value);
	}
	
	public void setValue(Cluster cl1, Cluster cl2, double value) {
		m_htDistanceMatrix.put(prepareKey(cl1, cl2), value);
	}
	
	public double getValue(Cluster cl1, Cluster cl2) {
		return m_htDistanceMatrix.get(prepareKey(cl1, cl2));
	}
	
	private String prepareKey(Cluster cl1, Cluster cl2) {
		return prepareKey(cl1.getClusterId(), cl2.getClusterId());
	}
	
	// Concatenates two cluster IDs to create a unique key. Left padded with zeros
	// Currently it uses 4 digits so it can hold up to 10000 IDs. 
	// This must be modified to work with larger number of objects.
	private String prepareKey(int clusterId1, int clusterId2) {
		String key = String.format( "%1$04d%2$04d", new Integer(clusterId1), new Integer(clusterId2) );
		return key;
	}
	
	private int[] parseKey(String key) {
		int[] result = new int[2]; 
		result[0] = Integer.parseInt(key.substring(0, 4));
		result[1] = Integer.parseInt(key.substring(4, 8));
		return result;
	}
	
	public int getSize() {
		return m_htDistanceMatrix.size();
	}
	
	// Finds the minimum distance between clusters
	// Since java doesn't have out parameters, int values are wrapped in objects
	public int[] findMinimumDistance() {
		double dMinimum = Double.MAX_VALUE;
		int[] result = new int[2];
		
		Enumeration<String> distances = m_htDistanceMatrix.keys();
		String strKey = "";
		double dDistance = -1;
		String strMinimumDistanceKey = "";
		while (distances.hasMoreElements()) {
			strKey = distances.nextElement();
			dDistance =	m_htDistanceMatrix.get(strKey);
			if (dDistance < dMinimum) {
				dMinimum = dDistance;
				strMinimumDistanceKey = strKey;
			}
		}
		
		result = parseKey(strMinimumDistanceKey);
		
		Logger.logMessage(String.format("Smallest distance is %.6f. Under key: %s", dMinimum, strMinimumDistanceKey));
		return result;
	}
	
	public void initializeDistanceMatrix(Cluster objectlist, DistanceMetricStrategy distMetric) {
		
		DistanceCalculator distCalc = new DistanceCalculator(distMetric);
		
		for (int i = 0; i < objectlist.getCount() - 1; i++) {
			for (int j = i + 1; j < objectlist.getCount(); j++) {
				double dDistance = distCalc.calculate(objectlist.getObject(i), objectlist.getObject(j));
				setValue(i, j, dDistance);
			}
		}
		
		m_htInitialDistanceMatrix = m_htDistanceMatrix;
		
		Logger.logMessage("Initial distances calculated");
		printDistanceValues();
	}
	// Calculates new distances between clusters.
	
	public void calculateDistanceMatrix(ClusteringMethod clusteringMethod, Cluster clMerged1, Cluster clMerged2, Cluster clNew) {
		
		if (clusteringMethod.getClass().getName().compareTo("AverageLinkClusteringMethod") == 0) {
			calculateDistanceMatrixForAverage(clusteringMethod, clMerged1, clMerged2, clNew);
			return;
		}
		
		Logger.logMessage("Calculating new distance matrix.");
		
		Hashtable<String, Double> htNewDistanceMatrix = new Hashtable<String, Double>();
		
		Enumeration<String> distances = m_htDistanceMatrix.keys();
		String strKey = "";
		double dDistance = -1;
		int nCurrClusterId1 = -1;
		int nCurrClusterId2 = -1;
		
		while (distances.hasMoreElements()) {
			strKey = distances.nextElement();
			dDistance =	m_htDistanceMatrix.get(strKey);
			
			int[] result = parseKey(strKey);
			nCurrClusterId1 = result[0];
			nCurrClusterId2 = result[1];
			
			// If the cluster IDs in the key are both the same as the recently merged clusters, skip it. This value
			// won't be in the new table.
			if (  (nCurrClusterId1 == clMerged1.getClusterId() && nCurrClusterId2 == clMerged2.getClusterId()) ||
				  (nCurrClusterId2 == clMerged2.getClusterId() && nCurrClusterId1 == clMerged1.getClusterId()) ) {
				// don't need this value anymore, since these clusters are merged into clNew
				continue;
			}
			else
			// If one of the cluster IDs in the key match with one of the recently merged clusters, the use
			// this value to calculate the distances of the new cluster.
			if ( nCurrClusterId1 == clMerged1.getClusterId() || nCurrClusterId1 == clMerged2.getClusterId() ) {
				String strNewKey = prepareKey(clNew.getClusterId(), nCurrClusterId2);
				
				if (contains(htNewDistanceMatrix, strNewKey)) {
					double dCurrentValue = htNewDistanceMatrix.get(strNewKey);
					
					double dNewValue =  m_clusteringMethod.calculateDistance(dCurrentValue, dDistance);
					htNewDistanceMatrix.remove(strNewKey);
					htNewDistanceMatrix.put(strNewKey, dNewValue);
				} else {
					htNewDistanceMatrix.put(strNewKey, dDistance);					
				}
				
			}
			else
			// This block is same as above. Only checking the other cluster ID. This eliminates the dependency of the order
			// of the IDs in the key.
			if ( nCurrClusterId2 == clMerged1.getClusterId() || nCurrClusterId2 == clMerged2.getClusterId() ) {
				String strNewKey = prepareKey(clNew.getClusterId(), nCurrClusterId1);
				
				if (contains(htNewDistanceMatrix, strNewKey)) {
					double dCurrentValue = htNewDistanceMatrix.get(strNewKey);
					
					double dNewValue =  m_clusteringMethod.calculateDistance(dCurrentValue, dDistance);
					htNewDistanceMatrix.remove(strNewKey);
					htNewDistanceMatrix.put(strNewKey, dNewValue);
				} else {
					htNewDistanceMatrix.put(strNewKey, dDistance);					
				}
			}
			// If the IDs both don't match with the current key, then this distance is not relevant to 
			// the recently merged clusters. So we can use the old value as is.
			else {
				htNewDistanceMatrix.put(strKey, dDistance);
			}
			
		}
		
		m_htDistanceMatrix = htNewDistanceMatrix;
		
		printDistanceValues();
	}
	
	
	
	public void calculateDistanceMatrixForAverage(ClusteringMethod clusteringMethod, Cluster clMerged1, Cluster clMerged2, Cluster clNew) {
		
		Logger.logMessage("Calculating new distance matrix.");
		
		Hashtable<String, Double> htNewDistanceMatrix = new Hashtable<String, Double>();
		
		Enumeration<String> distances = m_htDistanceMatrix.keys();
		String strKey = "";
		double dDistance = -1;
		int nCurrClusterId1 = -1;
		int nCurrClusterId2 = -1;
		
		while (distances.hasMoreElements()) {
			strKey = distances.nextElement();
			dDistance =	m_htDistanceMatrix.get(strKey);
			
			int[] result = parseKey(strKey);
			nCurrClusterId1 = result[0];
			nCurrClusterId2 = result[1];
			
			// If the cluster IDs in the key are both the same as the recently merged clusters, skip it. This value
			// won't be in the new table.
			if (  (nCurrClusterId1 == clMerged1.getClusterId() && nCurrClusterId2 == clMerged2.getClusterId()) ||
				  (nCurrClusterId2 == clMerged2.getClusterId() && nCurrClusterId1 == clMerged1.getClusterId()) ) {
				// don't need this value anymore, since these clusters are merged into clNew
				continue;
			}
			else
			// If one of the cluster IDs in the key match with one of the recently merged clusters, the use
			// this value to calculate the distances of the new cluster. If it
			if ( nCurrClusterId1 == clMerged1.getClusterId() || nCurrClusterId1 == clMerged2.getClusterId() ) {
				String strNewKey = prepareKey(clNew.getClusterId(), nCurrClusterId2);
				double dAverageDistance = 0;
				
				if (!contains(htNewDistanceMatrix, strNewKey)) {
					// From the initial distance matrix, find each object's distance to the cluster being merged.
					for (ClusterObject obj : clNew) {
						String strTempKey = prepareKey(obj.getObjectId(), nCurrClusterId2);
						dAverageDistance +=	m_htInitialDistanceMatrix.get(strTempKey);
					}
					dAverageDistance = dAverageDistance / (double) clNew.getCount();
					
					htNewDistanceMatrix.put(strNewKey, dAverageDistance);
				}
			}
			else
			// This block is same as above. Only checking the other cluster ID. This eliminates the dependency of the order
			// of the IDs in the key.
			if ( nCurrClusterId2 == clMerged1.getClusterId() || nCurrClusterId2 == clMerged2.getClusterId() ) {
				String strNewKey = prepareKey(clNew.getClusterId(), nCurrClusterId1);
				double dAverageDistance = 0;
				
				if (!contains(htNewDistanceMatrix, strNewKey)) {
					// From the initial distance matrix, find each object's distance to the cluster being merged.
					for (ClusterObject obj : clNew) {
						String strTempKey = prepareKey(obj.getObjectId(), nCurrClusterId2);
						dAverageDistance +=	m_htInitialDistanceMatrix.get(strTempKey);
					}
					dAverageDistance = dAverageDistance / (double) clNew.getCount();
					
					htNewDistanceMatrix.put(strNewKey, dAverageDistance);
				}
			}
			// If the IDs both don't match with the current key, then this distance is not relevant to 
			// the recently merged clusters. So we can use the old value as is.
			else {
				htNewDistanceMatrix.put(strKey, dDistance);
			}
			
		}
		
		m_htDistanceMatrix = htNewDistanceMatrix;
		
		printDistanceValues();
	}
	
	
	// Checks if the given key is in the given hashtable.
	private boolean contains(Hashtable<String, Double> tableToCheck, String keytoCheck) {
		boolean bResult = false;
		Enumeration<String> currentKeys = tableToCheck.keys();
		String strKey;
		while (currentKeys.hasMoreElements()) {
			strKey = currentKeys.nextElement();
			if (strKey.compareTo(keytoCheck) == 0) {
				bResult = true;
				break;
			}
		}
		
		return bResult;
	}

	
	private void printDistanceValues() {
		Enumeration<String> distances = m_htDistanceMatrix.keys();
		String strKey = "";
		double dDistance = -1;
		while (distances.hasMoreElements()) {
			strKey = distances.nextElement();
			dDistance =	m_htDistanceMatrix.get(strKey);
			Logger.logMessage( String.format("Key: %s      Value: %.6f", strKey, dDistance));
		}
	}
	
}
