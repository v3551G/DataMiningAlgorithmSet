package DataMining_KMeans;

/**
 * K-means��K��ֵ���㷨������
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		String filePath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\Clustering\\DataMining_KMeans\\input.txt";
		//�������������趨
		int classNum = 3;
		
		KMeansTool tool = new KMeansTool(filePath, classNum);
		tool.kMeansClustering();
	}
}