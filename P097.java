import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P097 {
	static final int DIGIT_NUM = 12;
	static final long[] POW10 = new long[DIGIT_NUM + 1];
	static {
		POW10[0] = 1;
		for (int i = 1; i < POW10.length; i++) {
			POW10[i] = POW10[i - 1] * 10;
		}
	}
	static final long MODULO = POW10[DIGIT_NUM];
	static final int SHIFT_STEP = 6;

	public static void main(String[] args) throws Throwable {
		System.err.println(convertToStr(solve(28433, 2, 7830457, 1)).substring(
				2));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		StringTokenizer st = new StringTokenizer(line);
		int T = Integer.parseInt(st.nextToken());
		long sum = 0;
		for (int tc = 0; tc < T; tc++) {
			line = in.readLine();
			st = new StringTokenizer(line);

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			sum = addMod(sum, solve(A, B, C, D));
		}
		System.out.println(convertToStr(sum));
		in.close();
	}

	static String convertToStr(long number) {
		return String.format("%0" + DIGIT_NUM + "d", number);
	}

	static long solve(int A, int B, int C, int D) {
		return addMod(multiplyMod(A, powMod(B, C)), D);
	}

	static long powMod(long base, int exponent) {
		long result = 1;
		while (exponent != 0) {
			if (exponent % 2 != 0) {
				result = multiplyMod(result, base);
			}

			base = multiplyMod(base, base);
			exponent /= 2;
		}
		return result;
	}

	static long multiplyMod(long x, long y) {
		long result = 0;
		int shiftNum = 0;
		while (y != 0) {
			result = addMod(result,
					shiftMod(x * (y % POW10[SHIFT_STEP]), shiftNum));
			y /= POW10[SHIFT_STEP];
			shiftNum += SHIFT_STEP;
		}
		return result;
	}

	static long shiftMod(long number, int shiftNum) {
		return number % POW10[DIGIT_NUM - shiftNum] * POW10[shiftNum];
	}

	static long addMod(long x, long y) {
		return (x + y) % MODULO;
	}
}
