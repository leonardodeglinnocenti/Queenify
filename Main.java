import java.util.Scanner;
import java.lang.String;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		testQueenify();
	}

	public static void queenify() throws InterruptedException {
		int size;
		Scanner userInput = new Scanner(System.in);
		do {
			System.out.print("Insert chessboard size: ");
			size = userInput.nextInt();
		} while (size < 4);

		long start, end, elapsedTime;

		OptimizedQueenify optimizedQueenify = new OptimizedQueenify();
		optimizedQueenify.setSize(size);
		optimizedQueenify.setShowResult(true);

		Thread t = new Thread(optimizedQueenify);

		start = System.currentTimeMillis();
		t.start();
		t.join();
		end = System.currentTimeMillis();

		elapsedTime = end - start;

		System.out.println();
		System.out.println("The algorithm took: " + elapsedTime + "ms");
	}


	public static void compareQueenify() throws InterruptedException {
		int size, numberOfCycles;
		Scanner userInput = new Scanner(System.in);
		do {
			System.out.print("Insert chessboard size: ");
			size = userInput.nextInt();
		} while (size < 4);

		System.out.print("Insert number of cycles: ");
		numberOfCycles = userInput.nextInt();

		long start, end;
		long nonOptimizedElapsed;
		long optimizedElapsed ;
		long nonOptimizedAverage = 0;
		long optimizedAverage = 0;

		for (int i = 0; i < numberOfCycles; i++) { // this recursion tests the two algorithms a number of times given in input

			Queenify queenify = new Queenify();
			OptimizedQueenify optimizedQueenify = new OptimizedQueenify();
			queenify.setSize(size);
			optimizedQueenify.setSize(size);

			Thread threadNonOptimized = new Thread(queenify);
			Thread threadOptimized = new Thread(optimizedQueenify);

			start = System.currentTimeMillis();
			threadNonOptimized.start();
			threadNonOptimized.join(); // wait for the thread to finish
			end = System.currentTimeMillis();
			nonOptimizedElapsed = end - start;

			start = System.currentTimeMillis();
			threadOptimized.start();
			threadOptimized.join(); // wait for the thread to finish
			end = System.currentTimeMillis();
			optimizedElapsed = end - start;

			optimizedAverage += optimizedElapsed;
			nonOptimizedAverage += nonOptimizedElapsed;

		}

		optimizedAverage = optimizedAverage / size;
		nonOptimizedAverage = nonOptimizedAverage / size;

		System.out.println();
		System.out.println("Non-optimized took: " + nonOptimizedAverage + "ms");
		System.out.println("Optimized took: " + optimizedAverage + "ms");
	}

	public static void testQueenify() throws InterruptedException { // what is the size after which the optimized version of the algorithm is no longer faster?
		int numberOfCycles, maxCycles, size, sizeStep;
		Scanner userInput = new Scanner(System.in);

		System.out.print("Insert number of cycles for each test: ");
		numberOfCycles = userInput.nextInt();

		System.out.print("Insert maximum number of cycles: ");
                maxCycles = userInput.nextInt();

		System.out.print("Insert initial size of the chessboard: ");
		size = userInput.nextInt();

		System.out.print("Insert step for incrementing size: ");
		sizeStep = userInput.nextInt();

		long start, end;
		long nonOptimizedElapsed;
		long optimizedElapsed;
		long nonOptimizedAverage = 0;
		long optimizedAverage = 0;
		
		int counter = 0;
		int optimizedWins = 0;
		int nonOptimizedWins = 0;
		do {

			for (int i = 0; i < numberOfCycles; i++) { // this recursion tests the two algorithms a number of times given in input

				Queenify queenify = new Queenify();
				OptimizedQueenify optimizedQueenify = new OptimizedQueenify();
				queenify.setSize(size);
				optimizedQueenify.setSize(size);

				Thread threadNonOptimized = new Thread(queenify);
				Thread threadOptimized = new Thread(optimizedQueenify);

				start = System.currentTimeMillis();
				threadNonOptimized.start();
				threadNonOptimized.join(); // wait for the thread to finish
				end = System.currentTimeMillis();
				nonOptimizedElapsed = end - start;

				start = System.currentTimeMillis();
				threadOptimized.start();
				threadOptimized.join(); // wait for the thread to finish
				end = System.currentTimeMillis();
				optimizedElapsed = end - start;

				optimizedAverage += optimizedElapsed;
				nonOptimizedAverage += nonOptimizedElapsed;

			}

			optimizedAverage = optimizedAverage / numberOfCycles;
			nonOptimizedAverage = nonOptimizedAverage / numberOfCycles;

			System.out.println();
			System.out.println("Non-optimized took: " + nonOptimizedAverage + "ms");
			System.out.println("Optimized took: " + optimizedAverage + "ms");

			size = size + sizeStep;
			
			counter++;

			if (optimizedAverage > nonOptimizedAverage)
				nonOptimizedWins++;
			else
				optimizedWins++;
		} while (maxCycles > counter);

		size -= sizeStep;
		System.out.println();
		System.out.println("Optimized Wins: " + optimizedWins);
		System.out.println("Non-optimized Wins: " + nonOptimizedWins);
		System.out.println();

	}
	
}
