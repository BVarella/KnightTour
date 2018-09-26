public class Main {

    public static void main(String[] args) {

        //Used to measure execution time.
        long startTime = System.nanoTime();

        //Start solving the problem.
        KnightTour knightTour = new KnightTour();
        knightTour.solveKnightTourProblem();

        //Print execution time.
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;
        System.out.println("Running time: "+duration+" milliseconds");
        System.out.println();
    }
}
