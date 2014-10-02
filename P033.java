import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class P033 {
	static int[] power10;

	public static void main(String[] args) {
		Set<Fraction> sampleResult = solve(2, 1);
		int numeratorProduct = 1;
		int denominatorProduct = 1;
		for (Fraction fraction : sampleResult) {
			numeratorProduct *= fraction.numerator;
			denominatorProduct *= fraction.denominator;
		}
		System.err.println(denominatorProduct
				/ gcd(numeratorProduct, denominatorProduct));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		Set<Fraction> result = solve(N, K);
		int numeratorSum = 0;
		int denominatorSum = 0;
		for (Fraction fraction : result) {
			numeratorSum += fraction.numerator;
			denominatorSum += fraction.denominator;
		}
		System.out.println(numeratorSum + " " + denominatorSum);
		in.close();
	}

	static int gcd(int a, int b) {
		while (b != 0) {
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	static Set<Fraction> solve(int N, int K) {
		buildPower10(N);

		Set<Fraction> result = new HashSet<Fraction>();
		int cancelledLength = N - K;
		String format = "%0" + cancelledLength + "d";
		for (int cancelledNumerator = 1; cancelledNumerator < power10[cancelledLength]; cancelledNumerator++) {
			for (int cancelledDenominator = cancelledNumerator + 1; cancelledDenominator < power10[cancelledLength]; cancelledDenominator++) {
				if (N == 4 && K == 1) {
					for (int digit = 1; digit <= 9; digit++) {
						for (int numeratorInsertIndex = 0; numeratorInsertIndex < (cancelledNumerator < power10[N - 2] ? 1
								: N); numeratorInsertIndex++) {
							int numerator = cancelledNumerator
									/ power10[N - 1 - numeratorInsertIndex]
									* power10[N - numeratorInsertIndex] + digit
									* power10[N - 1 - numeratorInsertIndex]
									+ cancelledNumerator
									% power10[N - 1 - numeratorInsertIndex];
							for (int denominatorInsertIndex = 0; denominatorInsertIndex < (cancelledDenominator < power10[N - 2] ? 1
									: N); denominatorInsertIndex++) {
								int denominator = cancelledDenominator
										/ power10[N - 1
												- denominatorInsertIndex]
										* power10[N - denominatorInsertIndex]
										+ digit
										* power10[N - 1
												- denominatorInsertIndex]
										+ cancelledDenominator
										% power10[N - 1
												- denominatorInsertIndex];
								if (denominatorInsertIndex > 0
										&& denominator / power10[N - 1] < numerator
												/ power10[N - 1]) {
									break;
								}
								if (numerator * cancelledDenominator == denominator
										* cancelledNumerator) {
									result.add(new Fraction(numerator,
											denominator));
								}
							}
						}
					}
				} else {
					String cancelledNumeratorStr = String.format(format,
							cancelledNumerator);
					String cancelledDenominatorStr = String.format(format,
							cancelledDenominator);
					int common = gcd(cancelledNumerator, cancelledDenominator);
					int deltaNumerator = cancelledNumerator / common;
					int deltaDenominator = cancelledDenominator / common;
					for (int numerator = power10[N - 1] / deltaNumerator
							* deltaNumerator, denominator = numerator
							* deltaDenominator / deltaNumerator; denominator < power10[N]; numerator += deltaNumerator, denominator += deltaDenominator) {
						if (numerator < power10[N - 1]) {
							continue;
						}
						if (isCancelledFrom(cancelledNumeratorStr,
								cancelledDenominatorStr, numerator + "",
								denominator + "")) {
							result.add(new Fraction(numerator, denominator));
						}
					}
				}
			}
		}
		return result;
	}

	static void buildPower10(int N) {
		power10 = new int[N + 1];
		for (int i = 0, p = 1; i < power10.length; i++, p *= 10) {
			power10[i] = p;
		}
	}

	static boolean isCancelledFrom(String cancelledA, String cancelledB,
			String a, String b) {
		String cancelStrA = findCancelStr(cancelledA, a);
		String cancelStrB = findCancelStr(cancelledB, b);
		return cancelStrA != null && cancelStrA.indexOf('0') < 0
				&& cancelStrA.equals(cancelStrB);
	}

	static String findCancelStr(String cancelled, String origin) {
		String cancelStr = "";
		int fromIndex = 0;
		for (int i = 0; i < cancelled.length(); i++) {
			char ch = cancelled.charAt(i);
			int index = origin.indexOf(ch, fromIndex);
			if (index < 0) {
				return null;
			}
			cancelStr += origin.substring(fromIndex, index);
			fromIndex = index + 1;
		}
		cancelStr += origin.substring(fromIndex);
		return cancelStr
				.chars()
				.sorted()
				.mapToObj(ch -> (char) ch)
				.collect(StringBuffer::new, StringBuffer::append,
						StringBuffer::append).toString();
	}
}

class Fraction {
	int numerator;
	int denominator;

	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	@Override
	public int hashCode() {
		return numerator * denominator;
	}

	@Override
	public boolean equals(Object obj) {
		Fraction other = (Fraction) obj;
		return numerator == other.numerator && denominator == other.denominator;
	}
}