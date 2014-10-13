import java.math.BigInteger;
import java.util.Scanner;

public class P053 {
	public static void main(String[] args) {
		System.err.println(solve(100, 1000000));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		long K = in.nextLong();
		System.out.println(solve(N, K));
		in.close();
	}

	static int solve(int N, long K) {
		int count = 0;
		BigInteger limit = new BigInteger(K + "");
		for (int n = 1; n <= N; n++) {
			for (int r = 0; r + r <= n; r++) {
				BigInteger c = C(n, r);
				if (c.compareTo(limit) > 0) {
					if (n % 2 == 0) {
						if (r + r == n) {
							count++;
						} else {
							count += 1 + (n / 2 - r) * 2;
						}
					} else {
						count += (n / 2 - r + 1) * 2;
					}
					break;
				}
			}
		}
		return count;
	}

	static BigInteger C(int n, int r) {
		BigInteger result = BigInteger.ONE;
		for (int i = n; i > n - r; i--) {
			result = result.multiply(new BigInteger(i + ""));
		}
		for (int i = 1; i <= r; i++) {
			result = result.divide(new BigInteger(i + ""));
		}
		return result;
	}
}
