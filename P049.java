import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class P049 {
	static final int LIMIT = 1000000;
	static boolean[] primes = new boolean[LIMIT];

	@SuppressWarnings("unchecked")
	static List<Sequence>[] results = new List[5];

	public static void main(String[] args) {
		buildPrimes();
		buildAllResults();

		solve(9999, 3).stream()
				.filter(result -> !result.equals("148748178147"))
				.forEach(result -> System.err.println(result));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		solve(N, K).forEach(result -> System.out.println(result));
		in.close();
	}

	static void buildPrimes() {
		Arrays.fill(primes, true);
		primes[0] = false;
		primes[1] = false;
		for (int i = 2; i * i < primes.length; i++) {
			if (primes[i]) {
				for (int j = i * i; j < primes.length; j += i) {
					primes[j] = false;
				}
			}
		}
	}

	static void buildAllResults() {
		for (int k = 3; k <= 4; k++) {
			buildResults(k);
		}
	}

	static void buildResults(int k) {
		results[k] = new ArrayList<Sequence>();
		boolean[] visited = new boolean[LIMIT];
		for (int i = 1000; i < visited.length; i++) {
			if (!primes[i] || visited[i]) {
				continue;
			}
			Set<Integer> primePermutations = findPrimePermutations(i);
			for (int first : primePermutations) {
				for (int second : primePermutations) {
					if (first >= second) {
						continue;
					}
					int delta = second - first;
					List<Integer> sequence = new ArrayList<Integer>();
					for (int j = 0; j < k; j++) {
						sequence.add(first + j * delta);
					}
					if (sequence.stream().allMatch(
							element -> primePermutations.contains(element))) {
						results[k].add(new Sequence(first, delta, k));
					}
				}
			}
			primePermutations.stream().forEach(
					permutation -> visited[permutation] = true);
		}
		Collections.sort(results[k]);
	}

	static Set<Integer> findPrimePermutations(int number) {
		Set<Integer> primePermutations = new HashSet<Integer>();
		search(primePermutations, new StringBuilder(number + ""), 0);
		return primePermutations;
	}

	static void search(Set<Integer> primePermutations, StringBuilder str,
			int index) {
		if (index == str.length() - 1) {
			int permutation = Integer.parseInt(str.toString());
			if (primes[permutation]) {
				primePermutations.add(permutation);
			}
			return;
		}
		for (int i = index; i < str.length(); i++) {
			if (index == 0 && str.charAt(i) == '0') {
				continue;
			}
			swap(str, index, i);
			search(primePermutations, str, index + 1);
			swap(str, index, i);
		}
	}

	static void swap(StringBuilder str, int index1, int index2) {
		char temp = str.charAt(index1);
		str.setCharAt(index1, str.charAt(index2));
		str.setCharAt(index2, temp);
	}

	static List<String> solve(int N, int K) {
		return results[K].stream().filter(result -> result.first < N)
				.map(Sequence::toString).collect(Collectors.toList());
	}
}

class Sequence implements Comparable<Sequence> {
	int first;
	int delta;
	int size;

	public Sequence(int first, int delta, int size) {
		this.first = first;
		this.delta = delta;
		this.size = size;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < size; i++) {
			result.append(first + i * delta);
		}
		return result.toString();
	}

	@Override
	public int compareTo(Sequence o) {
		return (this.first != o.first) ? (this.first - o.first)
				: (this.delta - o.delta);
	}
}