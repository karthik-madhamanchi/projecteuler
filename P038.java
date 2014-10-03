import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P038 {
	static final int[] LENGTHS = { 8, 9 };
	static Map<Integer, List<Integer>> length2multipliers;

	public static void main(String[] args) {
		buildLength2Multipliers();

		System.err.println(solve(Integer.MAX_VALUE, 9).stream()
				.mapToInt(multiplier -> generatePandigital(multiplier, 9))
				.max().getAsInt());

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		solve(N, K).stream().forEach(
				multiplier -> System.out.println(multiplier));
		in.close();
	}

	static void buildLength2Multipliers() {
		length2multipliers = new HashMap<Integer, List<Integer>>();
		for (int length : LENGTHS) {
			List<Integer> multipliers = new ArrayList<Integer>();
			for (int i = 2; ((long) i * i + "").length() <= length; i++) {
				Integer pandigital = generatePandigital(i, length);
				if (pandigital != null) {
					multipliers.add(i);
				}
			}
			length2multipliers.put(length, multipliers);
		}
	}

	static List<Integer> solve(int N, int K) {
		return length2multipliers.get(K).stream()
				.filter(multiplier -> multiplier < N)
				.collect(Collectors.toList());
	}

	static Integer generatePandigital(int multiplier, int K) {
		String pandigital = "";
		boolean[] used = new boolean[K + 1];
		int remaining = K;
		for (int product = multiplier;; product += multiplier) {
			String productStr = product + "";
			for (int i = 0; i < productStr.length(); i++) {
				int digit = productStr.charAt(i) - '0';
				if (digit == 0 || digit >= used.length || used[digit]) {
					return null;
				}
				used[digit] = true;
			}
			remaining -= productStr.length();
			pandigital += productStr;
			if (remaining == 0) {
				return Integer.parseInt(pandigital);
			}
		}
	}
}
