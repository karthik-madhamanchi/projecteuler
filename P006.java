import java.util.Scanner;

public class P006 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static long solve(int N) {
		long sum = 0;
		long sumSqure = 0;
		for (long i = 1; i <= N; i++) {
			sum += i;
			sumSqure += i * i;
		}
		long squreSum = sum * sum;
		return squreSum - sumSqure;
	}
}
