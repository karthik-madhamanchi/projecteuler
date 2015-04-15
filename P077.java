import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P077 {
	static final int LIMIT = 1000;
	static long[][] ways;

	public static void main(String[] args) {
		for (int sampleN = 1;; sampleN++) {
			if (solve(sampleN) > 5000) {
				System.err.println(sampleN);
				break;
			}
		}

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static long solve(int N) {
		if (ways == null) {
			buildWays();
		}

		return ways[N][N];
	}

	static void buildWays() {
		ways = new long[LIMIT + 1][];
		for (int i = 0; i < ways.length; i++) {
			ways[i] = new long[i + 1];
		}

		List<Integer> primes = buildPrimes();

		ways[0][0] = 1;
		for (int i = 1; i < ways.length; i++) {
			for (int j = 0; j < ways[i].length; j++) {
				for (int prime : primes) {
					if (prime > j) {
						break;
					}

					ways[i][j] += ways[i - prime][Math.min(i - prime, prime)];
				}
			}
		}
	}

	static List<Integer> buildPrimes() {
		boolean[] primeArray = new boolean[LIMIT + 1];
		Arrays.fill(primeArray, 2, primeArray.length, true);
		for (int i = 0; i < primeArray.length; i++) {
			if (primeArray[i]) {
				for (int j = i * i; j < primeArray.length; j += i) {
					primeArray[j] = false;
				}
			}
		}

		List<Integer> primes = new ArrayList<Integer>();
		for (int i = 0; i < primeArray.length; i++) {
			if (primeArray[i]) {
				primes.add(i);
			}
		}
		return primes;
	}
}
