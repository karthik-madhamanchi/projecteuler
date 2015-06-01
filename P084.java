import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P084 {
	static final String[] BOARD = { "GO", "A1", "CC1", "A2", "T1", "R1", "B1",
			"CH1", "B2", "B3", "JAIL", "C1", "U1", "C2", "C3", "R2", "D1",
			"CC2", "D2", "D3", "FP", "E1", "CH2", "E2", "E3", "R3", "F1", "F2",
			"U2", "F3", "G2J", "G1", "G2", "CC3", "G3", "R4", "CH3", "H1",
			"T2", "H2" };
	static final int GO_SQUARE = findSquare("GO");
	static final int JAIL_SQUARE = findSquare("JAIL");
	static final int C1_SQUARE = findSquare("C1");
	static final int E3_SQUARE = findSquare("E3");
	static final int H2_SQUARE = findSquare("H2");
	static final int R1_SQUARE = findSquare("R1");
	static final double ERROR = 1E-9;

	public static void main(String[] args) {
		System.err.println(String.join(
				"",
				solve(true, 4, 3).stream()
						.map(index -> String.format("%02d", index))
						.collect(Collectors.toList())));

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		System.out.println(String.join(
				" ",
				solve(false, N, K).stream().map(index -> BOARD[index])
						.collect(Collectors.toList())));
		in.close();
	}

	// Algorithm: Markov chain
	static List<Integer> solve(boolean threeDoublesRule, int diceSide,
			int squareNum) {
		double[][] transitionMatrix = buildTransitionMatrix(threeDoublesRule,
				diceSide);

		double[] probs = new double[transitionMatrix.length];
		probs[0] = 1;
		double prevProbs[];
		do {
			prevProbs = probs;
			probs = multiply(prevProbs, transitionMatrix);
		} while (!isErrorWithin(probs, prevProbs, ERROR));

		List<Square_Prob> squareProbs = buildSquareProbs(threeDoublesRule,
				probs);
		Collections.sort(squareProbs,
				(sp1, sp2) -> (int) Math.signum(sp2.prob - sp1.prob));
		return squareProbs.subList(0, squareNum).stream().map(sp -> sp.square)
				.collect(Collectors.toList());
	}

	static int findSquare(String name) {
		for (int i = 0;; i++) {
			if (BOARD[i].equals(name)) {
				return i;
			}
		}
	}

	static double[][] buildTransitionMatrix(boolean threeDoublesRule,
			int diceSide) {
		int matrixSize = BOARD.length * getDoubleSize(threeDoublesRule);
		double[][] transitionMatrix = new double[matrixSize][matrixSize];
		for (int from = 0; from < BOARD.length; from++) {
			for (int doubleNumber = 0; doubleNumber < getDoubleSize(threeDoublesRule); doubleNumber++) {
				for (int dice1 = 1; dice1 <= diceSide; dice1++) {
					for (int dice2 = 1; dice2 <= diceSide; dice2++) {
						move(threeDoublesRule, transitionMatrix, from,
								doubleNumber, dice1 + dice2, dice1 == dice2,
								1.0 / (diceSide * diceSide));
					}
				}
			}
		}
		return transitionMatrix;
	}

	static void move(boolean threeDoublesRule, double[][] transitionMatrix,
			int from, int doubleNumber, int step, boolean isDouble, double p) {
		int fromIndex = convertToIndex(threeDoublesRule, from, doubleNumber);

		if (threeDoublesRule && doubleNumber == 2 && isDouble) {
			transitionMatrix[fromIndex][convertToIndex(threeDoublesRule,
					JAIL_SQUARE, 0)] += p;
			return;
		}

		int nextDoubleNumber = (threeDoublesRule && isDouble) ? doubleNumber + 1
				: 0;
		int to = (from + step + BOARD.length) % BOARD.length;
		if (BOARD[to].equals("G2J")) {
			transitionMatrix[fromIndex][convertToIndex(threeDoublesRule,
					JAIL_SQUARE, nextDoubleNumber)] += p;
		} else if (BOARD[to].startsWith("CC")) {
			transitionMatrix[fromIndex][convertToIndex(threeDoublesRule, to,
					nextDoubleNumber)] += p * (14.0 / 16);
			transitionMatrix[fromIndex][convertToIndex(threeDoublesRule,
					GO_SQUARE, nextDoubleNumber)] += p * (1.0 / 16);
			transitionMatrix[fromIndex][convertToIndex(threeDoublesRule,
					JAIL_SQUARE, nextDoubleNumber)] += p * (1.0 / 16);
		} else if (BOARD[to].startsWith("CH")) {
			transitionMatrix[fromIndex][convertToIndex(threeDoublesRule, to,
					nextDoubleNumber)] += p * (6.0 / 16);
			transitionMatrix[fromIndex][convertToIndex(threeDoublesRule,
					GO_SQUARE, nextDoubleNumber)] += p * (1.0 / 16);
			transitionMatrix[fromIndex][convertToIndex(threeDoublesRule,
					JAIL_SQUARE, nextDoubleNumber)] += p * (1.0 / 16);
			transitionMatrix[fromIndex][convertToIndex(threeDoublesRule,
					C1_SQUARE, nextDoubleNumber)] += p * (1.0 / 16);
			transitionMatrix[fromIndex][convertToIndex(threeDoublesRule,
					E3_SQUARE, nextDoubleNumber)] += p * (1.0 / 16);
			transitionMatrix[fromIndex][convertToIndex(threeDoublesRule,
					H2_SQUARE, nextDoubleNumber)] += p * (1.0 / 16);
			transitionMatrix[fromIndex][convertToIndex(threeDoublesRule,
					R1_SQUARE, nextDoubleNumber)] += p * (1.0 / 16);
			transitionMatrix[fromIndex][convertToIndex(threeDoublesRule,
					findNextSquare(to, "R"), nextDoubleNumber)] += p
					* (2.0 / 16);
			transitionMatrix[fromIndex][convertToIndex(threeDoublesRule,
					findNextSquare(to, "U"), nextDoubleNumber)] += p
					* (1.0 / 16);
			move(threeDoublesRule, transitionMatrix, from, doubleNumber,
					step - 3, isDouble, p * (1.0 / 16));
		} else {
			transitionMatrix[fromIndex][convertToIndex(threeDoublesRule, to,
					nextDoubleNumber)] += p;
		}
	}

	static int getDoubleSize(boolean threeDoublesRule) {
		return threeDoublesRule ? 3 : 1;
	}

	static int convertToIndex(boolean threeDoublesRule, int square,
			int doubleNumber) {
		return square * getDoubleSize(threeDoublesRule) + doubleNumber;
	}

	static int findNextSquare(int square, String name) {
		do {
			square = (square + 1) % BOARD.length;
		} while (!BOARD[square].startsWith(name));
		return square;
	}

	static double[] multiply(double a[], double matrix[][]) {
		int size = a.length;
		double result[] = new double[size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				result[i] += a[j] * matrix[j][i];
			}
		}
		return result;
	}

	static boolean isErrorWithin(double a[], double b[], double threshold) {
		for (int i = 0; i < a.length; i++) {
			if (Math.abs(a[i] - b[i]) >= threshold) {
				return false;
			}
		}
		return true;
	}

	static List<Square_Prob> buildSquareProbs(boolean threeDoublesRule,
			double[] probs) {
		List<Square_Prob> squareProbs = new ArrayList<Square_Prob>();
		int index = 0;
		while (index < probs.length) {
			double prob = 0;
			for (int i = 0; i < getDoubleSize(threeDoublesRule); i++) {
				prob += probs[index];
				index++;
			}
			squareProbs.add(new Square_Prob(squareProbs.size(), prob));
		}
		return squareProbs;
	}
}

class Square_Prob {
	int square;
	double prob;

	Square_Prob(int square, double prob) {
		this.square = square;
		this.prob = prob;
	}
}