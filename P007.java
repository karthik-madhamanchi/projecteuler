import java.util.Scanner;

public class P007 {
	static int[] primes;
	static final int LIMIT = 10001;

	public static void main(String[] args) {
		System.err.println(solve(10001));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static int solve(int N) {
		if (primes == null) {
			primes = new int[LIMIT];
			primes[0] = 2;
			int number = 1;
			for (int i = 1; i < primes.length; i++) {
				boolean isPrime;
				do {
					number += 2;
					isPrime = true;
					for (int j = 0; primes[j] * primes[j] <= number; j++) {
						if (number % primes[j] == 0) {
							isPrime = false;
							break;
						}
					}
				} while (!isPrime);
				primes[i] = number;
			}
		}
		return primes[N - 1];
	}
}
