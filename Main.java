import java.util.Scanner;

public class Main {	
	public static void main(String[] args) {
		System.out.print("Insert chessboard size: ");
		Scanner userInput = new Scanner(System.in);
		int size = userInput.nextInt();
		Chessboard c = new Chessboard(size);
		c.print();
		System.out.println();
		int failures = 0;
		while (!c.queenify()) {
			failures++;
			c.print();
			c.clear();
			System.out.println("FAILURE n˚" + failures);
		}
		System.out.println();
		c.print();
		System.out.println("SUCCESS");
	}
}

