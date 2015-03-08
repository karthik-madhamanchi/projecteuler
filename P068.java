import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P068 {
	static int[] inners;
	static int[] outers;
	static boolean[] used;
	static int[] numbers;
	static int innerSum;
	static int innerRunningSum;

	public static void main(String[] args) {
		int sampleN = 5;
		List<String> sampleSolutions = new ArrayList<String>();
		for (int sampleS = 1 + 2 + sampleN * 2; sampleS <= 1
				+ (sampleN * 2 - 1) + sampleN * 2; sampleS++) {
			sampleSolutions.addAll(solveWhenMaxAtOuter(sampleN, sampleS));
		}
		System.err.println(sampleSolutions.stream()
				.max((s1, s2) -> s1.compareTo(s2)).get());

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int S = in.nextInt();
		solve(N, S).stream().sorted().forEach(System.out::println);
		in.close();
	}

	static List<String> solve(int N, int S) {
		List<String> solutions = new ArrayList<String>();
		solutions.addAll(solveWhenMaxAtInner(N, S));
		solutions.addAll(solveWhenMaxAtOuter(N, S));
		return solutions;
	}

	static List<String> solveWhenMaxAtInner(int N, int S) {
		List<String> solutions = new ArrayList<String>();
		initVariables(N, S);
		fill(inners, 0, N * 2);

		for (int index = 1; index < numbers.length; index++) {
			if (inners[0] + numbers[index] >= S
					|| innerRunningSum + numbers[index] >= innerSum) {
				continue;
			}

			swap(numbers, 1, index);
			fill(inners, 1, numbers[1]);

			int outer0 = S - inners[0] - inners[1];
			if (outer0 < used.length && !used[outer0]) {
				int outer0Index = find(numbers, 2, outer0);
				swap(numbers, 2, outer0Index);
				fill(outers, 0, outer0);

				search(solutions, N, S, 1);

				unfill(outers, 0);
				swap(numbers, 2, outer0Index);
			}

			unfill(inners, 1);
			swap(numbers, 1, index);
		}

		return solutions;
	}

	static List<String> solveWhenMaxAtOuter(int N, int S) {
		List<String> solutions = new ArrayList<String>();
		initVariables(N, S);
		fill(outers, 0, N * 2);

		for (int index = 1; index < numbers.length; index++) {
			if (outers[0] + numbers[index] >= S) {
				continue;
			}

			swap(numbers, 1, index);
			fill(inners, 0, numbers[1]);

			int inner1 = S - outers[0] - inners[0];
			if (inner1 < used.length && !used[inner1]) {
				int inner1Index = find(numbers, 2, inner1);
				swap(numbers, 2, inner1Index);
				fill(inners, 1, inner1);

				search(solutions, N, S, 1);

				unfill(inners, 1);
				swap(numbers, 2, inner1Index);
			}

			unfill(inners, 0);
			swap(numbers, 1, index);
		}

		return solutions;
	}

	static void search(List<String> solutions, int N, int S, int line) {
		if (line == N - 2) {
			int innerNMinus1 = innerSum - innerRunningSum;
			if (innerNMinus1 < used.length && !used[innerNMinus1]) {
				int innerNMinus1Index = find(numbers, N * 2 - 3, innerNMinus1);
				swap(numbers, N * 2 - 3, innerNMinus1Index);
				fill(inners, N - 1, innerNMinus1);

				int outerNMinus2 = S - inners[N - 2] - inners[N - 1];
				if (outerNMinus2 >= 1 && outerNMinus2 < used.length
						&& !used[outerNMinus2]) {
					int outerNMinus2Index = find(numbers, N * 2 - 2,
							outerNMinus2);
					swap(numbers, N * 2 - 2, outerNMinus2Index);
					fill(outers, N - 2, outerNMinus2);

					int outerNMinus1 = S - inners[N - 1] - inners[0];
					if (outerNMinus1 == numbers[N * 2 - 1]) {
						fill(outers, N - 1, outerNMinus1);

						solutions.add(describeSolution(N));

						unfill(outers, N - 1);
					}

					unfill(outers, N - 2);
					swap(numbers, N * 2 - 2, outerNMinus2Index);
				}

				unfill(inners, N - 1);
				swap(numbers, N * 2 - 3, innerNMinus1Index);
			}
			return;
		}

		for (int index = line * 2 + 1; index < numbers.length; index++) {
			if (inners[line] + numbers[index] >= S
					|| innerRunningSum + numbers[index] >= innerSum) {
				continue;
			}

			swap(numbers, line * 2 + 1, index);
			fill(inners, line + 1, numbers[line * 2 + 1]);

			int outerLine = S - inners[line] - inners[line + 1];
			if (outerLine < used.length && !used[outerLine]) {
				int outerLineIndex = find(numbers, line * 2 + 2, outerLine);
				swap(numbers, line * 2 + 2, outerLineIndex);
				fill(outers, line, outerLine);

				search(solutions, N, S, line + 1);

				unfill(outers, line);
				swap(numbers, line * 2 + 2, outerLineIndex);
			}

			unfill(inners, line + 1);
			swap(numbers, line * 2 + 1, index);
		}
	}

	static String describeSolution(int N) {
		int startIndex = 0;
		for (int i = 1; i < outers.length; i++) {
			if (outers[i] < outers[startIndex]) {
				startIndex = i;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(outers[(startIndex - i + N) % N]);
			sb.append(inners[(startIndex - i + N + 1) % N]);
			sb.append(inners[(startIndex - i + N) % N]);
		}
		return sb.toString();
	}

	static int computeInnersSum(int N, int S) {
		return N * (S - (N * 2 + 1));
	}

	static void initVariables(int N, int S) {
		inners = new int[N];
		outers = new int[N];
		used = new boolean[N * 2 + 1];

		numbers = new int[N * 2];
		numbers[0] = N * 2;
		for (int i = 1; i < numbers.length; i++) {
			numbers[i] = i;
		}

		innerSum = computeInnersSum(N, S);
		innerRunningSum = 0;
	}

	static void fill(int[] nodes, int index, int number) {
		nodes[index] = number;
		used[number] = true;

		if (nodes == inners) {
			innerRunningSum += number;
		}
	}

	static void unfill(int[] nodes, int index) {
		used[nodes[index]] = false;

		if (nodes == inners) {
			innerRunningSum -= nodes[index];
		}
	}

	static void swap(int[] a, int index1, int index2) {
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}

	static int find(int[] a, int startIndex, int target) {
		for (int i = startIndex;; i++) {
			if (a[i] == target) {
				return i;
			}
		}
	}
}
