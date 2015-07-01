import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class P090 {
	public static void main(String[] args) {
		System.err.println(solve(9, 2));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		System.out.println(solve(N, M));
		in.close();
	}

	static int solve(int N, int M) {
		List<Set<Integer>> arrangements = new ArrayList<Set<Integer>>();
		for (int i = 0; i <= 9; i++) {
			for (int j = i + 1; j <= 9; j++) {
				for (int k = j + 1; k <= 9; k++) {
					for (int p = k + 1; p <= 9; p++) {
						for (int q = p + 1; q <= 9; q++) {
							for (int r = q + 1; r <= 9; r++) {
								Set<Integer> arrangement = new HashSet<Integer>(
										Arrays.asList(i, j, k, p, q, r));
								if (arrangement.contains(6)) {
									arrangement.add(9);
								}
								if (arrangement.contains(9)) {
									arrangement.add(6);
								}

								arrangements.add(arrangement);
							}
						}
					}
				}
			}
		}

		int[] squares = new int[N];
		for (int i = 0; i < squares.length; i++) {
			squares[i] = (i + 1) * (i + 1);
		}

		return search(arrangements, squares, new int[M], 0);
	}

	static int search(List<Set<Integer>> arrangements, int[] squares,
			int[] arrangementIndices, int index) {
		if (index == arrangementIndices.length) {
			return isValid(arrangements, squares, arrangementIndices) ? 1 : 0;
		}

		int solutionNum = 0;
		for (int i = (index == 0 ? 0 : arrangementIndices[index - 1]); i < arrangements
				.size(); i++) {
			arrangementIndices[index] = i;
			solutionNum += search(arrangements, squares, arrangementIndices,
					index + 1);
		}
		return solutionNum;
	}

	static boolean isValid(List<Set<Integer>> arrangements, int[] squares,
			int[] arrangementIndices) {
		for (int square : squares) {
			if (!canRepresent(arrangements, arrangementIndices, square, 0)) {
				return false;
			}
		}
		return true;
	}

	static boolean canRepresent(List<Set<Integer>> arrangements,
			int[] arrangementIndices, int number, int index) {
		if (index == arrangementIndices.length) {
			return true;
		}

		boolean result = false;
		for (int i = index; i < arrangementIndices.length; i++) {
			swap(arrangementIndices, index, i);
			if (arrangements.get(arrangementIndices[index]).contains(
					number % 10)) {
				if (canRepresent(arrangements, arrangementIndices, number / 10,
						index + 1)) {
					result = true;
				}
			}
			swap(arrangementIndices, index, i);
			if (result) {
				break;
			}
		}
		return result;
	}

	static void swap(int[] a, int index1, int index2) {
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}
}
