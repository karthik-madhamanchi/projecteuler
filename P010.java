import java.util.ArrayList;
import java.util.Scanner;

public class P010 {
	static ArrayList<Integer> primes;
	static final int LIMIT = 2000000;
	static long[] results;

	public static void main(String args[]) {
		System.err.println(solve(2000000));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static long solve(int N) {
		if (primes == null) {
			buildPrimes();
			buildResults();
		}
		return results[N];
	}

	static void buildPrimes() {
		primes = new ArrayList<Integer>();
		primes.add(2);
		for (int i = 3; i <= LIMIT; i += 2) {
			boolean isPrime = true;
			for (int j = 0; j < primes.size(); j++) {
				int p = primes.get(j);
				if (p * p > i) {
					break;
				}
				if (i % p == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				primes.add(i);
			}
		}
	}

	static void buildResults() {
		results = new long[LIMIT + 1];
		long sum = 0;
		int indexP = 0;
		for (int i = 0; i < results.length; i++) {
			if (indexP < primes.size() && primes.get(indexP) == i) {
				sum += i;
				indexP++;
			}
			results[i] = sum;
		}
	}
}
