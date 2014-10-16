import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class P055 {
	static final int ITERATION_LIMIT = 59;

	public static void main(String[] args) {
		int sampleLimit = 10000;
		System.err.println(sampleLimit
				- solve(sampleLimit, false).values().stream().mapToInt(x -> x)
						.sum());

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Map<String, Integer> palindrome2count = solve(N, true);
		String resultPalindrome = null;
		int maxCount = 0;
		for (Entry<String, Integer> entry : palindrome2count.entrySet()) {
			String palindrome = entry.getKey();
			int count = entry.getValue();
			if (count > maxCount) {
				maxCount = count;
				resultPalindrome = palindrome;
			}
		}
		System.out.println(resultPalindrome + " " + maxCount);
		in.close();
	}

	static Map<String, Integer> solve(int N, boolean zeroIteration) {
		Map<String, Integer> palindrome2count = new HashMap<String, Integer>();
		for (int i = 1; i <= N; i++) {
			boolean found = false;
			BigInteger number = new BigInteger(i + "");
			if (zeroIteration && isPalindrome(number.toString())) {
				found = true;
			}
			if (!found) {
				for (int j = 0; j < ITERATION_LIMIT; j++) {
					BigInteger reverse = new BigInteger(new StringBuffer(
							number.toString()).reverse().toString());
					number = number.add(reverse);
					if (isPalindrome(number.toString())) {
						found = true;
						break;
					}
				}
			}
			if (found) {
				String numberStr = number.toString();
				if (!palindrome2count.containsKey(numberStr)) {
					palindrome2count.put(numberStr, 0);
				}
				palindrome2count.put(numberStr,
						palindrome2count.get(numberStr) + 1);
			}
		}
		return palindrome2count;
	}

	static boolean isPalindrome(String str) {
		for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
			if (str.charAt(i) != str.charAt(j)) {
				return false;
			}
		}
		return true;
	}
}
