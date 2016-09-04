package chess;

import java.util.ArrayList;
import java.util.LinkedList;

public class ChessManipulate {

	private static final int EAGELENGTH = 8;
	private LinkedList<ArrayList<Integer>> resultList = new LinkedList<>();

	public ChessManipulate() {
		ArrayList<Integer> list = new ArrayList<>();
		long begin = System.currentTimeMillis();
		calculateQueens(list);
		long end = System.currentTimeMillis();
		System.out.println(resultList + "\n" + resultList.size() + "\n" + (end - begin));

	}

	public void calculateQueens(ArrayList<Integer> locationList) {

		if (locationList.size() == EAGELENGTH) {
			ArrayList<Integer> locationListClone = new ArrayList<>();
			locationListClone.addAll(locationList);
			resultList.add(locationListClone);
			return;
		}

		for (Integer i = 0; i < EAGELENGTH; i++) {
			if (!locationList.contains(i)) {
				boolean flag = true;
				for (int j = 0; j < locationList.size() && flag; j++) {
					if (Math.abs(i - locationList.get(j)) == Math.abs(locationList.size() - j))
						flag = false;
				}
				if (flag) {
					locationList.add(i);
					calculateQueens(locationList);
					locationList.remove(locationList.size() - 1);
				}
			}
		}
		return;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChessManipulate();
	}

}
