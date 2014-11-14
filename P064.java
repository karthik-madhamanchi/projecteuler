import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P064 {
	public static void main(String[] args) {
		System.err.println(solve(10000));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		System.out.println(solve(N));
		in.close();
	}

	static int solve(int N) {
		int count = 0;
		for (int i = 1; i <= N; i++) {
			if (computePeriod(i) % 2 != 0) {
				count++;
			}
		}
		return count;
	}

	static int computePeriod(int N) {
		int whole = (int) Math.round(Math.sqrt(N));
		if (whole * whole > N) {
			whole--;
		}
		if (whole * whole == N) {
			return 0;
		}
		int x = 1;
		int y = -whole;
		int z = 1;
		List<Fraction> fractions = new ArrayList<Fraction>();
		fractions.add(new Fraction(x, y, z));
		while (true) {
			int nextX = x * z;
			int nextY = -y * z;
			int nextZ = x * x * N - y * y;
			int common = gcd(gcd(Math.abs(nextX), Math.abs(nextY)),
					Math.abs(nextZ));
			nextX /= common;
			nextY /= common;
			nextZ /= common;
			while (nextX * nextX * N >= (nextZ - nextY) * (nextZ - nextY)) {
				nextY -= nextZ;
			}
			x = nextX;
			y = nextY;
			z = nextZ;
			Fraction f = new Fraction(x, y, z);
			int index = fractions.indexOf(f);
			if (index == -1) {
				fractions.add(f);
			} else {
				return fractions.size() - index;
			}
		}
	}

	static int gcd(int a, int b) {
		while (b != 0) {
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
	}
}

// F = (x * sqrt(N) + y) / z;
class Fraction {
	int x;
	int y;
	int z;

	public Fraction(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public boolean equals(Object obj) {
		Fraction other = (Fraction) obj;
		return x == other.x && y == other.y && z == other.z;
	}
}