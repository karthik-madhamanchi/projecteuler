import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P051 {
	static final int LIMIT = 10000000;
	static boolean[] primes = new boolean[LIMIT];

	public static void main(String[] args) {
		buildPrimes();

		boolean found = false;
		for (int n = 2; !found; n++) {
			for (int k = 1; k <= n; k++) {
				List<Integer> solution = solve(n, k, 8);
				if (solution != null) {
					System.err.println(solution.get(0));
					found = true;
				}
			}
		}

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		int L = in.nextInt();
		System.out.println(solve(N, K, L).stream().map(number -> number + "")
				.collect(Collectors.joining(" ")));
		in.close();
	}

	static void buildPrimes() {
		Arrays.fill(primes, true);
		primes[0] = false;
		primes[1] = false;
		for (int i = 2; i * i < primes.length; i++) {
			if (primes[i]) {
				for (int j = i * i; j < primes.length; j += i) {
					primes[j] = false;
				}
			}
		}
	}

	static List<Integer> solve(int N, int K, int L) {
		List<List<Integer>> arrangements = new ArrayList<List<Integer>>();
		searchArrangements(arrangements, new ArrayList<Integer>(), N, K);

		List<Integer> minSolution = null;
		int minNumber = pow(10, N - 1);
		int upper = pow(10, N - K);
		for (int i = 0; i < upper; i++) {
			for (List<Integer> arrangement : arrangements) {
				List<Integer> solution = new ArrayList<Integer>();
				for (int digit = 0; digit <= 9; digit++) {
					int replaced = replace(i, arrangement, digit, N);
					if (replaced >= minNumber && primes[replaced]) {
						solution.add(replaced);
					}
				}
				if (solution.size() >= L) {
					solution = solution.subList(0, L);
					if (minSolution == null
							|| compareSolution(solution, minSolution) < 0) {
						minSolution = solution;
					}
				}
			}
		}
		return minSolution;
	}

	static int replace(int number, List<Integer> arrangement, int digit, int N) {
		int replaced = 0;
		int arrangementIndex = 0;
		for (int i = 0; i < N; i++) {
			if (arrangementIndex < arrangement.size()
					&& arrangement.get(arrangementIndex) == i) {
				replaced = replaced * 10 + digit;
				arrangementIndex++;
			} else {
				replaced = replaced * 10 + number % 10;
				number /= 10;
			}
		}
		return replaced;
	}

	static void searchArrangements(List<List<Integer>> arrangements,
			List<Integer> current, int N, int K) {
		if (current.size() == K) {
			arrangements.add(new ArrayList<Integer>(current));
			return;
		}
		for (int i = (current.isEmpty() ? 0
				: (current.get(current.size() - 1) + 1)); i < N; i++) {
			current.add(i);
			searchArrangements(arrangements, current, N, K);
			current.remove(current.size() - 1);
		}
	}

	static int compareSolution(List<Integer> solution1, List<Integer> solution2) {
		for (int i = 0; i < solution1.size(); i++) {
			int diff = solution1.get(i) - solution2.get(i);
			if (diff != 0) {
				return diff;
			}
		}
		return 0;
	}

	static int pow(int base, int exponent) {
		int result = 1;
		for (int i = 0; i < exponent; i++) {
			result *= base;
		}
		return result;
	}
}