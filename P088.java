import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class P088 {
	public static void main(String[] args) {
		System.err.println(solve(12000));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		System.out.println(solve(N));
		in.close();
	}

	static int solve(int N) {
		Map<Integer, Integer> size2minProductSumNumber = new HashMap<Integer, Integer>();
		for (int i = 2; i <= N; i++) {
			size2minProductSumNumber.put(i, Integer.MAX_VALUE);
		}

		search(size2minProductSumNumber, N, 1, 0, 2, 0);

		return new HashSet<Integer>(size2minProductSumNumber.values()).stream()
				.mapToInt(x -> x).sum();
	}

	static void search(Map<Integer, Integer> size2minProductSumNumber, int N,
			int product, int sum, int maxNumber, int length) {
		for (int i = maxNumber; i <= N; i++) {
			long nextProduct = (long) product * i;
			int nextSum = sum + i;
			long totalLength = nextProduct - nextSum + length + 1;

			if (totalLength > N) {
				break;
			}

			if (totalLength > 1) {
				size2minProductSumNumber.put((int) totalLength, Math.min(
						size2minProductSumNumber.get((int) totalLength),
						(int) nextProduct));
			}

			search(size2minProductSumNumber, N, (int) nextProduct, nextSum, i,
					length + 1);
		}
	}
}
