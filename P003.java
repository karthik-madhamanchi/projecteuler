import java.util.Scanner;

public class P003 {
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
		long n = 1;
		long number = N;
		while (number != 1) {
			if (n * n > number) {
				n = number;
			} else {
				n++;
			}
			while (number % n == 0) {
				number /= n;
			}
		}
		return n;
	}
}
