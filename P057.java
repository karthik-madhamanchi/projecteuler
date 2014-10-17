import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P057 {
	public static void main(String[] args) {
		System.err.println(solve(1000).stream().count());

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		solve(N).forEach(iteration -> System.out.println(iteration));
		in.close();
	}

	static List<Integer> solve(int N) {
		List<Integer> iterations = new ArrayList<Integer>();
		LargeInteger numerator = new LargeInteger(1, 0);
		LargeInteger denominator = new LargeInteger(1, 0);
		for (int i = 1; i <= N; i++) {
			LargeInteger nextNumerator = LargeInteger.add(numerator,
					LargeInteger.add(denominator, denominator));
			LargeInteger nextDenominator = LargeInteger.add(numerator,
					denominator);

			numerator = nextNumerator;
			denominator = nextDenominator;

			if (numerator.exponent10 > denominator.exponent10) {
				iterations.add(i);
			}
		}
		return iterations;
	}
}

class LargeInteger {
	double value;
	int exponent10;

	public LargeInteger(double value, int exponent10) {
		this.value = value;
		this.exponent10 = exponent10;
	}

	static LargeInteger add(LargeInteger a, LargeInteger b) {
		int resultExponent10 = Math.min(a.exponent10, b.exponent10);
		double resultValue = a.value * pow10(a.exponent10 - resultExponent10)
				+ b.value * pow10(b.exponent10 - resultExponent10);

		while (resultValue > 10) {
			resultExponent10++;
			resultValue /= 10;
		}
		return new LargeInteger(resultValue, resultExponent10);
	}

	static int pow10(int exp10) {
		int result = 1;
		for (int i = 0; i < exp10; i++) {
			result *= 10;
		}
		return result;
	}
}