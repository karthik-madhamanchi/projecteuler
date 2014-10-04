import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P040 {
	public static void main(String[] args) throws Throwable {
		System.err.println(solve(new long[] { 1, 10, 100, 1000, 10000, 100000,
				1000000 }));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		StringTokenizer st = new StringTokenizer(line);
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			line = in.readLine();
			st = new StringTokenizer(line);
			long[] indices = new long[7];
			for (int i = 0; i < indices.length; i++) {
				indices[i] = Long.parseLong(st.nextToken());
			}
			System.out.println(solve(indices));
		}
		in.close();
	}

	static int solve(long[] indices) {
		int product = 1;
		for (long index : indices) {
			product *= findDigit(index);
		}
		return product;
	}

	static int findDigit(long index) {
		long remain = index;
		long allNum = 9;
		long startNum = 1;
		for (int digitNum = 1;; digitNum++) {
			long allDigitNum = allNum * digitNum;
			if (remain <= allDigitNum) {
				long number = startNum + (remain - 1) / digitNum;
				int digitIndex = (int) ((remain - 1) % digitNum);
				return (number + "").charAt(digitIndex) - '0';
			} else {
				remain -= allDigitNum;
			}
			allNum *= 10;
			startNum *= 10;
		}
	}
}
