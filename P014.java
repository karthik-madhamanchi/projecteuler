import java.util.Scanner;

public class P014 {
	static final int LIMIT = 5000000;
	static int[] stepNums = new int[LIMIT + 1];
	static int[] results = new int[stepNums.length];

	public static void main(String args[]) {
		buildStepNums();
		buildResults();

		System.err.println(solve(1000000));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static void buildStepNums() {
		stepNums[1] = 1;
		for (int i = 2; i < stepNums.length; i++) {
			countSteps(i);
		}
	}

	static void buildResults() {
		results[1] = 1;
		for (int i = 2; i < results.length; i++) {
			if (stepNums[i] >= stepNums[results[i - 1]]) {
				results[i] = i;
			} else {
				results[i] = results[i - 1];
			}
		}
	}

	static int solve(int N) {
		return results[N];
	}

	static void countSteps(int number) {
		long n = number;
		int count = 0;
		int result;
		while (true) {
			if (n < stepNums.length && stepNums[(int) n] != 0) {
				result = count + stepNums[(int) n];
				break;
			}
			if (n % 2 == 0) {
				n /= 2;
			} else {
				n = n * 3 + 1;
			}
			count++;
		}
		n = number;
		int step = result;
		while (true) {
			if (n < stepNums.length) {
				if (stepNums[(int) n] != 0) {
					break;
				}
				stepNums[(int) n] = step;
			}
			if (n % 2 == 0) {
				n /= 2;
			} else {
				n = n * 3 + 1;
			}
			step--;
		}
	}
}
