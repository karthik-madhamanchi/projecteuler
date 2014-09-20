import java.util.Scanner;
import java.util.stream.IntStream;

public class P017 {
	static final String[] DESC_1000 = { "", "Thousand", "Million", "Billion",
			"Trillion" };
	static final String[] DESC_1 = { "", "One", "Two", "Three", "Four", "Five",
			"Six", "Seven", "Eight", "Nine" };
	static final String[] DESC_1x = { "Ten", "Eleven", "Twelve", "Thirteen",
			"Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
			"Nineteen" };
	static final String[] DESC_10 = { "", "", "Twenty", "Thirty", "Forty",
			"Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

	public static void main(String[] args) {
		System.err.println(IntStream.rangeClosed(1, 1000)
				.mapToObj((N) -> solve(N, true))
				.mapToInt((words) -> words.replaceAll(" ", "").length()).sum());

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			long N = in.nextLong();
			System.out.println(solve(N, false));
		}
		in.close();
	}

	static String solve(long N, boolean needAnd) {
		if (N == 0) {
			return "Zero";
		}
		int desc1000Index = 0;
		String result = "";
		while (N != 0) {
			int part = (int) (N % 1000);
			int hundred = part / 100;
			int digit2 = part - hundred * 100;
			boolean partNeedAnd = needAnd && hundred > 0 && digit2 > 0;
			String partDesc = "";
			if (hundred > 0) {
				partDesc += DESC_1[hundred] + " Hundred";
			}
			if (partNeedAnd) {
				partDesc += " And ";
			}
			if (digit2 > 0) {
				if (!partDesc.isEmpty()) {
					partDesc += " ";
				}
				if (digit2 < 10) {
					partDesc += DESC_1[digit2];
				} else if (digit2 < 20) {
					partDesc += DESC_1x[digit2 - 10];
				} else {
					partDesc += (DESC_10[digit2 / 10] + " " + DESC_1[digit2 % 10])
							.trim();
				}
			}
			if (!partDesc.isEmpty()) {
				partDesc += " " + DESC_1000[desc1000Index];
			}
			desc1000Index++;
			result = (partDesc + " " + result).trim();
			N /= 1000;
		}
		return result;
	}
}
