import java.math.BigInteger;
import java.util.Scanner;

public class P020 {
	public static void main(String args[]) {
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
		BigInteger product = BigInteger.ONE;
		for (int i = 2; i <= N; i++) {
			product = product.multiply(new BigInteger(i + ""));
		}
		String str = product.toString();
		int sum = 0;
		for (int i = 0; i < str.length(); i++) {
			sum += str.charAt(i) - '0';
		}
		return sum;
	}
}
