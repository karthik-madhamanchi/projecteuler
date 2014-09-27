import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class P024 {
	public static void main(String[] args) {
		System.err.println(solve("0123456789", 1000000));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			long N = in.nextLong();
			System.out.println(solve("abcdefghijklm", N));
		}
		in.close();
	}

	static String solve(String alphabet, long N) {
		long factorial = LongStream.range(1, alphabet.length()).reduce(1,
				(a, b) -> a * b);
		List<Integer> choices = IntStream.range(0, alphabet.length()).boxed()
				.collect(Collectors.toList());
		String result = "";
		for (int i = alphabet.length() - 1; i >= 1 && N > 0; i--) {
			int index = (int) (N / factorial) - (N % factorial == 0 ? 1 : 0);
			result += alphabet.charAt(choices.get(index));

			N %= factorial;
			choices.remove(index);
			factorial /= i;
		}
		result += IntStream
				.range(0, choices.size())
				.mapToObj(
						i -> alphabet.charAt(choices.get(choices.size() - 1 - i)))
				.collect(StringBuffer::new, StringBuffer::append,
						StringBuffer::append);
		return result;
	}
}
