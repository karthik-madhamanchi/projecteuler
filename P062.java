import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class P062 {
	public static void main(String[] args) {
		for (int digitNum = 1;; digitNum++) {
			List<Long> sampleMinCubes = solve(computeRoot(digitNum), 5);
			if (!sampleMinCubes.isEmpty()) {
				System.err.println(sampleMinCubes.get(0));
				break;
			}
		}

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		List<Long> minCubes = solve(N, K);
		StringBuilder sb = new StringBuilder();
		for (long minCube : minCubes) {
			sb.append(minCube);
			sb.append("\n");
		}
		System.out.print(sb);
		in.close();
	}

	static int computeRoot(int digitNum) {
		long limit = 1;
		for (int i = 0; i < digitNum; i++) {
			limit *= 10;
		}
		int root = (int) Math.round(Math.pow(limit, 1.0 / 3));
		long cube = computeCube(root);
		if (cube >= limit) {
			root--;
		}
		return root;
	}

	static List<Long> solve(int N, int K) {
		Map<Long, Integer> key2count = new HashMap<Long, Integer>();
		Map<Long, Long> key2minCube = new HashMap<Long, Long>();
		for (int i = 1; i < N; i++) {
			long cube = computeCube(i);
			long key = generateKey(cube);
			if (key2count.containsKey(key)) {
				key2count.put(key, key2count.get(key) + 1);
			} else {
				key2count.put(key, 1);
				key2minCube.put(key, cube);
			}
		}

		List<Long> minCubes = new ArrayList<Long>();
		for (Entry<Long, Integer> entry : key2count.entrySet()) {
			long key = entry.getKey();
			int count = entry.getValue();
			if (count == K) {
				minCubes.add(key2minCube.get(key));
			}
		}
		Collections.sort(minCubes);
		return minCubes;
	}

	static long computeCube(int x) {
		return (long) x * x * x;
	}

	static long generateKey(long number) {
		int[] counts = new int[10];
		while (number != 0) {
			counts[(int) (number % 10)]++;
			number /= 10;
		}
		long key = 0;
		for (int i = counts.length - 1; i >= 0; i--) {
			for (int j = 0; j < counts[i]; j++) {
				key = key * 10 + i;
			}
		}
		return key;
	}
}
