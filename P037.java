import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P037 {
	static List<Integer> truncatablePrimes;

	public static void main(String args[]) {
		buildTruncatablePrimes();

		System.err.println(solve(Integer.MAX_VALUE));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		System.out.println(solve(N));
		in.close();
	}

	static void buildTruncatablePrimes() {
		truncatablePrimes = new ArrayList<Integer>();
		search(2);
		search(3);
		search(5);
		search(7);
	}

	static void search(int number) {
		for (int i = 1; i <= 9; i += 2) {
			int next = number * 10 + i;
			if (isPrime(next)) {
				if (isValid(next)) {
					truncatablePrimes.add(next);
				}
				search(next);
			}
		}
	}

	static int solve(int N) {
		return truncatablePrimes.stream().filter(i -> i < N).mapToInt(i -> i)
				.sum();
	}

	static boolean isValid(int number) {
		while (number != 0) {
			if (!isPrime(number)) {
				return false;
			}
			number %= (int) Math.pow(10, Math.floor(Math.log10(number)));
		}
		return true;
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
}
