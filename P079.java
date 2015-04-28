import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P079 {
	public static void main(String[] args) {
		List<String> sampleAttempts = Arrays.asList(new String[] { "319",
				"680", "180", "690", "129", "620", "762", "689", "762", "318",
				"368", "710", "720", "710", "629", "168", "160", "689", "716",
				"731", "736", "729", "316", "729", "729", "710", "769", "290",
				"719", "680", "318", "389", "162", "289", "162", "718", "729",
				"319", "790", "680", "890", "362", "319", "760", "316", "729",
				"380", "319", "728", "716" });
		System.err.println(solve(sampleAttempts));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		List<String> attempts = new ArrayList<String>();
		for (int i = 0; i < T; i++) {
			attempts.add(in.next());
		}
		String answer = solve(attempts);
		System.out.println(answer == null ? "SMTH WRONG" : answer);
		in.close();
	}

	static String solve(List<String> attempts) {
		StringBuilder password = new StringBuilder();
		while (!attempts.isEmpty()) {
			Character minHead = null;
			for (String attempt : attempts) {
				char head = attempt.charAt(0);
				if ((minHead == null || head < minHead)
						&& isValidHead(head, attempts)) {
					minHead = head;
				}
			}

			if (minHead == null) {
				return null;
			}

			password.append(minHead);
			attempts = removeHead(attempts, minHead);
		}
		return password.toString();
	}

	static boolean isValidHead(char head, List<String> attempts) {
		for (String attempt : attempts) {
			if (attempt.indexOf(head) >= 1) {
				return false;
			}
		}
		return true;
	}

	static List<String> removeHead(List<String> attempts, char minHead) {
		List<String> result = new ArrayList<String>();
		for (String attempt : attempts) {
			if (attempt.charAt(0) == minHead) {
				if (attempt.length() > 1) {
					result.add(attempt.substring(1));
				}
			} else {
				result.add(attempt);
			}
		}
		return result;
	}
}
