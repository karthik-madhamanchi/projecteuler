import java.util.Scanner;

public class P046 {
	public static void main(String[] args) {
		System.err.println(solveEuler());

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static int solveEuler() {
		for (int i = 3;; i += 2) {
			if (isPrime(i)) {
				continue;
			}
			if (solve(i) == 0) {
				return i;
			}
		}
	}

	static boolean isPrime(int number) {
		if (number < 2) {
			return false;
		}
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	static int solve(int N) {
		int wayNum = 0;
		for (int i = 1; i <= Math.sqrt(N / 2); i++) {
			if (isPrime(N - 2 * i * i)) {
				wayNum++;
			}
		}
		return wayNum;
	}
}
