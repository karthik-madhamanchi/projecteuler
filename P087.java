import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class P087 {
	static final int LIMIT = 10000000;
	static int[] solutions;

	public static void main(String[] args) throws Throwable {
		System.err.println(solve(49999999));

		PrintWriter out = new PrintWriter(System.out);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(in.readLine());
			out.println(solve(N));
		}
		in.close();
		out.close();
	}

	static int solve(int N) {
		if (solutions == null) {
			buildSolutions();
		}

		if (N < solutions.length) {
			return solutions[N];
		} else {
			return findSums(N).size();
		}
	}

	static void buildSolutions() {
		Set<Integer> sums = findSums(LIMIT);

		solutions = new int[LIMIT + 1];
		for (int i = 1; i < solutions.length; i++) {
			solutions[i] = solutions[i - 1] + (sums.contains(i) ? 1 : 0);
		}
	}

	static Set<Integer> findSums(int limit) {
		Set<Integer> squares = new HashSet<Integer>();
		Set<Integer> cubes = new HashSet<Integer>();
		Set<Integer> fouths = new HashSet<Integer>();
		for (int i = 2; i * i <= limit; i++) {
			if (isPrime(i)) {
				squares.add(i * i);
				if ((long) i * i * i <= limit) {
					cubes.add(i * i * i);
					if ((long) i * i * i * i <= limit) {
						fouths.add(i * i * i * i);
					}
				}
			}
		}

		Set<Integer> sums = add(add(squares, cubes, limit), fouths, limit);
		return sums;
	}

	static boolean isPrime(int number) {
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	static Set<Integer> add(Set<Integer> a, Set<Integer> b, int limit) {
		Set<Integer> c = new HashSet<Integer>();
		for (int oneA : a) {
			for (int oneB : b) {
				if (oneA + oneB <= limit) {
					c.add(oneA + oneB);
				}
			}
		}
		return c;
	}
}
