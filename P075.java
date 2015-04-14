import java.util.Scanner;

public class P075 {
	static final int LIMIT = 5000000;

	static int[] solutions;

	public static void main(String[] args) {
		System.err.println(solve(1500000));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static int solve(int N) {
		if (solutions == null) {
			buildSolutions();
		}

		return solutions[N];
	}

	static void buildSolutions() {
		solutions = new int[LIMIT + 1];
		boolean[] validLengths = buildValidLengths();
		for (int i = 1; i < solutions.length; i++) {
			solutions[i] = solutions[i - 1] + (validLengths[i] ? 1 : 0);
		}
	}

	static boolean[] buildValidLengths() {
		boolean[] multipleSolutions = new boolean[LIMIT + 1];
		Integer[] lengthSolutions = new Integer[LIMIT + 1];
		int limitI = (int) Math.floor(Math.sqrt(LIMIT / 4));
		for (int i = 1; i <= limitI; i++) {
			int ii = i * i;
			int limitJ = (int) Math.floor(Math.sqrt(LIMIT / 2 - ii));
			for (int j = i + 1; j <= limitJ; j++) {
				int a = j * j - ii;
				int b = 2 * i * j;
				int c = j * j + ii;
				int baseMinSide = Math.min(a, b);
				int baseLength = a + b + c;
				for (int length = baseLength, minSide = baseMinSide; length <= LIMIT; length += baseLength, minSide += baseMinSide) {
					if (multipleSolutions[length]) {
						continue;
					}

					if (lengthSolutions[length] == null) {
						lengthSolutions[length] = minSide;
					} else if (lengthSolutions[length] != minSide) {
						multipleSolutions[length] = true;
					}
				}
			}
		}

		boolean[] validLengths = new boolean[LIMIT + 1];
		for (int i = 0; i < validLengths.length; i++) {
			validLengths[i] = !multipleSolutions[i]
					&& lengthSolutions[i] != null;
		}
		return validLengths;
	}
}
