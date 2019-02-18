
public class AGNES {
	
	// Connection string
	private String m_strConnectionString;
	public void setConnectionString(String m_strConnectionString) {
		this.m_strConnectionString = m_strConnectionString;
	}
	public String getConnectionString() {
		return m_strConnectionString;
	}
	
	private ClusteringMethod m_clusterMethod;
	public void setClusterMethod(ClusteringMethod m_clusterMethod) {
		this.m_clusterMethod = m_clusterMethod;
	}
	public ClusteringMethod getClusterMethod() {
		return m_clusterMethod;
	}	
	
	
	private DistanceMatrix m_distanceMatrix;
	private DistanceMatrix m_initialDistanceMatrix;
	private Cluster m_clusterObjectList; // Initial cluster. In the beginning, all the objects 
										 // in the database are considered as one big cluster.
	
	private ClusterCollection m_allClusters;
	private int m_nLastUsedId = 0;
	
	public void run() {
		
		Logger.logMessage("AGNES started.");
		
		// Load data...
		Database db = new TextFileDatabase();
		m_clusterObjectList = db.loadData(getConnectionString());
		
		// Create a cluster for each object. Initially all objects are considered as separate clusters. 
		m_allClusters = new ClusterCollection();
		for (int i = 0; i < m_clusterObjectList.getCount(); i++) {
			Cluster newCluster = new Cluster();
			ClusterObject clusterObj = m_clusterObjectList.getObject(i);
			clusterObj.setObjectId(m_nLastUsedId); // Initially object IDs are same as cluster IDs. After mergers, cluster IDs will change.
			clusterObj.setClusterId(m_nLastUsedId);
			newCluster.add(clusterObj);
			
			// Every cluster should have a unique ID to distinguish them. 
			newCluster.setClusterId(m_nLastUsedId);
			
			m_allClusters.addCluster(newCluster);
			m_nLastUsedId++;
		}
		
		m_initialDistanceMatrix = new DistanceMatrix(m_clusterMethod); 
		m_initialDistanceMatrix.initializeDistanceMatrix(m_clusterObjectList, new JacquardTanimotoDistanceMetric());
		
		
		// Copy this to a new object. If method is not average a new object is not needed.  
		m_distanceMatrix = m_initialDistanceMatrix;
		
		for (int i = 1; i < m_clusterObjectList.getCount(); i++) {
			
			// Find the minimum distance between clusters.
			int nClusterId1 = -1;
			int nClusterId2 = -1;
			int[] result = m_distanceMatrix.findMinimumDistance();
			nClusterId1 = result[0];
			nClusterId2 = result[1];
			
			Logger.logMessage(String.format("Clusters to merge: %d, %d", nClusterId1, nClusterId2));
			
			// Get the two clusters two be merged.
			Cluster cluster1 = m_allClusters.getCluster(nClusterId1);
			Cluster cluster2 = m_allClusters.getCluster(nClusterId2);
			
			// Merge clusters: Add objects of the cluster1 to cluster2.   
			Cluster clMergedCluster = mergeClusters(cluster1, cluster2);
			m_allClusters.addCluster(clMergedCluster);
			
			m_distanceMatrix.calculateDistanceMatrix(m_clusterMethod, cluster1, cluster2, clMergedCluster);
			
			// Delete old clusters
			m_allClusters.removeCluster(cluster1);
			m_allClusters.removeCluster(cluster2);
		}
	}
	
	// Merges (or fuses) two clusters. And deletes the distance values from
	// the matrix. So the matrix is reduced by one after every merger. 
	private Cluster mergeClusters(Cluster cluster1, Cluster cluster2) {
		
		Cluster clMergedCluster = new Cluster();
		clMergedCluster.setClusterId(m_nLastUsedId);
		
		Logger.logMessage(String.format("New cluster ID: %d", clMergedCluster.getClusterId()));
		
		// Add all objects in the source to the cluster.
		for (ClusterObject clObj : cluster1) {
			clObj.setClusterId(m_nLastUsedId);
			clMergedCluster.add(clObj);
			
			Logger.logMessage(String.format("Adding obejct to new cluster: %d", clObj.getObjectId()));
		}
		
		for (ClusterObject clObj : cluster2) {
			clObj.setClusterId(m_nLastUsedId);
			clMergedCluster.add(clObj);
			
			Logger.logMessage(String.format("Adding obejct to new cluster: %d", clObj.getObjectId()));
		}
		
		m_nLastUsedId++;
		
		Logger.logMessage( String.format("%s merged with %s", cluster1.toString(), cluster2.toString())); 
		
		return clMergedCluster;
	}
}

