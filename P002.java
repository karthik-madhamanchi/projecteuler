import java.util.Scanner;

public class P002 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			long N = in.nextLong();
			System.out.println(solve(N));
		}
		in.close();
	}

	static long solve(long N) {
		long fa = 1;
		long fb = 2;
		long sum = 2;
		while (true) {
			long fc = fa + fb;
			if (fc > N) {
				break;
			}
			if (fc % 2 == 0) {
				sum += fc;
			}
			fa = fb;
			fb = fc;
		}
		return sum;
	}
}
