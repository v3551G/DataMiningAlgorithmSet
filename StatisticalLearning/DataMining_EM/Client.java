package DataMining_EM;

/**
 * EM��������㷨����������
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		String filePath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\StatisticalLearning\\DataMining_EM\\input.txt";
		
		EMTool tool = new EMTool(filePath);
		tool.readDataFile();
		tool.exceptMaxStep();
	}
}
