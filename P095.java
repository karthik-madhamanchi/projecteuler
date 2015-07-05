import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P095 {
	static final boolean HACKERRANK = true;

	public static void main(String[] args) {
		if (!HACKERRANK) {
			System.err.println(solve(1000000));
		} else {
			Scanner in = new Scanner(System.in);
			int N = in.nextInt();
			System.out.println(solve(N));
			in.close();
		}
	}

	static int solve(int N) {
		int[] lengths = new int[N + 1];
		for (int i = 2; i < lengths.length; i++) {
			if (lengths[i] != 0) {
				continue;
			}

			List<Integer> chain = new ArrayList<Integer>();
			chain.add(i);
			int number = i;
			while (true) {
				List<PrimeDivisor> pds = findPrimeDivisors(number);
				int nextNumber = sumOfDivisors(pds, 0, 1) - number;

				if (nextNumber > N || lengths[nextNumber] != 0) {
					for (int j = 0; j < chain.size(); j++) {
						lengths[chain.get(j)] = -1;
					}
					break;
				}

				int head = chain.indexOf(nextNumber);
				if (head >= 0) {
					for (int j = 0; j < head; j++) {
						lengths[chain.get(j)] = -1;
					}
					for (int j = head; j < chain.size(); j++) {
						lengths[chain.get(j)] = chain.size() - head;
					}
					break;
				}
				chain.add(nextNumber);
				number = nextNumber;
			}
		}

		int maxLength = -1;
		int minMember = 0;
		for (int i = 2; i < lengths.length; i++) {
			if (lengths[i] > maxLength) {
				maxLength = lengths[i];
				minMember = i;
			}
		}
		return minMember;
	}

	static List<PrimeDivisor> findPrimeDivisors(int number) {
		List<PrimeDivisor> pds = new ArrayList<PrimeDivisor>();
		for (int i = 2; i * i <= number; i++) {
			if (isPrime(i) && number % i == 0) {
				int count = 0;
				while (number % i == 0) {
					number /= i;
					count++;
				}
				pds.add(new PrimeDivisor(i, count));
			}
		}
		if (number > 1) {
			pds.add(new PrimeDivisor(number, 1));
		}
		return pds;
	}

	static boolean isPrime(int number) {
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	static int sumOfDivisors(List<PrimeDivisor> pds, int index, int curDivisor) {
		if (index == pds.size()) {
			return curDivisor;
		}

		int result = 0;
		int power = 1;
		for (int i = 0; i <= pds.get(index).exponent; i++) {
			result += sumOfDivisors(pds, index + 1, curDivisor * power);
			power *= pds.get(index).prime;
		}
		return result;
	}
}

class PrimeDivisor {
	int prime;
	int exponent;

	PrimeDivisor(int prime, int exponent) {
		this.prime = prime;
		this.exponent = exponent;
	}
}