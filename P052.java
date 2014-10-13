import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class P052 {
	public static void main(String[] args) {
		System.err.println(solve(Integer.MAX_VALUE, 6, 1).get(0).get(0));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		solve(N, K, Integer.MAX_VALUE).forEach(
				solution -> System.out.println(solution.stream()
						.map(number -> number + "")
						.collect(Collectors.joining(" "))));
		in.close();
	}

	static List<List<Integer>> solve(int N, int K, int solutionLimit) {
		List<List<Integer>> solutions = new ArrayList<List<Integer>>();
		for (int i = 1; i <= N && solutions.size() < solutionLimit; i++) {
			if ((i + "").length() != (i * K + "").length()) {
				continue;
			}
			List<Integer> solution = buildSequence(i, K);
			if (isValidSolution(solution)) {
				solutions.add(solution);
			}
		}
		return solutions;
	}

	static List<Integer> buildSequence(int base, int K) {
		return IntStream.rangeClosed(1, K).map(i -> i * base).boxed()
				.collect(Collectors.toList());
	}

	static boolean isValidSolution(List<Integer> solution) {
		return solution.stream().map(P052::generateKey)
				.collect(Collectors.toSet()).size() == 1;
	}

	static String generateKey(int number) {
		int counts[] = new int[10];
		while (number != 0) {
			counts[number % 10]++;
			number /= 10;
		}

		StringBuilder sb = new StringBuilder();
		for (int count : counts) {
			sb.append(count);
		}
		return sb.toString();
	}
}
