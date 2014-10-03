import java.util.Scanner;

public class P036 {
	public static void main(String args[]) {
		System.err.println(solve(1000000, 2));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		System.out.println(solve(N, K));
		in.close();
	}

	static int solve(int N, int K) {
		int sum = 0;
		for (int i = 1; i < N; i++) {
			if (isPalindromic(i + "") && isPalindromic(Integer.toString(i, K))) {
				sum += i;
			}
		}
		return sum;
	}

	static boolean isPalindromic(String str) {
		for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
			if (str.charAt(i) != str.charAt(j)) {
				return false;
			}
		}
		return true;
	}
}
