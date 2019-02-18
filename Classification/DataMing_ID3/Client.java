package DataMing_ID3;

/**
 * ID3决策树分类算法测试场景类
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		String filePath = "C:\\Users\\Administrator\\Desktop\\DataMiningAlgorithm-master\\DataMiningAlgorithm\\Classification\\DataMining_ID3\\input.txt";
		
		ID3Tool tool = new ID3Tool(filePath);
		tool.startBuildingTree(true);
	}
}
