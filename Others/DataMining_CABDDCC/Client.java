package DataMining_CABDDCC;

/**
 * ������ͨͼ�ķ��Ѿ����㷨
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] agrs){
		String filePath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\Others\\DataMining_CABDDCC\\graphData.txt";
		//��ͨ������ֵ
		int length = 3;
		
		CABDDCCTool tool = new CABDDCCTool(filePath, length);
		tool.splitCluster();
	}
}
