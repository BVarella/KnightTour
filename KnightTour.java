import java.util.concurrent.ThreadLocalRandom;

class KnightTour {

    //Do not change. Intended to be 8x8 board only.
    private static final int BOARD_SIZE = 8;

    private static final int NOT_VISITED = -1;

    //Initial position. To be determined.
    private int initialX;
    private int initialY;

    private int numAttempts = 0;

    private int[][] solutionMatrix;

    //Possible Movements
    private int[] xMoves = { 2, 1, -1, -2, -2, -1,  1,  2 };
    private int[] yMoves = { 1, 2,  2,  1, -1, -2, -2, -1 };

    KnightTour(){

        this.solutionMatrix = new int[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
    }

    //Randomly choose a initial X and Y position.
    private void chooseInitialPosition(){

        initialX = ThreadLocalRandom.current().nextInt(0, 8);
        initialY = ThreadLocalRandom.current().nextInt(0, 8);

        System.out.println();
        System.out.println("Knight Tour 8x8");
        System.out.println("Starting at position: " + "["+(initialX+1)+"]"+"["+(initialY+1)+"]");

    }

    //Set every position of the board as NOT VISITED.
    private void initializeBoard(){

        for (int i = 0; i < BOARD_SIZE; ++i){
            for (int j = 0; j < BOARD_SIZE; ++j){
                this.solutionMatrix[i][j] = NOT_VISITED;
            }
        }
    }

    //Auxiliary function that calls solveProblem()
    void solveKnightTourProblem(){

        chooseInitialPosition();

        //Set the initial position as visited.
        this.solutionMatrix[initialX][initialY] = 0;

        if (!solveProblem(1,initialX,initialY)){
            System.out.println("Solution not found!");
            return;
        }
        showSolution();
    }

    //Knight tour problem solution using backtracking.
    private boolean solveProblem(int stepCount, int x, int y){

        //End if every position has been visited. Solution found.
        if (stepCount == BOARD_SIZE * BOARD_SIZE) return true;

        for (int i = 0; i < BOARD_SIZE; ++i){

            int nextX = x + xMoves[i];
            int nextY = y + yMoves[i];

            if (isValidMove(nextX, nextY)){

                this.solutionMatrix[nextX][nextY] = stepCount;

                if (solveProblem(stepCount+1, nextX, nextY)){
                    return true; //Keep going
                }
                this.solutionMatrix[nextX][nextY] = -1; //Backtrack
            }
        }
        return false;
    }

    private boolean isValidMove(int x, int y){

        numAttempts++;

        //Out of the board
        if (x < 0 || x >= BOARD_SIZE) return false;
        //Out of the board
        if (y < 0 || y >= BOARD_SIZE) return false;
        //Has been visited
        if (this.solutionMatrix[x][y] != NOT_VISITED) return false;

        return true;
    }

    private void showSolution(){

        System.out.println();

        for (int i = 0; i < BOARD_SIZE; ++i){
            for (int j = 0; j < BOARD_SIZE; ++j){
                String formatPosition = String.format("%4d", this.solutionMatrix[i][j]+1);
                System.out.print(formatPosition);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Number of Attempts: " + numAttempts);
    }
}
