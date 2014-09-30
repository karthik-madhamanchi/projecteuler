import java.util.Scanner;

public class P030 {
	public static void main(String args[]) {
		System.err.println(solve(5));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		System.out.println(solve(N));
		in.close();
	}

	static int solve(int N) {
		int sum = 0;
		int limit = findLimit(N);
		for (int i = 10; i <= limit; i++) {
			if (isValid(i, N)) {
				sum += i;
			}
		}
		return sum;
	}

	static int findLimit(int N) {
		int number = 10;
		int power = pow(9, N);
		int powerSum = power;
		while (powerSum > number) {
			powerSum += power;
			number *= 10;
		}
		return powerSum;
	}

	static boolean isValid(int number, int N) {
		int sum = 0;
		int n = number;
		while (n != 0) {
			int digit = n % 10;
			sum += pow(digit, N);
			n /= 10;
		}
		return sum == number;
	}

	static int pow(int base, int exponent) {
		int result = 1;
		for (int i = 0; i < exponent; i++) {
			result *= base;
		}
		return result;
	}
}
