
public class Queenify implements Runnable {

    public void run() {
        Chessboard c = new Chessboard(chessboardSize);
        System.out.println();
        int failures = 0;
        while (!c.queenify()) {
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

    public void setSize(int size) {
        chessboardSize = size;
    }
    public void setShowResult(boolean choice) { showResult = choice; }
    protected int chessboardSize = 0;
    protected boolean showResult = false;

}
