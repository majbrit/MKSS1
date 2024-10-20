package frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Input {

	public static boolean readBoolean() {
		boolean result;
		try {
			result = Boolean.valueOf(readString()).booleanValue();
		} catch(NumberFormatException e) {
			result = false;
		}

		return result;
	}

	public static double readDouble() {
		double result;
		try {
			result = Double.valueOf(readString()).doubleValue();
		} catch(NumberFormatException e) {
			result = 0d;
		}

		return result;
	}

	public static float readFloat() {
		float result;
		try {
			result = Float.valueOf(readString()).floatValue();
		}
		catch(NumberFormatException e) {
			result = 0f;
		}

		return result;
	}

	public static int readInt() {
		int result = 0;
		String v = null;
		try {
			v = readString();
			result = Integer.decode(v).intValue();
		} catch(NumberFormatException e) {
			return 0;
		}

		return result;
	}

	public static char readChar() {
		char result;
		String v = null;
		try {
			v = readString();
			result = v.charAt(0);
		} catch(NumberFormatException | StringIndexOutOfBoundsException e) {
			return 'n';
		}

		return result;
	}

	public static String readString() {
		String result;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			result = in.readLine();
		} catch(IOException e) {
			result = "";
		}
		return result;
	}

	// Only for test purposes
	public static void main(String[] s) {
		int eingabe = 0;
		while(eingabe != -1) {
			System.out.print("Enter text: ");
			System.out.println("frontend.Input was:" + Input.readString());
			System.out.print("Enter float: ");
			System.out.println("frontend.Input was:" + Input.readFloat());
			System.out.print("Enter double: ");
			System.out.println("frontend.Input was:" + Input.readDouble());
			System.out.print("Enter boolean: ");
			System.out.println("frontend.Input was:" + Input.readBoolean());
			System.out.print("Enter integer number (Cancel with -1): ");
			eingabe = Input.readInt();
			System.out.println("frontend.Input was: " + eingabe);
		}
	}
}
