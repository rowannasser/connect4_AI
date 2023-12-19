package Algorithms;

import State.Heuristic;
import State.State;

import java.io.FileWriter;
import java.io.IOException;

import static State.Utils.isFilledBoard;
import static State.Utils.print;

public class MiniMaxWithPruning implements IAlgorithms{
    int NodeExpanded = 0;

    Heuristic heur = new Heuristic();
    @Override
    public State getFinalDecision(State state, int k) {
        try {
            new FileWriter("Printing_Tree.txt", false).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long startTime = System.currentTimeMillis();
        State res =  Maximize(state , Integer.MIN_VALUE , Integer.MAX_VALUE , k );
        long endTime = System.currentTimeMillis();
        System.out.println("Nodes Expanded = " + this.NodeExpanded + " Time taken = " + (endTime - startTime));
        return res;

    }

    private State Maximize(State state, int alpha , int beta , int k){
        NodeExpanded++;
        if(  k<=0  || isFilledBoard(state.getBoard())){  //terminal state
            heur.EvaluateHeuristic(state);
            print(state.getBoard(), k, state.heuristic);
            return new State(null , state.heuristic , state.userScore , state.comScore);
        }
        print(state.getBoard(), k, state.heuristic);
        State Max =new State(null , Integer.MIN_VALUE , state.userScore , state.comScore);

        for(State child: state.GetNeighbours(state,true)){
            State childUtil = Minimize(child , alpha , beta , k-1);
            int chUtility = childUtil.heuristic;
            if(chUtility > Max.heuristic){
                Max = new State(child.copyBoard(child),chUtility , child.userScore , child.comScore);
            }
            if(Max.heuristic >= beta ){
                break;
            }
            if(Max.heuristic > alpha){
                alpha = Max.heuristic;
            }
        }
        return Max;
    }

   private State Minimize(State state, int alpha, int beta, int k) {
        NodeExpanded++;
        if( k<=0 || isFilledBoard(state.getBoard())){  //terminal state
            heur.EvaluateHeuristic(state );
            print(state.getBoard(), k, state.heuristic);
            return new State(null , state.heuristic , state.userScore , state.comScore);
        }
        print(state.getBoard(), k, state.heuristic);
        State Min =new State(null , Integer.MAX_VALUE , state.userScore , state.comScore);
        for(State child: state.GetNeighbours(state,false)){
            State childUtil = Maximize(child , alpha , beta , k-1);
            int chUtility = childUtil.heuristic;

            if(chUtility < Min.heuristic){
                Min = new State(child.copyBoard(child),chUtility , child.userScore , child.comScore);
            }
            if(Min.heuristic <= alpha){
                break;
            }
            if(Min.heuristic < beta){
                beta = Min.heuristic;
            }
        }
        return Min;
    }

}