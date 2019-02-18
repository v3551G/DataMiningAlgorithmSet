package DataMining_HITS;

/**
 * HITS¡¥Ω”∑÷ŒˆÀ„∑®
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		String filePath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\LinkMining\\DataMining_HITS\\input.txt";
		
		HITSTool tool = new HITSTool(filePath);
		tool.printResultPage();
	}
}
