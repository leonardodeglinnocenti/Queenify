import java.util.Scanner;

public class Main {	
	public static void main(String[] args) {
		int size;
		Scanner userInput = new Scanner(System.in);
		do {
			System.out.print("Insert chessboard size: ");
			size = userInput.nextInt();
		} while (size < 4);
		Chessboard c = new Chessboard(size);
		System.out.println();
		int failures = 0;
		while (!c.queenify()) {
			failures++;
			//c.print();
			c.clear();
			//System.out.println("FAILURE n˚" + failures);
		}
		c.print();
		System.out.println("SUCCESS");
		System.out.println("FAILURES:" + failures);
	}
}
