package DataMining_PrefixSpan;

/**
 * PrefixSpan����ģʽ�ھ��㷨
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] agrs){
		String filePath = "G:\\softWork\\eclipseWorkstation\\DataMiningAlgorithm\\SequentialPatterns\\DataMining_PrefixSpan\\input.txt";
		//��С֧�ֶ���ֵ��
		double minSupportRate = 0.4;
		
		PrefixSpanTool tool = new PrefixSpanTool(filePath, minSupportRate);
		tool.prefixSpanCalculate();
	}
}

