import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class P041 {
	static int digits[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	static List<Integer> pandigitalPrimes = new ArrayList<Integer>();

	public static void main(String[] args) {
		buildPandigitalPrimes();

		System.err.println(solve(Integer.MAX_VALUE));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static void buildPandigitalPrimes() {
		for (int i = 0; i < digits.length; i++) {
			search(0, i);
		}
	}

	static void search(int curIndex, int endIndex) {
		if (curIndex == endIndex) {
			int number = 0;
			for (int i = 0; i <= endIndex; i++) {
				number = number * 10 + digits[i];
			}
			if (isPrime(number)) {
				pandigitalPrimes.add(number);
			}
			return;
		}
		for (int i = curIndex; i <= endIndex; i++) {
			int temp = digits[i];
			for (int j = i; j > curIndex; j--) {
				digits[j] = digits[j - 1];
			}
			digits[curIndex] = temp;
			search(curIndex + 1, endIndex);
			temp = digits[curIndex];
			for (int j = curIndex; j < i; j++) {
				digits[j] = digits[j + 1];
			}
			digits[i] = temp;
		}
	}

	static boolean isPrime(int number) {
		if (number < 2) {
			return false;
		}
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	static int solve(int N) {
		int index = Collections.binarySearch(pandigitalPrimes, N);
		if (index < 0) {
			index = -1 - index - 1;
		}
		return (index < 0) ? -1 : pandigitalPrimes.get(index);
	}
}
