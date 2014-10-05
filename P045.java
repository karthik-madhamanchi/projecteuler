import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P045 {
	static final int HEXAGONAL_START_N = 144;

	public static void main(String[] args) {
		System.err.println(solveEuler());

		Scanner in = new Scanner(System.in);
		long N = in.nextLong();
		int a = in.nextInt();
		int b = in.nextInt();
		solve(N, a, b).forEach(result -> System.out.println(result));
		in.close();
	}

	static long solveEuler() {
		for (int n = HEXAGONAL_START_N;; n++) {
			long hn = H(n);
			if (isPentagonal(hn)) {
				return hn;
			}
		}
	}

	static boolean isTriangle(long number) {
		long x = (long) Math.sqrt(number * 2);
		return x * (x + 1) / 2 == number;
	}

	static boolean isPentagonal(long number) {
		long x = (long) (Math.sqrt(2.0 * number / 3 + 1.0 / 36) + 1.0 / 6);
		return x * (3 * x - 1) / 2 == number;
	}

	static long P(int n) {
		return n * (3 * n - 1L) / 2;
	}

	static long H(int n) {
		return n * (2 * n - 1L);
	}

	static List<Long> solve(long N, int a, int b) {
		List<Long> results = new ArrayList<Long>();
		for (int n = 1;; n++) {
			long number;
			if (b == 5) {
				number = P(n);
			} else { // b == 6
				number = H(n);
			}

			if (number >= N) {
				break;
			}

			if ((a == 3 && isTriangle(number))
					|| (a == 5 && isPentagonal(number))) {
				results.add(number);
			}
		}
		return results;
	}
}
