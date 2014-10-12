import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P050 {
	static final long LIMIT = 1000000000000L;
	static List<Integer> primes = new ArrayList<Integer>();
	static List<Long> accumulatives = new ArrayList<Long>();

	public static void main(String[] args) {
		buildPrimes();

		System.err.println(solve(1000000).sum);

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			long N = in.nextLong();
			Result result = solve(N);
			System.out.println(result.sum + " " + result.length);
		}
		in.close();
	}

	static void buildPrimes() {
		primes.add(2);
		long sum = 2;
		accumulatives.add(sum);
		for (int i = 3; sum + i <= LIMIT; i += 2) {
			boolean primeFlag = true;
			for (int j = 0; j < primes.size(); j++) {
				int p = primes.get(j);
				if (p * p > i) {
					break;
				}
				if (i % p == 0) {
					primeFlag = false;
					break;
				}
			}
			if (primeFlag) {
				primes.add(i);
				sum += i;
				accumulatives.add(sum);
			}
		}
	}

	static Result solve(long N) {
		int maxLength = 0;
		while (maxLength < primes.size()) {
			long sum = accumulatives.get(maxLength);
			if (sum > N) {
				break;
			}
			maxLength++;
		}

		for (int length = maxLength;; length--) {
			for (int beginIndex = 0; beginIndex + length - 1 < primes.size(); beginIndex++) {
				long sum = accumulatives.get(beginIndex + length - 1)
						- (beginIndex == 0 ? 0 : accumulatives
								.get(beginIndex - 1));
				if (sum > N) {
					break;
				}
				if (isPrime(sum)) {
					return new Result(sum, length);
				}
			}
		}
	}

	static boolean isPrime(long number) {
		for (long i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}

class Result {
	long sum;
	int length;

	public Result(long sum, int length) {
		this.sum = sum;
		this.length = length;
	}
}