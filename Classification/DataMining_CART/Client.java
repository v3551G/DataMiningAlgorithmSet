package DataMining_CART;

public class Client {
	public static void main(String[] args){
		String filePath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\Classification\\DataMining_CART\\input.txt";
		
		CARTTool tool = new CARTTool(filePath);
		
		tool.startBuildingTree();
	}
}
