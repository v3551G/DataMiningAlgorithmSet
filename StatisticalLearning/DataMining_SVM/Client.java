package DataMining_SVM;

/**
 * SVM֧������������������
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		//ѵ���������ļ�·��
		String trainDataPath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\StatisticalLearning\\DataMining_SVM\\trainInput.txt";
		//���������ļ�·��
		String testDataPath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\StatisticalLearning\\DataMining_SVM\\testInput.txt";
		
		SVMTool tool = new SVMTool(trainDataPath);
		//�Բ������ݽ���svm֧������������
		tool.svmPredictData(testDataPath);
	}

}
