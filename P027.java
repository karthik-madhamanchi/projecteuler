import java.util.Scanner;

public class P027 {
	public static void main(String args[]) {
		Result sampleResult = solve(999);
		System.err.println(sampleResult.a * sampleResult.b);

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Result result = solve(N);
		System.out.println(result.a + " " + result.b);
		in.close();
	}

	static Result solve(int N) {
		int max = 0;
		int answerA = 0;
		int answerB = 0;
		for (int a = -N; a <= N; a++) {
			for (int b = -N; b <= N; b++) {
				int consecutive = getConsecutive(a, b);
				if (consecutive > max) {
					max = consecutive;
					answerA = a;
					answerB = b;
				}
			}
		}
		return new Result(answerA, answerB);
	}

	static int getConsecutive(int a, int b) {
		for (int n = 0;; n++) {
			int value = n * n + a * n + b;
			if (!isPrime(value)) {
				return n;
			}
		}
	}

	static boolean isPrime(int number) {
		if (number < 2) {
			return false;
		}
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}

class Result {
	int a;
	int b;

	public Result(int a, int b) {
		this.a = a;
		this.b = b;
	}
}