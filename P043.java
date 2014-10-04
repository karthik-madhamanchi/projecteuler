import java.util.Scanner;

public class P043 {
	static int[] DIVISORS = { 2, 3, 5, 7, 11, 13, 17 };
	static int[] digits;
	static long sum;

	public static void main(String[] args) {
		System.err.println(solve(9));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		System.out.println(solve(N));
		in.close();
	}

	static long solve(int N) {
		digits = new int[N + 1];
		for (int i = 0; i < digits.length; i++) {
			digits[i] = i;
		}

		sum = 0;
		search(0);
		return sum;
	}

	static void search(int index) {
		if (index == digits.length - 1) {
			if (isValid()) {
				sum += getNumber(0, digits.length - 1);
			}
		} else {
			for (int i = index; i < digits.length; i++) {
				int temp = digits[i];
				for (int j = i; j > index; j--) {
					digits[j] = digits[j - 1];
				}
				digits[index] = temp;
				search(index + 1);
				temp = digits[index];
				for (int j = index; j < i; j++) {
					digits[j] = digits[j + 1];
				}
				digits[i] = temp;
			}
		}
	}

	static boolean isValid() {
		for (int i = 1; i + 2 < digits.length; i++) {
			if (getNumber(i, i + 2) % DIVISORS[i - 1] != 0) {
				return false;
			}
		}
		return true;
	}

	static long getNumber(int begin, int end) {
		long number = 0;
		for (int i = begin; i <= end; i++) {
			number = number * 10 + digits[i];
		}
		return number;
	}
}
