import java.util.*;

public class ConnectFourGame {
    
    // represents if the game is over yet
    boolean isWon = false;

    // A GameBoard is a 6x7 2D array of ints, where each index
    // is one of:
    // - 0
    // - 1
    // - 2
    // represents the moves made in a Connect 4 Game
    // initializes as all zeroes (all empty)
    int[][] board = new int[6][7];

    // A PlayerMove is one of:
    // - 1
    // - 2
    // represents the player whose turn it is.
    int playerMove = 1;

    // represents the last move made
    int[] lastMove = new int[2];

    public ConnectFourGame() {
        // doesn't need to do anything
    }

    // makeMove : Int -> Boolean
    // adds PlayerMove to the given column, and returns
    // whether it was successful or not
    public boolean makeMove(int col) {
        if (board[0][col] != 0) {
            return false;
        } else {
            fall(col, playerMove);
            isWon = checkForWin();
            changePlayer();
            return true;
        }
    }
    
    // fall : Int Int -> Void
    // places move into lowest position 
    public void fall(int col, int player) {
        // improve later with binary search for zero
        boolean notHit = true;
        for (int i = 0; (i < 5 && notHit); i++) {
            if (board[i+1][col] != 0) {
                notHit = false;
                board[i][col] = playerMove;
                lastMove[0] = i;
                lastMove[1] = col;
            }
        }
        if (notHit) {
            board[5][col] = playerMove;
            lastMove[0] = 5;
            lastMove[1] = col;
        }
    }

    // checkForWin() : Void -> boolean
    // Are there 4 consecutively touching pieces from one player?
    public boolean checkForWin() {
        return checkVertical() || checkHorizontal() || checkUpDiagonal() || checkDownDiagonal();
    }

    public boolean checkVertical() {
        int count = 0;
        for (int hole = 0; hole < 6; hole++) {
            if (board[hole][lastMove[1]] == playerMove) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    public boolean checkHorizontal() {
        int count = 0;
        for (int hole : board[lastMove[0]]) {
            if (hole == playerMove) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    public boolean checkUpDiagonal() {
        int count = 0;
        for (int i = -3; i < 4; i++) {
            int row = lastMove[0] + i;
            int col = lastMove[1] - i;
            if (row < 0 || row > 5 || col < 0 || col > 6) {

            } else {
                if (board[row][col] == playerMove) {
                    count++;
                    if (count == 4) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    public boolean checkDownDiagonal() {
        int count = 0;
        for (int i = -3; i < 4; i++) {
            int row = lastMove[0] + i;
            int col = lastMove[1] + i;
            if (row < 0 || row > 5 || col < 0 || col > 6) {

            } else {
                if (board[row][col] == playerMove) {
                    count++;
                    if (count == 4) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    public int changePlayer() {
        if (playerMove == 1) {
            playerMove = 2;
            return playerMove;
        } else {
            playerMove = 1;
            return playerMove;
        }
    }

    @Override
    // toString() : Void -> String
    // returns a string representation of the board
    public String toString() {
        String result = "{\n";
        for (int[] row : board) {
            result += "\t" + Arrays.toString(row) + "\n";
        }
        result += "}";
        return result;
    }
}