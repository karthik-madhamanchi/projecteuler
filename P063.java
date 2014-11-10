import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class P063 {
	public static void main(String[] args) {
		System.err.println(solve().size());

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		BigInteger lower = power10(N - 1);
		BigInteger upper = power10(N);
		for (BigInteger solution : solve()) {
			if (solution.compareTo(lower) >= 0 && solution.compareTo(upper) < 0) {
				System.out.println(solution);
			}
		}
		in.close();
	}

	static BigInteger power10(int exponent) {
		BigInteger result = BigInteger.ONE;
		BigInteger ten = new BigInteger("10");
		for (int i = 0; i < exponent; i++) {
			result = result.multiply(ten);
		}
		return result;
	}

	static List<BigInteger> solve() {
		List<BigInteger> solutions = new ArrayList<BigInteger>();
		for (int i = 1; i <= 9; i++) {
			BigInteger base = new BigInteger(i + "");
			BigInteger power = BigInteger.ONE;
			for (int exponent = 1;; exponent++) {
				power = power.multiply(base);
				int powerLen = power.toString().length();
				if (powerLen < exponent) {
					break;
				}
				if (powerLen == exponent) {
					solutions.add(power);
				}
			}
		}
		Collections.sort(solutions);
		return solutions;
	}
}
