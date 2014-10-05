import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P047 {
	public static void main(String[] args) {
		System.err.println(solve(Integer.MAX_VALUE, 4, 1).get(0));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		solve(N, K, Integer.MAX_VALUE).stream().forEach(
				result -> System.out.println(result));
		in.close();
	}

	static List<Integer> solve(int N, int K, int resultSizeLimit) {
		List<Integer> results = new ArrayList<Integer>();
		int consecutive = 0;
		for (int i = 2; i - K + 1 <= N && results.size() < resultSizeLimit; i++) {
			if (getPrimeFactorNum(i) == K) {
				consecutive++;
			} else {
				consecutive = 0;
			}
			if (consecutive >= K) {
				results.add(i - K + 1);
			}
		}
		return results;
	}

	static int getPrimeFactorNum(int number) {
		int count = 0;
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				count++;
				while (number % i == 0) {
					number /= i;
				}
			}
		}
		if (number > 1) {
			count++;
		}
		return count;
	}
}
