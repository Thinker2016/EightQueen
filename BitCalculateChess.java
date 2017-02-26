package chess;

/**
 * 
 * @author wenzhao 皇后问题的位运算解法. 为了减少时间和存储开销, 本程序只记录了解法总数, 并没有记录下问题的每一种解.
 * 
 */
public class BitCalculateChess {

	private static int EAGELENGTH;// 皇后个数，也就是棋盘的行数和列数.
	private int maskCode;// 将棋盘没有使用到的位数全部置0, 使用到的位数置1.
	private int count;// 记录解的个数.

	public BitCalculateChess() {
		maskCode = (int) (Math.pow(2, EAGELENGTH) - 1);// 当皇后数为16时，maskCode的值为0xFFFF(左16位置0,右16位置1).
		count = 0;
		long start = System.currentTimeMillis();
		calculate(0, 0, 0);
		long end = System.currentTimeMillis();
		System.out.format("共有%d种解法, 用时%d毫秒\n", count, end - start);
	}

	/**
	 * 计算皇后问题的解. a,b,c表示该行中与已有皇后在同一列和同一斜线上的位置.
	 * 
	 * @param a
	 *            从左上到右下方向的斜线.
	 * @param b
	 *            列.
	 * @param c
	 *            从左下到右上方向的斜线.
	 */
	public void calculate(int a, int b, int c) {
		if (b == maskCode) {// 求出了一个解
			count++;
			return;
		}
		int d = ~(a | b | c) & maskCode;// 这是本行能放置皇后的位置.
		while (d > 0) {// 本行存在能放置皇后的位置
			int bit = d & (-d);// 本行最右边能放置皇后的位置. 这里的位置使用采用的是从右到左的循环.
			calculate((a | bit) >> 1, b | bit, (c | bit) << 1);
			d -= bit;// 移除这个位置, 这样下一次循环的bit使用的就是下一个位置.
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length > 0)
			EAGELENGTH = Integer.parseInt(args[0]);
		else
			EAGELENGTH = 8;
		if (EAGELENGTH < 0 || EAGELENGTH > 31)
			System.out.println("本程序只能求解阶数0大于-1且小于32的皇后问题。");
		new BitCalculateChess();
	}

}
