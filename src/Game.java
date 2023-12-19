import Algorithms.MiniMaxWithPruning;
import Algorithms.MiniMaxWithoutPruning;
import State.State;

import java.util.Scanner;

public class Game {
    MiniMaxWithoutPruning withoutPru;
    MiniMaxWithPruning withPru;
    String method;
    private State state;
    private boolean PlayerTurn;
    int PlayerColor;
    int ExpandedNodes;

    Game(String m, int k,boolean playerTurn){
        withoutPru = new MiniMaxWithoutPruning();
        withPru = new MiniMaxWithPruning();
        method = m;
        state = new State(new int[6][7] , 0  , 0 , 0);
        this.PlayerTurn = playerTurn;
        this.PlayerColor = 1;
        this.ExpandedNodes = k;

    }

    void newGame(){
        Scanner sc=new Scanner(System.in);
        while(!end()){
            if (this.PlayerTurn){
                System.out.print("Enter your play : ");
                String play = sc.nextLine();  //1a  1b  1c 1d ....
                int row = play.charAt(0) - 49;
                int col = play.charAt(1) - 97;
                state.setElement(row, col, PlayerColor);
                this.PlayerTurn = false;
                System.out.println(state.printBoard(state));

            }
            else {
                System.out.println("After AI plays : ");
                if (method.equals("MiniMaxWithoutPruning")) {
                    state = withoutPru.getFinalDecision(state, this.ExpandedNodes);
                } else {
                    state = withPru.getFinalDecision(state, this.ExpandedNodes);

                }
                this.PlayerTurn = true;
                System.out.println(state.printBoard(state));
            }

        }
        if (state.comScore > state.userScore){
            System.out.println("You lost....");
        }else{
            System.out.println("You win...");
        }
    }

    boolean end(){
        int max = 6;
        for(int i = 0; i < max; i++){
            if(state.getLastFilled(state.getBoard(), i) < max - 1){
                return false;
            }
        }
        return true;
    }

}