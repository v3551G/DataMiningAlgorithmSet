package DataMining_FPTree;

/**
 * FPTreeƵ��ģʽ���㷨
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		String filePath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\AssociationAnalysis\\DataMining_FPTree\\testInput.txt";
		//��С֧�ֶ���ֵ
		int minSupportCount = 2;
		
		FPTreeTool tool = new FPTreeTool(filePath, minSupportCount);
		tool.startBuildingTree();
	}
}
