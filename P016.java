import java.math.BigInteger;
import java.util.Scanner;

public class P016 {
	public static void main(String args[]) {
		System.err.println(solve(1000));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static int solve(int N) {
		BigInteger number = BigInteger.ONE;
		for (int i = 0; i < N; i++) {
			number = number.multiply(new BigInteger("2"));
		}
		String str = number.toString();
		int sum = 0;
		for (int i = 0; i < str.length(); i++) {
			sum += str.charAt(i) - '0';
		}
		return sum;
	}
}
