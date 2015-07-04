import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class P093 {
	public static void main(String[] args) {
		int[] sampleDigits = new int[4];
		int maxN = -1;
		String sampleResult = null;
		for (sampleDigits[0] = 0; sampleDigits[0] <= 9; sampleDigits[0]++) {
			for (sampleDigits[1] = sampleDigits[0] + 1; sampleDigits[1] <= 9; sampleDigits[1]++) {
				for (sampleDigits[2] = sampleDigits[1] + 1; sampleDigits[2] <= 9; sampleDigits[2]++) {
					for (sampleDigits[3] = sampleDigits[2] + 1; sampleDigits[3] <= 9; sampleDigits[3]++) {
						int n = solve(sampleDigits);
						if (n > maxN) {
							maxN = n;
							sampleResult = "" + sampleDigits[0]
									+ sampleDigits[1] + sampleDigits[2]
									+ sampleDigits[3];
						}
					}
				}
			}
		}
		System.err.println(sampleResult);

		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int[] digits = new int[m];
		for (int i = 0; i < digits.length; i++) {
			digits[i] = in.nextInt();
		}
		System.out.println(solve(digits));
		in.close();
	}

	static int solve(int[] digits) {
		Set<Integer> results = new HashSet<Integer>();

		Rational[] numbers = new Rational[digits.length];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = new Rational(digits[i], 1);
		}
		search(results, numbers);

		for (int n = 1;; n++) {
			if (!results.contains(n)) {
				return n - 1;
			}
		}
	}

	static void search(Set<Integer> results, Rational[] numbers) {
		if (numbers.length == 1) {
			if (numbers[0].isInteger()) {
				results.add(numbers[0].toInteger());
			}
			return;
		}

		Rational[] nextNumbers = new Rational[numbers.length - 1];
		for (int i = 0; i < numbers.length; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				copy(nextNumbers, numbers, i, j);

				nextNumbers[nextNumbers.length - 1] = Rational.add(numbers[i],
						numbers[j]);
				search(results, nextNumbers);

				nextNumbers[nextNumbers.length - 1] = Rational.subtract(
						numbers[i], numbers[j]);
				search(results, nextNumbers);

				nextNumbers[nextNumbers.length - 1] = Rational.subtract(
						numbers[j], numbers[i]);
				search(results, nextNumbers);

				nextNumbers[nextNumbers.length - 1] = Rational.multiply(
						numbers[i], numbers[j]);
				search(results, nextNumbers);

				if (numbers[j].numerator != 0) {
					nextNumbers[nextNumbers.length - 1] = Rational.divide(
							numbers[i], numbers[j]);
					search(results, nextNumbers);
				}

				if (numbers[i].numerator != 0) {
					nextNumbers[nextNumbers.length - 1] = Rational.divide(
							numbers[j], numbers[i]);
					search(results, nextNumbers);
				}
			}
		}
	}

	static void copy(Rational[] dest, Rational[] src, int excludedIndex1,
			int excludedIndex2) {
		int index = 0;
		for (int i = 0; i < src.length; i++) {
			if (i == excludedIndex1 || i == excludedIndex2) {
				continue;
			}
			dest[index] = src[i];
			index++;
		}
	}
}

class Rational {
	int numerator;
	int denominator;

	Rational(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	boolean isInteger() {
		return numerator % denominator == 0;
	}

	int toInteger() {
		return numerator / denominator;
	}

	static Rational add(Rational a, Rational b) {
		return new Rational(a.numerator * b.denominator + b.numerator
				* a.denominator, a.denominator * b.denominator);
	}

	static Rational subtract(Rational a, Rational b) {
		return new Rational(a.numerator * b.denominator - b.numerator
				* a.denominator, a.denominator * b.denominator);
	}

	static Rational multiply(Rational a, Rational b) {
		return new Rational(a.numerator * b.numerator, a.denominator
				* b.denominator);
	}

	static Rational divide(Rational a, Rational b) {
		return new Rational(a.numerator * b.denominator, a.denominator
				* b.numerator);
	}
}