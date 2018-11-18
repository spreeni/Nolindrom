/**
 * 
 */

/**
 * @author Yannic
 *
 */
public class Nolindrom {

	/**
	 * @param args
	 */
	public static void main(String[] s) {
		// Takes an input array and launches the regular Program or the special Program if marked with an "x"
		if (s.length > 1) {
			if (s[1].equals("x")) specialApp(s[0]);
		} else regularApp(s[0]);
	}
	
	private static void regularApp(String x) {
		// Searches for each number up to an Input maximum if there is a Palindrom reachable. Prints the number if there is none.
		long max = Long.parseLong(x);
		for (long i=1; i <= max; i++) {
			if (isNolindrom(i)) {
				System.out.println(Long.toString(i));
			}
		}
	}
		
	private static boolean isNolindrom(long x) {
		// Searches for an input number if there is a Palindrom reachable with less than 100 iterations and before an long overflow is reached. Returns false if there is a Palindrom.
		long n = x;
		long r = reverseLong(n);
		int j = 0;
		while (j < 100) {
			if (Long.MAX_VALUE - n < r) {
				return true;
			} else if (reverseLong(n+r) == 0) {
				return true;
			}
			j++;
			if (isPalindrom(n+r)) {
				return false;
			}
			n += r;
			r = reverseLong(n);
		}
		return true;
	}
	
	
	private static boolean isPalindrom(long x) {
		// Checks if an input long is a Palindrom
		long rev = reverseLong(x);
		return (x == rev);
	}
	
	private static long reverseLong(long x) {
		// Reverses an input long
		String temp = new StringBuilder(Long.toString(x)).reverse().toString();
		if ((temp.length() == 19) && (Long.parseLong(temp.substring(0, 18)) > Long.parseLong(Long.toString(Long.MAX_VALUE).substring(0, 18)))) {
			return 0;
		}
		return Long.parseLong(temp);
	}
	
	
	
	
	private static void specialApp(String x) {
		// Same thing as regularApp, but using strings to avoid overflow
		long max = Long.parseLong(x);
		for (long i=1; i <= max; i++) {
			if (isNolindrom(i)) {
				String[] arr = specialIsNolindrom(i);
				if (Integer.parseInt(arr[1]) != 0) {
					System.out.println(i + " braucht "+ arr[1] + " Iterationen bis zum Palindrom " + arr[0]);
					return;
				}
			}
		}
		System.out.println("alle Zahlen werden auch durch Abbruch per Ueberlauf gefunden");
	}
	
	private static String[] specialIsNolindrom(long x) {
		// Same thing as isNolindrom, but using strings to avoid overflow
		String nStr = Long.toString(x);
		String rStr = new StringBuilder(nStr).reverse().toString();
		int j = 0;
		String[] output = {"", "0"};
		while (j < 100) {
			j++;
			nStr = addLongStrings(nStr,rStr);
			rStr = new StringBuilder(nStr).reverse().toString();
			if (nStr.equals(rStr)) {
				output[0] = nStr;
				output[1] =	Integer.toString(j);
				return output;
			}
		}
		return output;
	}
	
	private static String addLongStrings(String a, String b) {
		// Goes char for char through the strings and adds up the digits. Takes rest into the next loop if the sum is over 10.
		int rest = 0;
		int summe = 0;
		String newStr = "";
		for (int k=a.length()-1; k >= 0; k--) {			
			summe = (Integer.parseInt(Character.toString(a.charAt(k))) + Integer.parseInt(Character.toString(b.charAt(k))) + rest);
			if (summe >= 10 && k != 0) {
				rest = 1;
				newStr = Integer.toString(summe).charAt(1) + newStr;
			} else {
				if (rest == 1) rest = 0;
				newStr = Integer.toString(summe) + newStr;
			}
		}
		return newStr;
	}
	
}
