import java.math.BigInteger;
import java.util.Scanner;

public class P058 {
	static final int CERTAINTY = 7;

	public static void main(String[] args) {
		System.err.println(solve(10));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		System.out.println(solve(N));
		in.close();
	}

	static long solve(int N) {
		long total = 1;
		long totalPrime = 0;
		for (long i = 2;; i++) {
			long bottomRight = (i * 2 - 1) * (i * 2 - 1);
			long bottomLeft = bottomRight - 2 * (i - 1);
			long topLeft = bottomLeft - 2 * (i - 1);
			long topRight = topLeft - 2 * (i - 1);
			if (isPrime(bottomLeft)) {
				totalPrime++;
			}
			if (isPrime(topLeft)) {
				totalPrime++;
			}
			if (isPrime(topRight)) {
				totalPrime++;
			}
			total += 4;
			if (totalPrime * 100 < total * N) {
				return i * 2 - 1;
			}
		}
	}

	static boolean isPrime(long number) {
		return new BigInteger(number + "").isProbablePrime(CERTAINTY);
	}
}
