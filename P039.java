import java.util.Scanner;

public class P039 {
	static final int LIMIT = 5000000;
	static int[] results;

	public static void main(String[] args) {
		buildResults();

		System.err.println(solve(1000));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static void buildResults() {
		int[] solutionNums = new int[LIMIT + 1];
		int baseSum;
		for (int m = 1; 2 * m * m < solutionNums.length; m++) {
			for (int n = 1; n < m
					&& (baseSum = 2 * m * (m + n)) < solutionNums.length; n++) {
				if ((m - n) % 2 == 0 || gcd(m, n) != 1) {
					continue;
				}
				for (int i = baseSum; i < solutionNums.length; i += baseSum) {
					solutionNums[i]++;
				}
			}
		}

		int result = 0;
		results = new int[solutionNums.length];
		for (int i = 0; i < results.length; i++) {
			if (solutionNums[i] > solutionNums[result]) {
				result = i;
			}
			results[i] = result;
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

	static int solve(int N) {
		return results[N];
	}
}
