package DataMining_CABDDCC;

/**
 * 基于连通图的分裂聚类算法
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] agrs){
		String filePath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\Others\\DataMining_CABDDCC\\graphData.txt";
		//连通距离阈值
		int length = 3;
		
		CABDDCCTool tool = new CABDDCCTool(filePath, length);
		tool.splitCluster();
	}
}
