package State;

import java.util.LinkedHashSet;

public class State {
    State parent;
    public int[][] board;
    private final int boardRow=6, boardCol =7;
    public int heuristic;
    public int userScore;
    public int comScore;

    public State(int[][] b, int heu, int UScore, int Cscore) {
        board = b;
        parent = null;
        heuristic = heu;
        userScore = UScore;
        comScore = Cscore;
    }

    public LinkedHashSet<State> GetNeighbours(State s, boolean max) {
        LinkedHashSet<State> neighbours = new LinkedHashSet<>();
        for (int i = 0; i < s.board[0].length; ++i) {
            int x = getLastFilled(s.board, i);
            if (x < s.board.length) {
                State child = new State(copyBoard(s), s.heuristic, s.userScore, s.comScore);
                if (max) {
                    child.board[x][i] = 2;
                } else {
                    child.board[x][i] = 1;
                }
                child.parent = s;
                child.userScore = connect4(child.board, 1);
                child.comScore = connect4(child.board, 2);
                neighbours.add(child);
            }
        }
         return neighbours;
    }

    public String printBoard(State s) {
        StringBuilder stateBoard = new StringBuilder();
        for (int i = s.board.length - 1; i >= 0; i--) {
            for (int j = 0; j < s.board[0].length; j++) {
                if (s.board[i][j] == 0) {
                    stateBoard.append("_ ");
                } else if (s.board[i][j] == 1) {
                    stateBoard.append("R ");
                } else {
                    stateBoard.append("Y ");
                }
            }
            stateBoard.append("\n");
        }
        stateBoard.append("User: ").append(s.userScore).append("\n");
        stateBoard.append("Computer: ").append(s.comScore).append("\n");
        return stateBoard.toString();
    }


    public int getLastFilled(int[][] board, int col) {
        int i = board.length - 1;
        while (i >= 0 && board[i][col] == 0) {
            i--;
        }
        return ++i;
    }

    public int[][] copyBoard(State s) {
        int[][] array = new int[boardRow][];
        for (int i = 0; i < array.length; i++) {
            array[i] = new int[boardCol];
            System.arraycopy(s.board[i], 0, array[i], 0, array[i].length);
        }
        return array;
    }

    private int connect4(int[][] board, int val) {
        int count4 = 0;

        // vertical
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < boardCol; j++) {
                if (board[i][j] == val && board[i + 1][j] == val &&
                        board[i + 2][j] == val && board[i + 3][j] == val) {
                    count4++;
                }
            }
        }
        // horizontal
        for (int i = 0; i < boardRow; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == val && board[i][j + 1] == val &&
                        board[i][j + 2] == val && board[i][j + 3] == val) {
                    count4++;
                }
            }
        }
        //  diagonal ( \ )
        for (int i = 3; i < boardRow; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == val && board[i - 1][j + 1] == val &&
                        board[i - 2][j + 2] == val && board[i - 3][j + 3] == val) {
                    count4++;
                }
            }
        }
        //  diagonal ( / )
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == val && board[i + 1][j + 1] == val &&
                        board[i + 2][j + 2] == val && board[i + 3][j + 3] == val) {
                    count4++;
                }
            }
        }
        return count4;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getElement(int row, int col) {
        return board[row][col];
    }

    public void setElement(int row, int col, int playerPiece) {
        board[row][col] = playerPiece;
    }
}