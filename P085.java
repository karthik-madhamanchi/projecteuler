import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class P085 {
	static TreeMap<Integer, Integer> rectangleNum2Area;
	static final int RECTANGLE_NUM_LIMIT = 2000000;

	public static void main(String[] args) {
		System.err.println(solve(2000000));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int target = in.nextInt();
			System.out.println(solve(target));
		}
		in.close();
	}

	static int solve(int target) {
		if (rectangleNum2Area == null) {
			buildRectangleNum2Area();
		}

		Integer lowerRectangleNum = rectangleNum2Area.lowerKey(target);
		Integer higherRectangleNum = rectangleNum2Area.higherKey(target);
		int area;
		if (rectangleNum2Area.containsKey(target)) {
			area = rectangleNum2Area.get(target);
		} else if (lowerRectangleNum != null && higherRectangleNum != null
				&& lowerRectangleNum + higherRectangleNum == target * 2) {
			area = Math.max(rectangleNum2Area.get(lowerRectangleNum),
					rectangleNum2Area.get(higherRectangleNum));
		} else if (higherRectangleNum == null
				|| (lowerRectangleNum != null && lowerRectangleNum
						+ higherRectangleNum > target * 2)) {
			area = rectangleNum2Area.get(lowerRectangleNum);
		} else {
			area = rectangleNum2Area.get(higherRectangleNum);
		}
		return area;
	}

	static void buildRectangleNum2Area() {
		rectangleNum2Area = new TreeMap<Integer, Integer>();
		List<Integer> sideNums = buildSideNums();
		int maxRectangleNum = -1;
		for (int i = 0; i < sideNums.size(); i++) {
			if (maxRectangleNum >= RECTANGLE_NUM_LIMIT
					&& sideNums.get(i) * sideNums.get(i) > maxRectangleNum) {
				break;
			}
			for (int j = i; j < sideNums.size(); j++) {
				int rectangleNum = sideNums.get(i) * sideNums.get(j);
				int area = i * j;
				if (!rectangleNum2Area.containsKey(rectangleNum)
						|| rectangleNum2Area.get(rectangleNum) < area) {
					rectangleNum2Area.put(rectangleNum, area);
				}
				if (rectangleNum >= RECTANGLE_NUM_LIMIT) {
					maxRectangleNum = rectangleNum;
					break;
				}
			}
		}
	}

	static List<Integer> buildSideNums() {
		List<Integer> sideNums = new ArrayList<Integer>();
		int sideNum = 0;
		for (int i = 0; sideNum <= RECTANGLE_NUM_LIMIT; i++) {
			sideNum += i;
			sideNums.add(sideNum);
		}
		return sideNums;
	}
}
