package chess;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 * @author wenzhao 求解皇后问题的算法
 */
public class ChessManipulate {

	private static int EAGELENGTH;// 棋盘的行数和列数（国际象棋棋盘的行数和列数相等），也就是皇后的数量
	private LinkedList<ArrayList<Integer>> resultList = new LinkedList<>(); // 存放所有解

	public ChessManipulate() {
		ArrayList<Integer> list = new ArrayList<>();
		long begin = System.currentTimeMillis();
		calculateQueens(list);// 求皇后问题的解
		long end = System.currentTimeMillis();
		System.out.format("算题共用时%d毫秒\n", end - begin);
		System.out.format("一共有%d个解\n", resultList.size());
		Scanner s = new Scanner(System.in);
		System.out.println("请输入解的输出方式，1是标准输出，2是输出至当前目录下的Queen.txt文件中，3是不输出结果，默认是标准输出。");
		// 输入1,2或3之后按回车键; 一般情况下，标准输出就是输出至控制台
		String method = s.nextLine().trim();
		s.close();
		if (method.isEmpty() || method.equals("1"))
			System.out.println(resultList);
		else if (method.equals("2")) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Queen.txt")));
				writer.write(resultList.toString());
				writer.flush();
				writer.close();
				System.out.println("已将解输出至当前目录下的Queen.txt文件中。");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 计算皇后问题的解. 参数locationList的第i个元素表示棋盘的第i行,
	 * 第i个元素的值表示第i个皇后所处的列数（我们把位于第i行的皇后称为第i个皇后）
	 * 
	 * @param locationList
	 *            存放所有皇后所在位置的数组列表.
	 */
	public void calculateQueens(ArrayList<Integer> locationList) {

		if (locationList.size() == EAGELENGTH) {
			// 已经所有行和列上都有了皇后，此时求出了一个新的解，将该解加入到resultList当中
			ArrayList<Integer> locationListClone = new ArrayList<>();
			locationListClone.addAll(locationList);
			resultList.add(locationListClone);
			return;
		}

		for (int i = 0; i < EAGELENGTH; i++) {
			// i表示新加入的皇后所在的列
			if (!locationList.contains(i)) {
				boolean flag = true;// flag用于记录是否有两个皇后处于同一对角线上;
									// flag为真表示尚未检查出有两个皇后位于同一对角线上,
									// 为假表示已检查到有两皇后位于同一对角线上
				for (int j = 0; j < locationList.size() && flag; j++) {
					if (Math.abs(i - locationList.get(j)) == Math.abs(locationList.size() - j))
						flag = false;// 出现了两皇后处于同一对角线的情形，
				}
				if (flag) {
					// 新的皇后没有与之前的所有皇后处于同一行、同一列或同一对角线上，将此位置加入到locationList当中
					locationList.add(i);
					calculateQueens(locationList);// 继续递归求出该情况下的所有解
					locationList.remove(locationList.size() - 1);// 将该位置从locationList当中移除，以便继续求出其它情况下的解
				}
			}
		}
		return;

	}

	/**
	 * main方法
	 * 
	 * @param args
	 *            若参数不为空，则args[0]为皇后数量.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length > 0)
			EAGELENGTH = Integer.parseInt(args[0]);
		else
			EAGELENGTH = 8;
		new ChessManipulate();
	}

}
