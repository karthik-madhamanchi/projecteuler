import java.util.Scanner;

public class P072 {
	static final int LIMIT = 1000000;
	static long[] results;

	public static void main(String[] args) {
		System.err.println(solve(1000000));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static long solve(int N) {
		if (results == null) {
			buildResults();
		}

		return results[N];
	}

	static void buildResults() {
		results = new long[LIMIT + 1];
		for (int i = 2; i < results.length; i++) {
			results[i] = results[i - 1] + computePhi(i);
		}
	}

	static int computePhi(int n) {
		int phi = n;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0 && isPrime(i)) {
				phi = phi / i * (i - 1);
				while (n % i == 0) {
					n /= i;
				}
			}
		}
		if (n > 1) {
			phi = phi / n * (n - 1);
		}
		return phi;
	}

	static boolean isPrime(int number) {
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}
