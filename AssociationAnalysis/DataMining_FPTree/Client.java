package DataMining_FPTree;

/**
 * FPTree频繁模式树算法
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		String filePath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\AssociationAnalysis\\DataMining_FPTree\\testInput.txt";
		//最小支持度阈值
		int minSupportCount = 2;
		
		FPTreeTool tool = new FPTreeTool(filePath, minSupportCount);
		tool.startBuildingTree();
	}
}
