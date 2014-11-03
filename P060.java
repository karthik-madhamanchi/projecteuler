import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class P060 {
	static final long[] POWERS10 = { 1, 10, 100, 1000, 10000, 100000 };
	static boolean[] isPrimes;
	@SuppressWarnings("unchecked")
	static List<Integer>[] primesGroups = new List[2];
	static final boolean HACKERRANK = true;
	static final int CERTAINTY = 100;

	public static void main(String[] args) {
		if (!HACKERRANK) {
			int N = 2;
			while (true) {
				List<Integer> sums = solve(N, 5);
				if (sums.isEmpty()) {
					N += 5000;
				} else {
					int firstSum = sums.get(0);
					if (N >= firstSum) {
						System.err.println(firstSum);
						break;
					}
					N = firstSum;
				}
			}
		} else {
			Scanner in = new Scanner(System.in);
			int N = in.nextInt();
			int K = in.nextInt();
			List<Integer> sums = solve(N, K);
			StringBuilder sb = new StringBuilder();
			for (int sum : sums) {
				sb.append(sum);
				sb.append("\n");
			}
			System.out.print(sb);
			in.close();
		}
	}

	static List<Integer> solve(int N, int K) {
		List<Integer> sums = new ArrayList<Integer>();

		buildPrimes(N);

		for (int g = 0; g < 2; g++) {
			@SuppressWarnings("unchecked")
			TreeSet<Integer>[] adjacentsList = new TreeSet[primesGroups[g]
					.size()];
			for (int i = 0; i < primesGroups[g].size(); i++) {
				adjacentsList[i] = new TreeSet<Integer>();
				int prime1 = primesGroups[g].get(i);
				for (int j = i + 1; j < primesGroups[g].size(); j++) {
					int prime2 = primesGroups[g].get(j);
					if (checkPrime(concat(prime1, prime2))
							&& checkPrime(concat(prime2, prime1))) {
						adjacentsList[i].add(j);
					}
				}
			}
			search(sums, primesGroups[g], adjacentsList, K,
					new ArrayList<Integer>());
		}

		Collections.sort(sums);
		return sums;
	}

	static void search(List<Integer> sums, List<Integer> primesGroup,
			TreeSet<Integer>[] adjacentsList, int K, List<Integer> current) {
		if (current.size() == K) {
			sums.add(current.stream().mapToInt(primesGroup::get).sum());
			return;
		}
		if (current.isEmpty()) {
			for (int i = 0; i < primesGroup.size(); i++) {
				if (adjacentsList[i].size() + 1 >= K) {
					current.add(i);
					search(sums, primesGroup, adjacentsList, K, current);
					current.remove(current.size() - 1);
				}
			}
			return;
		}
		int last = current.get(current.size() - 1);
		if (adjacentsList[last].size() + 1 + current.size() >= K) {
			for (int next : adjacentsList[last]) {
				boolean found = true;
				for (int elem : current) {
					if (!adjacentsList[elem].contains(next)) {
						found = false;
						break;
					}
				}
				if (found) {
					current.add(next);
					search(sums, primesGroup, adjacentsList, K, current);
					current.remove(current.size() - 1);
				}
			}
		}
	}

	static int computeDigitSumBy3(int number) {
		int digitSum = 0;
		while (number != 0) {
			digitSum += number % 10;
			number /= 10;
		}
		return digitSum % 3;
	}

	static void buildPrimes(int N) {
		isPrimes = new boolean[N];
		Arrays.fill(isPrimes, true);
		isPrimes[0] = false;
		isPrimes[1] = false;
		for (int i = 2; i * i < isPrimes.length; i++) {
			if (isPrimes[i]) {
				for (int j = i * i; j < isPrimes.length; j += i) {
					isPrimes[j] = false;
				}
			}
		}

		for (int i = 0; i < primesGroups.length; i++) {
			primesGroups[i] = new ArrayList<Integer>();
			primesGroups[i].add(3);
		}
		for (int i = 4; i < isPrimes.length; i++) {
			if (isPrimes[i]) {
				primesGroups[computeDigitSumBy3(i) - 1].add(i);
			}
		}
	}

	static long concat(int number1, int number2) {
		for (int i = 0;; i++) {
			if (POWERS10[i] > number2) {
				return number1 * POWERS10[i] + number2;
			}
		}
	}

	static boolean checkPrime(long number) {
		if (number < isPrimes.length) {
			return isPrimes[(int) number];
		}
		if (number <= Integer.MAX_VALUE) {
			return MillerRabin32.miller_rabin_32((int) number);
		} else {
			return new BigInteger(number + "").isProbablePrime(CERTAINTY);
		}
	}
}

// http://en.literateprograms.org/Miller-Rabin_primality_test_%28Java%29
class MillerRabin32 {
	private static int modular_exponent_32(int base, int power, int modulus) {
		long result = 1;
		for (int i = 31; i >= 0; i--) {
			result = (result * result) % modulus;
			if ((power & (1 << i)) != 0) {
				result = (result * base) % modulus;
			}
		}
		return (int) result; // Will not truncate since modulus is an int
	}

	private static boolean miller_rabin_pass_32(int a, int n) {
		int d = n - 1;
		int s = Integer.numberOfTrailingZeros(d);
		d >>= s;
		int a_to_power = modular_exponent_32(a, d, n);
		if (a_to_power == 1)
			return true;
		for (int i = 0; i < s - 1; i++) {
			if (a_to_power == n - 1)
				return true;
			a_to_power = modular_exponent_32(a_to_power, 2, n);
		}
		if (a_to_power == n - 1)
			return true;
		return false;
	}

	public static boolean miller_rabin_32(int n) {
		if (n <= 1)
			return false;
		else if (n == 2)
			return true;
		else if (miller_rabin_pass_32(2, n)
				&& (n <= 7 || miller_rabin_pass_32(7, n))
				&& (n <= 61 || miller_rabin_pass_32(61, n)))
			return true;
		else
			return false;
	}
}