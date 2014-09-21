import java.util.Scanner;

public class P019 {
	static long year;
	static int month;

	public static void main(String[] args) {
		System.err.println(solve(1901, 1, 1, 2000, 12, 31));

		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 0; tc < T; tc++) {
			long Y1 = in.nextLong();
			int M1 = in.nextInt();
			int D1 = in.nextInt();
			long Y2 = in.nextLong();
			int M2 = in.nextInt();
			int D2 = in.nextInt();
			System.out.println(solve(Y1, M1, D1, Y2, M2, D2));
		}
		in.close();
	}

	static int solve(long Y1, int M1, int D1, long Y2, int M2, int D2) {
		int sundayNum = 0;
		year = Y1;
		month = M1;
		if (D1 > 1) {
			moveMonth();
		}
		while (year < Y2 || (year == Y2 && month <= M2)) {
			if (isSunday(year, month)) {
				sundayNum++;
			}
			moveMonth();
		}
		return sundayNum;
	}

	static void moveMonth() {
		month++;
		if (month > 12) {
			year++;
			month = 1;
		}
	}

	static boolean isSunday(long year, int month) {
		return daysTo(year, month, 1) % 7 == 6;
	}

	static long daysTo(long year, int month, int day) {
		long days = 0;
		if (year > 1900) {
			days += countWholeYears(1900, year - 1);
		}
		for (int m = 1; m < month; m++) {
			days += getDaysInMonth(year, m);
		}
		days += day;
		return days - 1;
	}

	static int getDaysInMonth(long year, int m) {
		if (m == 4 || m == 6 || m == 9 || m == 11) {
			return 30;
		} else if (m == 2) {
			return 28 + (isLeap(year) ? 1 : 0);
		} else {
			return 31;
		}
	}

	static boolean isLeap(long year) {
		return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
	}

	static long countWholeYears(long startYear, long endYear) {
		long leapNum = ((endYear - startYear) / 4 + 1)
				- ((endYear - startYear) / 100
						- ((endYear - startYear) / 100 + 3) / 4 + 1);
		return (endYear - startYear + 1) * 365 + leapNum;
	}
}
