import java.util.ArrayList;
import java.util.Iterator;


public class ClusterCollection implements Iterable<Cluster> {

	private ArrayList<Cluster> m_clusterList = new ArrayList<Cluster>();
	
	
	@Override
	public Iterator<Cluster> iterator() {
		return m_clusterList.iterator();
	}

	public void addCluster(Cluster cluster) {
		m_clusterList.add(cluster);
	}

	public void removeCluster(Cluster cluster) {
		for (Cluster clTemp : m_clusterList) {
			if (clTemp.getClusterId() == cluster.getClusterId()) {
				m_clusterList.remove(clTemp);
				break;
			}
		}
	}
	
	public int getCount() {
		return m_clusterList.size();
	}
	
	public Cluster getCluster(int clusterId) {
		
		Cluster clResult = null;
		for (Cluster clTemp : m_clusterList) {
			if (clTemp.getClusterId() == clusterId) {
				clResult = clTemp;
				break;
			}
		}
		
		return clResult;
	}
	
}
