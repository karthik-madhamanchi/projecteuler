import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class P028 {
	static final int MODULO = 1000000007;

	public static void main(String args[]) throws Throwable {
		System.err.println(solve(1001));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		StringTokenizer st = new StringTokenizer(line);
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			line = in.readLine();
			st = new StringTokenizer(line);
			long N = Long.parseLong(st.nextToken());
			System.out.println(solve(N));
		}
		in.close();
	}

	static int solve(long N) {
		BigInteger biN = new BigInteger(N + "");
		return biN.pow(3).multiply(new BigInteger("4"))
				.add(biN.pow(2).multiply(new BigInteger("3")))
				.add(biN.multiply(new BigInteger("8")))
				.subtract(new BigInteger("9")).divide(new BigInteger("6"))
				.mod(new BigInteger(MODULO + "")).intValue();
	}
}
