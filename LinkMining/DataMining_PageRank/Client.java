package DataMining_PageRank;

/**
 * PageRank计算网页重要性/排名算法
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		String filePath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\LinkMining\\DataMining_PageRank\\input.txt";
		
		PageRankTool tool = new PageRankTool(filePath);
		tool.printPageRankValue();
	}
}
