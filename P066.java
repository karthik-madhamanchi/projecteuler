// http://en.wikipedia.org/wiki/Pell%27s_equation#Fundamental_solution_via_continued_fractions
// http://en.wikipedia.org/wiki/Generalized_continued_fraction

import java.math.BigInteger;
import java.util.Scanner;

public class P066 {
	public static void main(String[] args) {
		System.err.println(solve(1000));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		System.out.println(solve(N));
		in.close();
	}

	static int solve(int N) {
		BigInteger maxX = BigInteger.ZERO;
		int result = 0;
		for (int D = 1; D <= N; D++) {
			BigInteger x = solvePellEquation(D);
			if (x != null && x.compareTo(maxX) > 0) {
				maxX = x;
				result = D;
			}
		}
		return result;
	}

	static BigInteger solvePellEquation(int D) {
		// F = bn + (x * sqrt(D) + y) / z
		int bn = (int) Math.round(Math.sqrt(D));
		if (bn * bn > D) {
			bn--;
		}
		if (bn * bn == D) {
			return null;
		}
		int x = 1;
		int y = -bn;
		int z = 1;

		BigInteger An_2 = BigInteger.ZERO;
		BigInteger An_1 = BigInteger.ONE;
		BigInteger Bn_2 = BigInteger.ONE;
		BigInteger Bn_1 = BigInteger.ZERO;
		while (true) {
			BigInteger An = An_1.multiply(new BigInteger(bn + "")).add(An_2);
			BigInteger Bn = Bn_1.multiply(new BigInteger(bn + "")).add(Bn_2);
			if (f(An, Bn, D).equals(BigInteger.ONE)) {
				return An;
			}

			An_2 = An_1;
			An_1 = An;
			Bn_2 = Bn_1;
			Bn_1 = Bn;

			int nextX = x * z;
			int nextY = -y * z;
			int nextZ = x * x * D - y * y;
			int common = gcd(gcd(Math.abs(nextX), Math.abs(nextY)),
					Math.abs(nextZ));
			x = nextX / common;
			y = nextY / common;
			z = nextZ / common;
			bn = 0;
			while (x * x * D >= (z - y) * (z - y)) {
				y -= z;
				bn++;
			}
		}
	}

	static int gcd(int a, int b) {
		while (b != 0) {
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	static BigInteger f(BigInteger x, BigInteger y, int D) {
		return x.pow(2).subtract(y.pow(2).multiply(new BigInteger(D + "")));
	}
}
