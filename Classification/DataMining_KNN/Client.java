package DataMining_KNN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * k最近邻算法场景类型
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		String trainDataPath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\Classification\\DataMining_KNN\\trainInput.txt";
		String testDataPath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\Classification\\DataMining_KNN\\testinput.txt";
		
		KNNTool tool = new KNNTool(trainDataPath, testDataPath);
		tool.knnCompute(3);
		
	}
}
