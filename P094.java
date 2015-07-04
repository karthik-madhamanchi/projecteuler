// http://www.alpertron.com.ar/QUAD.HTM

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class P094 {
	public static void main(String[] args) {
		System.err.println(solve(1000000000));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			long N = in.nextLong();
			System.out.println(solve(N));
		}
		in.close();
	}

	static long solve(long N) {
		long perimeterSum = 0;

		// 3x^2 + 4x + 1 = y ^ 2, sides = (2x, 2x + 1, 2x + 1)
		for (long x : search(N, -2, -1, -2, -3, -2, -2, new int[][] {
				{ -1, 0 }, { 0, -1 }, { 0, 1 } })) {
			long perimeter = 6 * x + 2;
			if (perimeter <= N) {
				perimeterSum += perimeter;
			}
		}

		// 3x^2 - 4x + 1 = y ^ 2, sides = (2x, 2x - 1, 2x - 1)
		for (long x : search(N, -2, -1, 2, -3, -2, 2, new int[][] { { 1, 0 },
				{ 0, -1 }, { 0, 1 } })) {
			long perimeter = 6 * x - 2;
			if (perimeter <= N) {
				perimeterSum += perimeter;
			}
		}

		return perimeterSum;
	}

	static Set<Long> search(long N, int P, int Q, int K, int R, int S, int L,
			int[][] initialXYs) {
		Set<Long> xList = new HashSet<Long>();
		for (int[] initialXY : initialXYs) {
			xList.addAll(searchSolutions(N, P, Q, K, R, S, L, initialXY[0],
					initialXY[1]));
		}
		return xList;
	}

	static Set<Long> searchSolutions(long N, int P, int Q, int K, int R, int S,
			int L, int x0, int y0) {
		Set<Long> xList = new HashSet<Long>();
		long x = x0;
		long y = y0;
		while (Math.abs(x) <= N) {
			if (x > 1) {
				xList.add(x);
			}

			long nextX = P * x + Q * y + K;
			long nextY = R * x + S * y + L;
			x = nextX;
			y = nextY;
		}
		return xList;
	}
}
