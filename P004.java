import java.util.Scanner;

public class P004 {
	public static void main(String args[]) {
		System.err.println(solve(Integer.MAX_VALUE));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static int solve(int N) {
		int max = -1;
		for (int i = 100; i <= 999; i++) {
			for (int j = 100; j <= 999; j++) {
				int product = i * j;
				if (product >= N) {
					break;
				}
				if (isPalindrome(product + "") && product > max) {
					max = product;
				}
			}
		}
		return max;
	}

	static boolean isPalindrome(String str) {
		int pos1 = 0;
		int pos2 = str.length() - 1;
		while (pos1 <= pos2 && str.charAt(pos1) == str.charAt(pos2)) {
			pos1++;
			pos2--;
		}
		return !(pos1 <= pos2);
	}
}
