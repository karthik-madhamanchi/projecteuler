import java.util.ArrayList;
import java.util.Scanner;

public class P012 {
	public static void main(String args[]) {
		System.err.println(solve(500));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static int solve(int N) {
		ArrayList<Integer> divisorNums = new ArrayList<Integer>();
		divisorNums.add(0);
		divisorNums.add(1);
		for (int n = 2;; n++) {
			if (n % 2 == 0) {
				int facNum2 = getFactorNum2(n / 2);
				divisorNums.add(divisorNums.get(n / 2) / (facNum2 + 1)
						* (facNum2 + 2));
			} else {
				int facNum = getFactorNum(n);
				divisorNums.add(facNum);
				if (facNum * divisorNums.get((n - 1) / 2) > N) {
					return (n - 1) * n / 2;
				}
				if (facNum * divisorNums.get((n + 1) / 2) > N) {
					return n * (n + 1) / 2;
				}
			}
		}
	}

	static int getFactorNum2(int a) {
		int count = 0;
		while (a % 2 == 0) {
			a /= 2;
			count++;
		}
		return count;
	}

	static int getFactorNum(int a) {
		int factorNum = 1;
		for (int i = 3; i * i <= a; i += 2) {
			if (isPrime(i)) {
				int count = 0;
				while (a % i == 0) {
					a /= i;
					count++;
				}
				factorNum *= count + 1;
			}
		}
		if (a > 1) {
			factorNum *= 2;
		}
		return factorNum;
	}

	static boolean isPrime(int a) {
		for (int i = 3; i * i <= a; i += 2) {
			if (a % i == 0) {
				return false;
			}
		}
		return true;
	}
}
