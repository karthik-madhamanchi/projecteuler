import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class P029 {
	public static void main(String args[]) {
		System.err.println(solve(100));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		System.out.println(solve(N));
		in.close();
	}

	static long solve(int N) {
		int maxExponent = (int) Math.ceil(Math.log(N) / Math.log(2));
		HashMap<Integer, Integer> exponent2distinctNum = new HashMap<Integer, Integer>();
		HashSet<Integer> convertedExponents = new HashSet<Integer>();
		for (int i = 2; i <= N; i++) {
			convertedExponents.add(i);
		}
		for (int exponent = 2; exponent <= maxExponent; exponent++) {
			for (int i = 2; i <= N; i++) {
				convertedExponents.add(exponent * i);
			}
			exponent2distinctNum.put(exponent, convertedExponents.size());
		}

		boolean[] visited = new boolean[N + 1];
		long total = 0;
		for (int i = 2; i < visited.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				if ((long) i * i <= N) {
					int exponent = 1;
					for (int power = i * i; power <= N; power *= i) {
						exponent++;
						visited[power] = true;
					}
					total += exponent2distinctNum.get(exponent);
				} else {
					total += N - 1;
				}
			}
		}
		return total;
	}
}
