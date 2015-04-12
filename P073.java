import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P073 {
	public static void main(String[] args) {
		System.err.println(solve(2, 12000));

		Scanner in = new Scanner(System.in);
		int A = in.nextInt();
		int D = in.nextInt();
		System.out.println(solve(A, D));
		in.close();
	}

	static long solve(int A, int D) {
		long result = 0;
		for (int d = 2; d <= D; d++) {
			if (d == A || d == A + 1) {
				continue;
			}
			int lowerN = (int) Math.ceil(1.0 / (A + 1) * d);
			int upperN = (int) Math.floor(1.0 / A * d);
			if (lowerN <= upperN) {
				result += findCoprimesBetween(d, lowerN, upperN);
			}
		}
		return result;
	}

	static int findCoprimesBetween(int d, int lowerN, int upperN) {
		int coprimeNum = 0;
		List<Integer> primeFactors = findPrimeFactors(d);
		for (int code = 0; code < (1 << primeFactors.size()); code++) {
			boolean used[] = decode(code, primeFactors.size());

			int sign = 1;
			int divisor = 1;
			for (int i = 0; i < used.length; i++) {
				if (used[i]) {
					sign = -sign;
					divisor *= primeFactors.get(i);
				}
			}

			coprimeNum += sign * findMultiplesBetween(divisor, lowerN, upperN);
		}
		return coprimeNum;
	}

	static List<Integer> findPrimeFactors(int number) {
		List<Integer> primeFactors = new ArrayList<Integer>();
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0 && isPrime(i)) {
				primeFactors.add(i);
				while (number % i == 0) {
					number /= i;
				}
			}
		}
		if (number > 1) {
			primeFactors.add(number);
		}
		return primeFactors;
	}

	static boolean isPrime(int number) {
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	static boolean[] decode(int code, int size) {
		boolean[] used = new boolean[size];
		for (int i = 0; i < used.length; i++) {
			used[i] = ((code & 1) != 0);
			code >>= 1;
		}
		return used;
	}

	static int findMultiplesBetween(int divisor, int lowerN, int upperN) {
		int lowerMultiple = lowerN / divisor * divisor;
		if (lowerMultiple < lowerN) {
			lowerMultiple += divisor;
		}

		int upperMultiple = upperN / divisor * divisor;
		if (upperMultiple > upperN) {
			upperMultiple -= divisor;
		}
		return Math.max(0, upperMultiple / divisor - lowerMultiple / divisor
				+ 1);
	}
}
