import java.util.Scanner;

public class P069 {
	static final int PRIMES[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
			41, 43, 47 };

	public static void main(String[] args) {
		System.err.println(solve(1000001));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			long N = in.nextLong();
			System.out.println(solve(N));
		}
		in.close();
	}

	static long solve(long N) {
		long product = 1;
		for (int prime : PRIMES) {
			product *= prime;
			if (product >= N) {
				product /= prime;
				break;
			}
		}
		return product;
	}
}
