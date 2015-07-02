import java.util.Scanner;

public class P091 {
	public static void main(String[] args) {
		System.err.println(solve(50));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		System.out.println(solve(N));
		in.close();
	}

	static int solve(int N) {
		int count = 3 * N * N;
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				int common = gcd(x, y);
				int deltaX = y / common;
				int deltaY = x / common;
				count += Math.min(x / deltaX, (N - y) / deltaY)
						+ Math.min((N - x) / deltaX, y / deltaY);
			}
		}
		return count;
	}

	static int gcd(int a, int b) {
		while (b != 0) {
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
	}
}
