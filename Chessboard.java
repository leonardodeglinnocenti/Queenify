import java.util.Random;

public class Chessboard {
	enum Symbol { Q, F, N }
	private Symbol B[][];

	

	public Chessboard(int n) throws IllegalArgumentException {
		if (n >= 4) {
			size = n;
			B = new Symbol[n][n]; // an array with the proper size is initialized
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					B[i][j] = Symbol.F;
				}
			}
		} else throw new IllegalArgumentException();
	}

	public void clear() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				B[i][j] = Symbol.F;
	}

	public void print() {
		for (int i = 0; i < size; i++) {
			System.out.print("\n");
			for (int j = 0; j < size; j++) {
				System.out.print(B[i][j] + " ");
			}
		}
	}

	public Boolean isLegalPosition(int pos) {
		if (pos >= 0 && pos < size)
			return true;
		else
			return false;
	}

	public Boolean placeQueen(int row, int col) {
		if (isLegalPosition(row) && isLegalPosition(col)) {
			B[row][col] = Symbol.Q;
			return true;
		} else return false;
	}

	public int getRandomNumberInRange(int min, int max) throws IllegalArgumentException {
		if (min > max)
			throw new IllegalArgumentException("max must be greater than min");

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public Boolean getQueenPositionInLine(int line, int[] out) {
		for (int j = 0; j < size; j++) {
			if (B[line][j] == Symbol.Q) {
				out[0] = j;
				return true;
			}
		}
		return false;
	}

	public int[] getFreePlacesInLine(int line) { // this function returns in [0] the number of free places found followed by the free positions
		int[] freePlaces = new int[size + 1]; // at most there are size free places + 1 to keep the number of places found
		int counter = 1; // counts the found free places
		for (int j = 0; j < size; j++) {
			if (B[line][j] == Symbol.F) {
				freePlaces[counter++] = j;
			}
		}
		freePlaces[0] = --counter;
		return freePlaces;
	}

	public Boolean placeRandomQueenInLine(int line) { // This method places a Queen in a random free (F) place 
		int place;
		boolean freeSpaceFound = false;
		for (int j = 0; j < size && !freeSpaceFound; j++) // determines if there's at least one place left
			if (B[line][j] == Symbol.F)
				freeSpaceFound = true;
		if (freeSpaceFound) { // TODO: can this be improved?
			do {
				place = getRandomNumberInRange(0, size - 1);
			} while (B[line][place] != Symbol.F);
		} else return false;
		B[line][place] = Symbol.Q;
		launchBeams(line, place);
		return true;
	}

	public boolean optimizedPlaceRandomQueenInLine(int line) { // This method places a Queen in a random free (F) place
		int place;
		int[] freeSpacesFound = getFreePlacesInLine(line);
		if (freeSpacesFound[0] > 0) { // TODO: can this be improved?
			place = getRandomNumberInRange(1, freeSpacesFound[0]);
			place = freeSpacesFound[place]; // get the selected free space
		} else return false;
		B[line][place] = Symbol.Q;
		launchBeams(line, place);
		return true;
	}

	public void launchBeams(int row, int col) {
		for (int j = 0; j < size; j++)
			B[row][j] = Symbol.N; // horizontal beam
		for (int i = row; i < size; i++)
			B[i][col] = Symbol.N; // vertical beam
		int offset = 0; //TODO: Replace conveniently with just j as index
		for (int j = col; j >= 0 && (row + offset) < size; j--) { // oblique beam
			B[row + offset][j] = Symbol.N;
			offset++;
		}
		offset = 0;
		for (int j = col; j < size && (row + offset) < size; j++) {
			B[row + offset][j] = Symbol.N;
			offset++;
		}
		B[row][col] = Symbol.Q; // restoring Queen in her position
	}

	public Boolean queenify() {
		for (int i = 0; i < size; i++) {
			if (!placeRandomQueenInLine(i))
			       	return false;
		}
		return true;
	}

	public Boolean optimizedQueenify() {
		for (int i = 0; i < size; i++) {
			if (!optimizedPlaceRandomQueenInLine(i))
				return false;
		}
		return true;
	}

	private int size;

}
