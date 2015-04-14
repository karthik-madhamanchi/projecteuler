import java.util.Scanner;

public class P076 {
	static final int MODULO = 1000000007;
	static final int LIMIT = 1000;

	static int[][] ways;

	public static void main(String[] args) {
		System.err.println(solve(100));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static int solve(int N) {
		if (ways == null) {
			buildWays();
		}

		return ways[N][N] - 1;
	}

	static void buildWays() {
		ways = new int[LIMIT + 1][LIMIT + 1];
		ways[0][0] = 1;
		for (int i = 0; i <= LIMIT; i++) {
			for (int j = 1; j <= LIMIT; j++) {
				for (int k = 0; k * j <= i; k++) {
					ways[i][j] = addMod(ways[i][j], ways[i - k * j][j - 1]);
				}
			}
		}
	}

	static int addMod(int x, int y) {
		return (x + y) % MODULO;
	}
}
