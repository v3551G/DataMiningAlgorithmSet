package DataMining_DBSCAN;

/**
 * Dbscan�����ܶȵľ����㷨������
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		String filePath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\Others\\DataMining_DBSCAN\\input.txt";
		//��ɨ��뾶
		double eps = 3;
		//��С����������ֵ
		int minPts = 3;
		
		DBSCANTool tool = new DBSCANTool(filePath, eps, minPts);
		tool.dbScanCluster();
	}
}
