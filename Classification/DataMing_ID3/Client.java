package DataMing_ID3;

/**
 * ID3�����������㷨���Գ�����
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
