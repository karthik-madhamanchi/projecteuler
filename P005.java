import java.util.Scanner;

public class P005 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static long solve(int N) {
		long result = 1;
		for (int i = 1; i <= N; i++) {
			result = calcLCM(result, i);
		}
		return result;
	}

	static long calcLCM(long a, long b) {
		long gcd = calcGCD(a, b);
		return a / gcd * b;
	}

	static long calcGCD(long a, long b) {
		while (b != 0) {
			long c = a % b;
			a = b;
			b = c;
		}
		return a;
	}
}
