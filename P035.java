import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P035 {
	static final int LIMIT = 1000000;
	static List<Integer> circularPrimes;

	public static void main(String args[]) {
		buildCircularPrimes();

		System.err.println(solve(1000000).stream().count());

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		System.out.println(solve(N).stream().mapToInt(i -> i).sum());
		in.close();
	}

	static void buildCircularPrimes() {
		circularPrimes = new ArrayList<Integer>();
		boolean primes[] = new boolean[LIMIT];
		for (int i = 1; i < primes.length; i++) {
			if (!primes[i]) {
				boolean allPrime = true;
				List<Integer> rotations = new ArrayList<Integer>();
				int p = i;
				for (int j = 0; j == 0 || p != i; j++) {
					rotations.add(p);
					if ((primes[p] = isPrime(p)) == false) {
						allPrime = false;
					}
					p = rotate(p, (int) Math.pow(10, Math.floor(Math.log10(i))));
				}
				if (allPrime) {
					circularPrimes.addAll(rotations);
				}
			}
		}
	}

	static List<Integer> solve(int N) {
		return circularPrimes.stream()
				.filter(circularPrime -> circularPrime < N)
				.collect(Collectors.toList());
	}

	static boolean isPrime(int number) {
		if (number == 1) {
			return false;
		}
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	static int rotate(int current, int highestBase) {
		return current % 10 * highestBase + current / 10;
	}
}
