import java.util.Scanner;

public class P009 {
	public static void main(String[] args) {
		System.err.println(solve(1000));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static long solve(int N) {
		if (N % 2 != 0) {
			return -1;
		}
		long result = -1;
		int product = N / 2;
		for (int k = 1; k <= product; k++) {
			if (product % k == 0) {
				int subproduct = product / k;
				for (long m = 1; m * m < subproduct; m++) {
					if (subproduct % m == 0) {
						long n = subproduct / m - m;
						if (m > n) {
							long a = m * m - n * n;
							long b = 2 * m * n;
							long c = m * m + n * n;
							result = Math.max(result, a * b * c * k * k * k);
						}
					}
				}
			}
		}
		return result;
	}
}
