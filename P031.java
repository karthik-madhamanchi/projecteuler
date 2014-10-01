import java.util.Scanner;

public class P031 {
	static final int[] COINS = { 1, 2, 5, 10, 20, 50, 100, 200 };
	static final int LIMIT = 100000;
	static final int MODULO = 1000000007;
	static int[] results = new int[LIMIT + 1];

	public static void main(String[] args) {
		buildResults();

		System.err.println(solve(200));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static void buildResults() {
		int[][] ways = new int[LIMIT + 1][COINS.length];
		for (int i = 0; i < ways[0].length; i++) {
			ways[0][i] = 1;
		}
		for (int i = 1; i < ways.length; i++) {
			for (int j = 0; j < ways[0].length; j++) {
				for (int k = 0; k <= j; k++) {
					int prevNumber = i - COINS[k];
					if (prevNumber >= 0) {
						ways[i][j] = addMod(ways[i][j], ways[prevNumber][k]);
					}
				}
			}
		}
		for (int i = 0; i < results.length; i++) {
			results[i] = ways[i][ways[0].length - 1];
		}
	}

	static int addMod(int a, int b) {
		return (a + b) % MODULO;
	}

	static int solve(int N) {
		return results[N];
	}
}
