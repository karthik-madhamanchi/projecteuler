import java.util.Scanner;
import java.util.stream.LongStream;

public class P048 {
	static final long MODULO = 10000000000L;

	public static void main(String[] args) {
		System.err.println(solve(1000));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		System.out.println(solve(N));
		in.close();
	}

	static long solve(int N) {
		return LongStream.rangeClosed(1, N).map(P048::power)
				.reduce(0, P048::addMod);
	}

	static long power(long n) {
		long result = 1;
		long base = n;
		while (n != 0) {
			if (n % 2 != 0) {
				result = multiplyMod(result, base);
			}
			base = multiplyMod(base, base);
			n /= 2;
		}
		return result;
	}

	static long multiplyMod(long a, long b) {
		long result = 0;
		while (b != 0) {
			result = addMod(result, a * (b % 10) % MODULO);
			b /= 10;
			a = a * 10 % MODULO;
		}
		return result;
	}

	static long addMod(long a, long b) {
		return (a + b) % MODULO;
	}
}
