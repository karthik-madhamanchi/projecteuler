// http://en.wikipedia.org/wiki/Partition_%28number_theory%29

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class P078 {
	static final int LIMIT = 60000;

	static Map<Integer, int[]> modulo2ways = new HashMap<Integer, int[]>();

	public static void main(String[] args) {
		int sampleModulo = 1000000;
		for (int sampleN = 1;; sampleN++) {
			if (solve(sampleN, sampleModulo) == 0) {
				System.err.println(sampleN);
				break;
			}
		}

		final int MODULO = 1000000007;
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N, MODULO));
		}
		in.close();
	}

	static int solve(int N, int modulo) {
		if (!modulo2ways.containsKey(modulo)) {
			buildModulo2ways(modulo);
		}

		return modulo2ways.get(modulo)[N];
	}

	static void buildModulo2ways(int modulo) {
		int[] ways = new int[LIMIT + 1];
		ways[0] = 1;
		ways[1] = 1;
		for (int i = 2; i < ways.length; i++) {
			int sign = 1;
			for (int j = 1;; j++) {
				int temp = j * (3 * j - 1) / 2;
				if (i - temp < 0) {
					break;
				}
				ways[i] = addMod(ways[i], sign * ways[i - temp], modulo);

				temp = j * (3 * j + 1) / 2;
				if (i - temp < 0) {
					break;
				}
				ways[i] = addMod(ways[i], sign * ways[i - temp], modulo);

				sign = -sign;
			}
		}

		modulo2ways.put(modulo, ways);
	}

	static int addMod(int a, int b, int modulo) {
		return ((a + b) % modulo + modulo) % modulo;
	}
}
