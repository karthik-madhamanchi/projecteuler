// http://oeis.org/A068571

import java.util.Scanner;

public class P092 {
	static final int MODULO = 1000000007;

	public static void main(String[] args) {
		System.err.println(solve(7));

		Scanner in = new Scanner(System.in);
		int K = in.nextInt();
		System.out.println(solve(K));
		in.close();
	}

	static int solve(int K) {
		int[][] h = new int[K + 1][81 * K + 1];
		h[0][0] = 1;
		for (int n = 1; n < h.length; n++) {
			for (int x = 0; x <= 81 * n; x++) {
				for (int digit = 0; digit <= 9 && digit * digit <= x; digit++) {
					h[n][x] = addMod(h[n][x], h[n - 1][x - digit * digit]);
				}
			}
		}

		int happyCount = 0;
		for (int x = 1; x < h[K].length; x++) {
			if (isHappy(x)) {
				happyCount = addMod(happyCount, h[K][x]);
			}
		}
		return subtractMod(subtractMod(powMod(10, K), 1), happyCount);
	}

	static boolean isHappy(int number) {
		while (true) {
			if (number == 1) {
				return true;
			} else if (number == 4) {
				return false;
			}
			number = next(number);
		}
	}

	static int next(int number) {
		int result = 0;
		while (number != 0) {
			int digit = number % 10;
			result += digit * digit;
			number /= 10;
		}
		return result;
	}

	static int addMod(int a, int b) {
		return (int) (((long) a + b + MODULO) % MODULO);
	}

	static int subtractMod(int a, int b) {
		return addMod(a, -b);
	}

	static int powMod(int base, int exponent) {
		int result = 1;
		for (int i = 0; i < exponent; i++) {
			result = (int) ((long) result * base % MODULO);
		}
		return result;
	}
}
