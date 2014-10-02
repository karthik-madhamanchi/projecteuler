import java.util.Scanner;

public class P034 {
	static final int[] FACTORIALS = new int[10];
	static {
		FACTORIALS[0] = 1;
		for (int i = 1; i < FACTORIALS.length; i++) {
			FACTORIALS[i] = FACTORIALS[i - 1] * i;
		}
	}

	public static void main(String[] args) {
		System.err.println(solve(findLimit(), true));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		System.out.println(solve(N, false));
		in.close();
	}

	static int findLimit() {
		for (int minNumber = 1, maxFactorialSum = FACTORIALS[9];; minNumber *= 10, maxFactorialSum += FACTORIALS[9]) {
			if (minNumber > maxFactorialSum) {
				return maxFactorialSum - FACTORIALS[9] + 1;
			}
		}
	}

	static int solve(int N, boolean sameOrDivisible) {
		int sum = 0;
		for (int i = 10; i < N; i++) {
			int factorialSum = computeFactorialSum(i);
			if ((sameOrDivisible && factorialSum == i)
					|| (!sameOrDivisible && factorialSum % i == 0)) {
				sum += i;
			}
		}
		return sum;
	}

	static int computeFactorialSum(int number) {
		int factorialSum = 0;
		int p = number;
		while (p != 0) {
			factorialSum += FACTORIALS[p % 10];
			p /= 10;
		}
		return factorialSum;
	}
}
