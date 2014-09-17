import java.util.Scanner;

public class P001 {
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
		return calcMultipleSum(N, 3) + calcMultipleSum(N, 5)
				- calcMultipleSum(N, 15);
	}

	static long calcMultipleSum(int limit, int multiple) {
		if (limit < multiple) {
			return 0;
		}
		int a0 = multiple;
		int an = (limit - 1) / multiple * multiple;
		int n = (an - a0) / multiple + 1;
		return ((long) a0 + an) * n / 2;
	}
}
