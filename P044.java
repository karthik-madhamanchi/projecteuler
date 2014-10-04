import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P044 {
	public static void main(String[] args) {
		System.err.println(solveEuler());

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		solve(N, K).forEach(result -> System.out.println(result));
		in.close();
	}

	static long solveEuler() {
		for (int n = 1;; n++) {
			long pn = P(n);
			for (long i = 1; i * i <= 2 * pn; i++) {
				if (2 * pn % i != 0) {
					continue;
				}
				long temp = 1 + 2 * pn / i - 3 * i;
				if (temp <= 0 || temp % 6 != 0) {
					continue;
				}
				long a = temp / 6;
				long b = a + i;
				long pa = a * (3 * a - 1) / 2;
				long pb = b * (3 * b - 1) / 2;
				if (isPentagonal(pa + pb)) {
					return pn;
				}
			}
		}
	}

	static List<Long> solve(int N, int K) {
		List<Long> results = new ArrayList<Long>();
		for (int n = K + 1; n < N; n++) {
			long pn = P(n);
			if (isPentagonal(pn - P(n - K)) || isPentagonal(pn + P(n - K))) {
				results.add(pn);
			}
		}
		return results;
	}

	static long P(int x) {
		return x * (3 * x - 1L) / 2;
	}

	static boolean isPentagonal(long number) {
		int x = (int) Math
				.round((Math.sqrt(2.0 * number / 3 + 1.0 / 36) + 1.0 / 6));
		return P(x) == number;
	}
}
