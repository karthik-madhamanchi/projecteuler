// https://projecteuler.net/overview=071

import java.util.Scanner;

public class P071 {
	public static void main(String[] args) {
		System.err.println(solve(3, 7, 1000000).numerator);

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int a = in.nextInt();
			int b = in.nextInt();
			long N = in.nextLong();
			Fraction result = solve(a, b, N);
			System.out.println(result.numerator + " " + result.denominator);
		}
		in.close();
	}

	static Fraction solve(int a, int b, long N) {
		ExtendEuclideanResult eer = solveExtendedEuclidean(a, b);
		long numerator = eer.y;
		long denominator = eer.x;
		long t = N / b - denominator / b;
		numerator += a * t;
		denominator += b * t;
		if (denominator > N) {
			numerator -= a;
			denominator -= b;
		} else if (denominator <= N - b) {
			numerator += a;
			denominator += b;
		}
		return new Fraction(numerator, denominator);
	}

	// a * x - b * y = gcd(a, b)
	static ExtendEuclideanResult solveExtendedEuclidean(int a, int b) {
		if (b == 0) {
			return new ExtendEuclideanResult(a, 1, 0);
		}
		ExtendEuclideanResult eer = solveExtendedEuclidean(b, a % b);
		return new ExtendEuclideanResult(eer.r, -eer.y, -eer.x - a / b * eer.y);
	}
}

class ExtendEuclideanResult {
	int r;
	int x;
	int y;

	ExtendEuclideanResult(int r, int x, int y) {
		this.r = r;
		this.x = x;
		this.y = y;
	}
}

class Fraction {
	long numerator;
	long denominator;

	Fraction(long numerator, long denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
}