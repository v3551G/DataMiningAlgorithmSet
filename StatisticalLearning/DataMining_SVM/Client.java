package DataMining_SVM;

/**
 * SVM支持向量机场景调用类
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		//训练集数据文件路径
		String trainDataPath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\StatisticalLearning\\DataMining_SVM\\trainInput.txt";
		//测试数据文件路径
		String testDataPath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\StatisticalLearning\\DataMining_SVM\\testInput.txt";
		
		SVMTool tool = new SVMTool(trainDataPath);
		//对测试数据进行svm支持向量机分类
		tool.svmPredictData(testDataPath);
	}

}
