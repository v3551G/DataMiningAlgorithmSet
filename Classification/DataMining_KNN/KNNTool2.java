package DataMining_KNN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import sun.security.jca.GetInstance.Instance;

//import org.apache.activemq.filter.ComparisonExpression;

/**
 * k������㷨������
 * 
 * @author lyq
 * 
 */
public class KNNTool2 {
	// Ϊ4���������Ȩ�أ�Ĭ��Ȩ�ر�һ��
	//public int[] classWeightArray = new int[] { 1, 1, 1, 1 };

	// ����Ĳ�ͬ����
	private ArrayList<String> classTypes;
	// �������
	private Instance[] resultSamples;
	// ѵ���������б�����
	private Instance[] trainSamples;
	// ѵ��������
	private Instance[] trainData;
	// ���Լ�����
	private Instance[] testData;

	/**
	 * ������������������ŷ����þ���
	 * 
	 * @param f1
	 *            ���Ƚ�����1
	 * @param f2
	 *            ���Ƚ�����2
	 * @return
	 */
	private int computeEuclideanDistance(Instance s1, Instance s2) {
		String[] f1 = s1.getFeatures();
		String[] f2 = s2.getFeatures();
		// ŷ����þ���
		int distance = 0;

		for (int i = 0; i < f1.length; i++) {
			int subF1 = Integer.parseInt(f1[i]);
			int subF2 = Integer.parseInt(f2[i]);

			distance += (subF1 - subF2) * (subF1 - subF2);
		}

		return distance;
	}

	/**
	 * ����K�����
	 * @param k
	 * �ڶ��ٵ�k��Χ��
	 */
	public void knnCompute(int k) {
		String className = "";
		String[] tempF = null;
		Sample temp;
		resultSamples = new ArrayList<Sample>();
		trainSamples = new ArrayList<Sample>();
		// ����������
		HashMap<String, Integer> classCount;
		// ���Ȩ�ر�
		HashMap<String, Integer> classWeight = new HashMap<String, Integer>();
		// ���Ƚ���������ת�������������
		for (String[] s : testData) {
			temp = new Sample(s);
			resultSamples.add(temp);
		}

		for (String[] s : trainData) {
			className = s[0];
			tempF = new String[s.length - 1];
			System.arraycopy(s, 1, tempF, 0, s.length - 1); //copy
			temp = new Sample(className, tempF);
			trainSamples.add(temp);
		}

		// �������������ĵ�ѵ��������
		ArrayList<Sample> kNNSample = new ArrayList<Sample>();
		// ����ѵ�����ݼ������������������K��ѵ��������
		for (Sample s : resultSamples) {
			classCount = new HashMap<String, Integer>();
			int index = 0;
			for (String type : classTypes) {
				// ��ʼʱ����Ϊ0
				classCount.put(type, 0);
				classWeight.put(type, classWeightArray[index++]);
			}
			for (Sample tS : trainSamples) {
				int dis = computeEuclideanDistance(s, tS);
				tS.setDistance(dis);
			}

			Collections.sort(trainSamples);
			kNNSample.clear();
			// ��ѡ��ǰk��������Ϊ�����׼
			for (int i = 0; i < trainSamples.size(); i++) {
				if (i < k) {
					kNNSample.add(trainSamples.get(i));
				} else {
					break;
				}
			}
			// �ж�K��ѵ�����ݵĶ����ķ����׼
			for (Sample s1 : kNNSample) {
				int num = classCount.get(s1.getClassName());
				// ���з���Ȩ�صĵ��ӣ�Ĭ�����Ȩ��ƽ�ȣ������иı䣬����Ȩ�ش�Զ��Ȩ��С
				num += classWeight.get(s1.getClassName()); // ���ڲ�ͬ���Ȩ����ͬ���� num ++
				classCount.put(s1.getClassName(), num);
			}

			int maxCount = 0;
			// ɸѡ��k��ѵ��������������һ������
			for (Map.Entry entry : classCount.entrySet()) {
				if ((Integer) entry.getValue() > maxCount) {
					maxCount = (Integer) entry.getValue();
					s.setClassName((String) entry.getKey());
				}
			}

			System.out.print("��������������");
			for (String s1 : s.getFeatures()) {
				System.out.print(s1 + " ");
			}
			System.out.println("���ࣺ" + s.getClassName());
		}
	}
}
