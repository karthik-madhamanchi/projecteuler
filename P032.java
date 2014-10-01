import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P032 {
	public static void main(String[] args) {
		System.err.println(solve(9));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		System.out.println(solve(N));
		in.close();
	}

	static int solve(int N) {
		HashSet<Integer> products = new HashSet<Integer>();

		for (int digitNum1 = 1; digitNum1 * 3 <= N; digitNum1++) {
			for (int digitNum2 = digitNum1; N - digitNum1 - digitNum2 >= digitNum2; digitNum2++) {
				search(products, N, digitNum1, digitNum2,
						new ArrayList<Integer>(), new boolean[N + 1]);
			}
		}

		return products.stream().mapToInt(n -> n).sum();
	}

	static void search(HashSet<Integer> products, int N, int digitNum1,
			int digitNum2, ArrayList<Integer> usedDigits, boolean[] used) {
		if (usedDigits.size() == digitNum1 + digitNum2) {
			int number1 = buildNumber(usedDigits.subList(0, digitNum1));
			int number2 = buildNumber(usedDigits.subList(digitNum1, digitNum1
					+ digitNum2));
			int product = number1 * number2;
			if (isValidProduct(product, N - digitNum1 - digitNum2, used)) {
				products.add(product);
			}
		} else {
			for (int i = 1; i < used.length; i++) {
				if (!used[i]) {
					used[i] = true;
					usedDigits.add(i);
					search(products, N, digitNum1, digitNum2, usedDigits, used);
					usedDigits.remove(usedDigits.size() - 1);
					used[i] = false;
				}
			}
		}
	}

	static int buildNumber(List<Integer> digits) {
		return digits.stream().reduce((result, digit) -> result * 10 + digit)
				.get();
	}

	static boolean isValidProduct(int product, int digitNum, boolean[] used) {
		return (product + "").length() == digitNum
				&& (product + "").chars().boxed().collect(Collectors.toSet())
						.size() == digitNum
				&& (product + "")
						.chars()
						.map(ch -> ch - '0')
						.allMatch(
								digit -> digit > 0 && digit < used.length
										&& !used[digit]);
	}
}
