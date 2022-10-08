
public class OptimizedQueenify extends Queenify {
    @Override
    public void run() {
        Chessboard c = new Chessboard(chessboardSize);
        System.out.println();
        int failures = 0;
        while (!c.optimizedQueenify()) {
            failures++;
            //c.print();
            c.clear();
            //System.out.println("FAILURE n˚" + failures); // show failed attempts
        }
        if (showResult)
            c.print(); // show final result
        System.out.println("SUCCESS");
        System.out.println("FAILURES: " + failures);
    }

}