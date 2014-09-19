import java.math.BigInteger;
import java.util.Scanner;

public class P015 {
	static final int MODULO = 1000000007;

	public static void main(String args[]) {
		System.err.println(solve(20, 20, false));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			int M = in.nextInt();
			System.out.println(solve(N, M, true));
		}
		in.close();
	}

	static BigInteger solve(int N, int M, boolean needMod) {
		if (N > M) {
			return solve(M, N, needMod);
		}
		BigInteger number = BigInteger.ONE;
		for (int i = N + M; i > M; i--) {
			number = number.multiply(new BigInteger(i + ""));
		}
		for (int i = N; i > 1; i--) {
			number = number.divide(new BigInteger(i + ""));
		}
		if (needMod) {
			number = number.mod(new BigInteger(MODULO + ""));
		}
		return number;
	}
}
