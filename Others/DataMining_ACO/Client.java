package DataMining_ACO;

/**
 * ��Ⱥ�㷨������
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		//��������
		String filePath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\Others\\DataMining_ACO\\input.txt";
		//��������
		int antNum;
		//��Ⱥ�㷨��������
		int loopCount;
		//���Ʋ���
		double alpha;
		double beita;
		double p;
		double Q;
		
		antNum = 3;
		alpha = 0.5;
		beita = 1;
		p = 0.5;
		Q = 5;
		loopCount = 5;
		
		ACOTool tool = new ACOTool(filePath, antNum, alpha, beita, p, Q);
		tool.antStartSearching(loopCount);
	}
}
