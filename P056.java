import java.math.BigInteger;
import java.util.Scanner;

public class P056 {
	public static void main(String[] args) {
		System.err.println(solve(100));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		System.out.println(solve(N));
		in.close();
	}

	static int solve(int N) {
		int maxDigitSum = -1;
		for (int a = 1; a < N; a++) {
			BigInteger number = BigInteger.ONE;
			BigInteger biA = new BigInteger(a + "");
			for (int b = 1; b < N; b++) {
				number = number.multiply(biA);
				maxDigitSum = Math.max(maxDigitSum,
						computeDigitSum(number.toString()));
			}
		}
		return maxDigitSum;
	}

	static int computeDigitSum(String str) {
		int sum = 0;
		for (int i = 0; i < str.length(); i++) {
			sum += str.charAt(i) - '0';
		}
		return sum;
	}
}
