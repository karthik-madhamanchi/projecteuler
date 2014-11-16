import java.math.BigInteger;
import java.util.Scanner;

public class P065 {
	public static void main(String[] args) {
		System.err.println(solve(100));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		System.out.println(solve(N));
		in.close();
	}

	static int solve(int N) {
		int[] constants = new int[N];
		constants[0] = 2;
		int middle = 2;
		for (int i = 1; i < constants.length; i++) {
			if (i % 3 == 2) {
				constants[i] = middle;
				middle += 2;
			} else {
				constants[i] = 1;
			}
		}

		Fraction fn = new Fraction(new BigInteger(
				constants[constants.length - 1] + ""), BigInteger.ONE);
		for (int i = constants.length - 2; i >= 0; i--) {
			fn = Fraction.add(Fraction.inverse(fn), new Fraction(
					new BigInteger(constants[i] + ""), BigInteger.ONE));
		}

		fn.reduce();
		return fn.numerator.toString().chars().map(ch -> ch - '0').sum();
	}
}

class Fraction {
	BigInteger numerator;
	BigInteger denominator;

	Fraction(BigInteger numerator, BigInteger denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	void reduce() {
		BigInteger common = numerator.gcd(denominator);
		numerator = numerator.divide(common);
		denominator = denominator.divide(common);
	}

	static Fraction inverse(Fraction f) {
		return new Fraction(f.denominator, f.numerator);
	}

	static Fraction add(Fraction a, Fraction b) {
		BigInteger n = a.numerator.multiply(b.denominator).add(
				a.denominator.multiply(b.numerator));
		BigInteger d = a.denominator.multiply(b.denominator);
		return new Fraction(n, d);
	}
}