package DataMining_RoughSets;

/**
 * ´Ö²Ú¼¯Ô¼¼òËã·¨
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		String filePath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\RoughSets\\DataMining_RoughSets\\input.txt";
		
		RoughSetsTool tool = new RoughSetsTool(filePath);
		tool.findingReduct();
	}
}
