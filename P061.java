import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class P061 {
	public static void main(String[] args) {
		System.err.println(solve(new int[] { 3, 4, 5, 6, 7, 8 }).iterator()
				.next().stream().mapToInt(x -> x).sum());

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int[] types = new int[T];
		for (int i = 0; i < types.length; i++) {
			types[i] = in.nextInt();
		}
		solve(types).stream()
				.mapToInt(solution -> solution.stream().mapToInt(x -> x).sum())
				.sorted().forEach(System.out::println);
		in.close();
	}

	static Set<Set<Integer>> solve(int[] types) {
		Set<Set<Integer>> solutions = new HashSet<Set<Integer>>();
		search(solutions, types, 0, new int[types.length * 2]);
		return solutions;
	}

	static void search(Set<Set<Integer>> solutions, int[] types, int index,
			int[] digits) {
		if (index == types.length - 1) {
			int number = generateNumber(digits[index * 2],
					digits[index * 2 + 1], digits[0], digits[1]);
			if (isType(types[index], number)) {
				Set<Integer> solution = new HashSet<Integer>();
				for (int i = 0; i < types.length; i++) {
					solution.add(generateNumber(digits[i * 2],
							digits[i * 2 + 1], digits[(i * 2 + 2)
									% digits.length], digits[(i * 2 + 3)
									% digits.length]));
				}
				if (solution.size() == types.length) {
					solutions.add(solution);
				}
			}
			return;
		}

		int beginP = index == 0 ? 1000 : generateNumber(digits[index * 2],
				digits[index * 2 + 1], 0, 0);
		int endP = index == 0 ? 9999 : generateNumber(digits[index * 2],
				digits[index * 2 + 1], 9, 9);
		for (int i = index; i < (index == 0 ? (index + 1) : types.length); i++) {
			swap(types, index, i);
			int p;
			for (int n = computeN(types[index], beginP); (p = f(types[index], n)) <= endP; n++) {
				if (p < beginP) {
					continue;
				}
				if (p % 100 / 10 != 0) {
					if (index == 0) {
						digits[index * 2] = p / 1000;
						digits[index * 2 + 1] = p % 1000 / 100;
					}
					digits[index * 2 + 2] = p % 100 / 10;
					digits[index * 2 + 3] = p % 10;
					search(solutions, types, index + 1, digits);
				}
			}
			swap(types, index, i);
		}
	}

	static void swap(int a[], int index1, int index2) {
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}

	static int generateNumber(int a, int b, int c, int d) {
		return a * 1000 + b * 100 + c * 10 + d;
	}

	static boolean isType(int type, int p) {
		int n = computeN(type, p);
		return f(type, n) == p;
	}

	static int computeN(int type, int p) {
		double n = 0;
		if (type == 3) {
			n = (Math.sqrt(8 * p + 1) - 1) / 2;
		} else if (type == 4) {
			n = Math.sqrt(p);
		} else if (type == 5) {
			n = (Math.sqrt(24 * p + 1) + 1) / 6;
		} else if (type == 6) {
			n = (Math.sqrt(8 * p + 1) + 1) / 4;
		} else if (type == 7) {
			n = (Math.sqrt(40 * p + 9) + 3) / 10;
		} else if (type == 8) {
			n = (Math.sqrt(3 * p + 1) + 1) / 3;
		}
		return (int) Math.round(n);
	}

	static int f(int type, int n) {
		int p = 0;
		if (type == 3) {
			p = n * (n + 1) / 2;
		} else if (type == 4) {
			p = n * n;
		} else if (type == 5) {
			p = n * (3 * n - 1) / 2;
		} else if (type == 6) {
			p = n * (2 * n - 1);
		} else if (type == 7) {
			p = n * (5 * n - 3) / 2;
		} else if (type == 8) {
			p = n * (3 * n - 2);
		}
		return p;
	}
}
