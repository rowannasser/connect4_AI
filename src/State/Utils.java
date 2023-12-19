package State;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Utils {
    public static boolean isFilledBoard(int[][] board) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    } public static void writeFile(boolean append, StringBuilder name) {
        FileWriter output;
        try {
            output = new FileWriter("Printing_Tree.txt", append);
            output.write(name.toString());
            output.flush();
            output.close();
        } catch (IOException e) {
            System.out.println("Not Saved file\n");
        }
    }

    public static void print(int[][] board, int currentDepth, int eval) {
        StringBuilder tree = new StringBuilder();
        String repeated=new String(new char[Math.max(0,currentDepth*3-1)]).replace("\0", "\t");;
        for (int i = board.length-1; i >=0 ; i--) {
            tree.append(repeated);
            tree.append(Arrays.toString(board[i]).replaceAll("1", "R").
                    replaceAll("0", "-").replaceAll("2", "Y").replaceAll(",",""));
            tree.append("\n");
        }
        tree.append(repeated).append(eval);
        if (currentDepth <= 0 || isFilledBoard(board)) {
            tree.append(repeated);
        }
        tree.append("\n");
        writeFile(true,tree);
    }
}