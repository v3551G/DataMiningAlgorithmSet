import java.util.ArrayList;
import java.util.Iterator;

public class Cluster implements Iterable<ClusterObject>{

	private int m_nClusterId = -1;
	public void setClusterId(int m_nClusterId) {
		this.m_nClusterId = m_nClusterId;
	}
	public int getClusterId() {
		return m_nClusterId;
	}

	public Cluster() {
		m_lstClusterObjects = new ArrayList<ClusterObject>();
	}
	
	private ArrayList<ClusterObject> m_lstClusterObjects;
	/*
	private ArrayList<ClusterObject> getClusterObjects() {
		return m_lstClusterObjects;
	}
	 */
	@Override
	public Iterator<ClusterObject> iterator() {
		return m_lstClusterObjects.iterator();
	}
	
	public void add(ClusterObject obj) {
		m_lstClusterObjects.add(obj);
		obj.setClusterId(this.getClusterId());
	}
	
	public int getCount() {
		return m_lstClusterObjects.size();
	}
	
	public ClusterObject getObject(int objectId) {
		ClusterObject result = null;
		
		for (ClusterObject obj: m_lstClusterObjects) {
			if (obj.getObjectId() == objectId) {
				result = obj;
				break;
			}
		}
		
		return result; 
	}
	
	public void clear() {
		m_lstClusterObjects.clear();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (ClusterObject clObj : m_lstClusterObjects) {
			sb.append(clObj.getObjectId());
			sb.append(",");
		}
		
		String strResult = sb.toString();
		strResult = strResult.substring(0, strResult.length() - 1); // To get rid of the trailing comma...
		return strResult;
	}
}
