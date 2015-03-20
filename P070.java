import java.util.Arrays;
import java.util.Scanner;

public class P070 {
	public static void main(String[] args) {
		System.err.println(solve(10000000));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		System.out.println(solve(N));
		in.close();
	}

	static int solve(int N) {
		boolean[] primes = new boolean[N];
		Arrays.fill(primes, true);

		int[] totients = new int[N];
		for (int i = 2; i < totients.length; i++) {
			totients[i] = i;
		}

		for (int i = 2; i < N; i++) {
			if (primes[i]) {
				for (int j = i; j < N; j += i) {
					if (j != i) {
						primes[j] = false;
					}
					totients[j] = totients[j] / i * (i - 1);
				}
			}
		}

		int result = -1;
		for (int i = 2; i < N; i++) {
			if (hasSameDigits(i, totients[i])
					&& (result < 0 || (long) i * totients[result] < (long) result
							* totients[i])) {
				result = i;
			}
		}
		return result;
	}

	static boolean hasSameDigits(int a, int b) {
		int digitsA[] = getDigits(a);
		int digitsB[] = getDigits(b);
		for (int i = 0; i < 10; i++) {
			if (digitsA[i] != digitsB[i]) {
				return false;
			}
		}
		return true;
	}

	static int[] getDigits(int number) {
		int digits[] = new int[10];
		while (number != 0) {
			digits[number % 10]++;
			number /= 10;
		}
		return digits;
	}
}
