import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P074 {
	static final int FACTORIALS[] = new int[] { 1, 1, 2, 6, 24, 120, 720, 5040,
			40320, 362880 };
	static final int LIMIT_N = 1000000;
	static final int LIMIT_L = 60;
	static List<Integer>[] solutions;

	public static void main(String[] args) {
		System.err.println(solve(999999, 60).size());

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			int L = in.nextInt();
			List<Integer> solutions = solve(N, L);
			if (solutions.isEmpty()) {
				System.out.println(-1);
			} else {
				System.out.println(String.join(
						" ",
						solutions.stream().map(Object::toString)
								.collect(Collectors.toList())));
			}
		}
		in.close();
	}

	static List<Integer> solve(int N, int L) {
		if (solutions == null) {
			buildSolutions();
		}

		return solutions[L].stream().filter(solution -> solution <= N)
				.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	static void buildSolutions() {
		solutions = new List[LIMIT_N];
		for (int i = 0; i < solutions.length; i++) {
			solutions[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i <= LIMIT_N; i++) {
			List<Integer> chain = new ArrayList<Integer>();
			chain.add(i);
			int number = i;
			while (true) {
				number = computeNext(number);
				if (chain.indexOf(number) >= 0) {
					solutions[chain.size()].add(i);
					break;
				}
				chain.add(number);
			}
		}
	}

	static int computeNext(int number) {
		int sum = 0;
		do {
			sum += FACTORIALS[number % 10];
			number /= 10;
		} while (number != 0);
		return sum;
	}
}
