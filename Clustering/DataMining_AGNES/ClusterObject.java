import java.util.BitSet;

public class ClusterObject {
	
	private BitSet m_vector = null;
	public BitSet getVector() {
		return m_vector;
	}	
	
	private int m_nObjectId = -1;
	public void setObjectId(int m_nObjectId) {
		this.m_nObjectId = m_nObjectId;
	}
	public int getObjectId() {
		return m_nObjectId;
	}
	
	
	private int m_nClusterId = -1;
	public void setClusterId(int m_nClusterId) {
		this.m_nClusterId = m_nClusterId;
	}
	public int getClusterId() {
		return m_nClusterId;
	}
	
	
	private int m_nSize = 0;
	public void setSize(int m_nSize) {
		this.m_nSize = m_nSize;
	}
	public int getSize() {
		return m_nSize;
	}
	
	
	public ClusterObject() {
		m_vector = new BitSet();
	}
	
	public ClusterObject(int nObjectId) {
		m_vector = new BitSet();
		m_nObjectId = nObjectId;	
	}
	
	public ClusterObject(int nObjectId, int nClusterId) {
		m_vector = new BitSet();
		m_nObjectId = nObjectId;
		m_nClusterId = nClusterId;
	}
	
	
	public ClusterObject(boolean...params) {
		for (int i = 0; i < params.length; i++) {
			setBit(i, params[i]);
		}
	}
	
	public ClusterObject(int...params) {
		for (int i = 0; i < params.length; i++) {
			setBit(i, HelperMethods.Int2Bool(params[i]));
		}
	}
	
	
	public static ClusterObject fromRow(String strLine) {
		return ClusterObject.fromRow(-1, strLine);
	}
	
	public static ClusterObject fromRow(int nObjectId, String strLine) {
		
		String[] strItems = strLine.split(",");
		ClusterObject newObj = new ClusterObject();
		newObj.setSize(strItems.length);
		
		for (int i = 0; i < strItems.length; i++) {
			newObj.setBit(i, HelperMethods.Int2Bool(Integer.parseInt(strItems[i])));
		}
		
		return newObj;
	}
	

	public void setBit(int index, boolean value) {
		m_vector.set(index, value);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < getSize(); i++) {
			sb.append(HelperMethods.Bool2Int(m_vector.get(i)));
		}
		
		return super.toString();
	}


}
