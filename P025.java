import java.util.Scanner;

public class P025 {
	static final int LIMIT = 5000;
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

	static void buildResults() {
		int index = 2;
		double a = 1;
		double b = 1;
		int power = 0;
		for (int i = 3; index < results.length; i++) {
			if (a + b >= 10) {
				a /= 10;
				b /= 10;
				power++;
			}
			double c = a + b;
			int lengthC = 1 + power;
			while (index < results.length && lengthC >= index) {
				results[index] = i;
				index++;
			}
			a = b;
			b = c;
		}
	}

	static int solve(int N) {
		return results[N];
	}
}
