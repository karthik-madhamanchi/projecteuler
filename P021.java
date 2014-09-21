import java.util.Scanner;

public class P021 {
	static final int LIMIT = 100000;
	static boolean[] amicables = new boolean[LIMIT];

	public static void main(String args[]) {
		buildAmicables();

		System.err.println(solve(10000));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static int solve(int N) {
		int sum = 0;
		for (int i = 1; i < N; i++) {
			if (amicables[i]) {
				sum += i;
			}
		}
		return sum;
	}

	static void buildAmicables() {
		for (int i = 1; i < amicables.length; i++) {
			if (amicables[i]) {
				continue;
			}
			int di = D(i);
			if (di <= i) {
				continue;
			}
			int ddi = D(di);
			if (ddi == i) {
				amicables[i] = true;
				amicables[di] = true;
			}
		}
	}

	static int D(int number) {
		int sum = 1;
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				sum += i;
				if (i != number / i) {
					sum += number / i;
				}
			}
		}
		return sum;
	}
}
