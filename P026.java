import java.util.Scanner;

public class P026 {
	static final int LIMIT = 10000;
	static int[] results = new int[LIMIT + 1];

	public static void main(String args[]) {
		buildResults();

		System.err.println(solve(1000));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static int solve(int N) {
		return results[N];
	}

	static void buildResults() {
		int longest = 0;
		int result = 0;
		for (int i = 1; i < results.length - 1; i++) {
			int cycle = getRecurCycle(i);
			if (cycle > longest) {
				longest = cycle;
				result = i;
			}
			results[i + 1] = result;
		}
	}

	static int getRecurCycle(int number) {
		int visited[] = new int[number];
		int current = 1;
		for (int step = 1;; step++) {
			current = current * 10 % number;
			if (current == 0) {
				return 0;
			}
			if (visited[current] > 0) {
				return step - visited[current];
			}
			visited[current] = step;
		}
	}
}
