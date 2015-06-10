import java.util.Scanner;

public class P086 {
	static final int M_LIMIT = 400000;
	static long[] cuboidNums;

	public static void main(String args[]) {
		for (int m = 1;; m++) {
			if (solve(m) > 1000000) {
				System.err.println(m);
				break;
			}
		}

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int M = in.nextInt();
			System.out.println(solve(M));
		}
		in.close();
	}

	static long solve(int M) {
		if (cuboidNums == null) {
			buildCuboidNums();
		}
		return cuboidNums[M];
	}

	static void buildCuboidNums() {
		int[] cuboids = new int[M_LIMIT + 1];
		for (int n = 1; n * n <= M_LIMIT; n++) {
			for (int m = n + 1; n * m <= M_LIMIT
					&& m * m - n * n <= 2 * M_LIMIT; m += 2) {
				if (gcd(m, n) != 1) {
					continue;
				}
				for (int k = 1;; k++) {
					int s = k * (m * m - n * n);
					int t = k * (2 * m * n);

					boolean valid = false;
					if (isValid(s, t)) {
						cuboids[s] += computeCuboidNum(s, t);
						valid = true;
					}
					if (isValid(t, s)) {
						cuboids[t] += computeCuboidNum(t, s);
						valid = true;
					}
					if (!valid) {
						break;
					}
				}
			}
		}

		cuboidNums = new long[M_LIMIT + 1];
		for (int i = 1; i < cuboidNums.length; i++) {
			cuboidNums[i] = cuboidNums[i - 1] + cuboids[i];
		}
	}

	static int gcd(int a, int b) {
		while (b != 0) {
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	static boolean isValid(int largestSide, int sumOfOtherTwoSides) {
		return largestSide <= M_LIMIT && sumOfOtherTwoSides <= 2 * largestSide;
	}

	static int computeCuboidNum(int largestSide, int sumOfOtherTwoSides) {
		return sumOfOtherTwoSides / 2
				- Math.max(0, sumOfOtherTwoSides - largestSide - 1);
	}
}
