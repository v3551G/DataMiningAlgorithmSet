package DataMining_GSpan;

/**
 * gSpanƵ����ͼ�ھ��㷨
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		//���������ļ���ַ
		String filePath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\GraphMining\\DataMining_GSpan\\input.txt";
		//��С֧�ֶ���
		double minSupportRate = 0.2;
		
		GSpanTool tool = new GSpanTool(filePath, minSupportRate);
		tool.freqGraphMining();
	}
}
