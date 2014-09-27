import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class P023 {
	static int LIMIT = 28124;
	static boolean canWrittens[] = new boolean[LIMIT];

	public static void main(String args[]) {
		buildCanWrittens();

		System.err.println(IntStream.range(0, LIMIT)
				.filter((number) -> solve(number) == "NO").sum());

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = in.nextInt();
			System.out.println(solve(N));
		}
		in.close();
	}

	static void buildCanWrittens() {
		ArrayList<Integer> abundants = new ArrayList<Integer>();
		abundants.add(12);
		canWrittens[24] = true;
		for (int i = 13; i <= LIMIT - 13; i++) {
			if (isAbundant(i)) {
				abundants.add(i);
				for (int j = 0; j < abundants.size(); j++) {
					int index = i + abundants.get(j);
					if (index >= canWrittens.length) {
						break;
					}
					canWrittens[index] = true;
				}
			}
		}
	}

	static String solve(int number) {
		return (number >= canWrittens.length || canWrittens[number]) ? "YES"
				: "NO";
	}

	static boolean isAbundant(int number) {
		int sum = 1;
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				sum += i;
				if (i != number / i) {
					sum += number / i;
				}
			}
		}
		return sum > number;
	}
}
