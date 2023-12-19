package Algorithms;

 import State.Heuristic;
 import State.State;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.Arrays;
 import java.util.LinkedHashMap;
 import static State.Utils.isFilledBoard;
 import static State.Utils.print;


public class MiniMaxWithoutPruning implements IAlgorithms {
    Heuristic heuristic = new Heuristic();
    int NodeExpanded;

    @Override
    public State getFinalDecision(State state, int k) {
        NodeExpanded = 0;
        try {
            new FileWriter("Printing_Tree.txt", false).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long startTime = System.currentTimeMillis();
        State res = Maximize(state, k);
        long endTime = System.currentTimeMillis();
        System.out.println("Nodes Expanded = " + this.NodeExpanded + " Time taken = " + (endTime - startTime));
        return res;
    }

    private State Maximize(State state, int k) { //AI which is 2
        NodeExpanded++;
        if (k <= 0 || isFilledBoard(state.getBoard())) {
            heuristic.EvaluateHeuristic(state);
            print(state.getBoard(), k, state.heuristic);
            return new State(null, state.heuristic, state.userScore, state.comScore);
        }
        State Max = new State(null, Integer.MIN_VALUE, state.userScore, state.comScore);
        for (State child : state.GetNeighbours(state, true)) {
            State childUtil = Minimize(child, (k - 1));
            int childHeuristic = childUtil.heuristic;
            if (childHeuristic > Max.heuristic) {
                Max = new State(child.copyBoard(child), childHeuristic, child.userScore, child.comScore);
            }
        }
        print(state.getBoard(), k, Max.heuristic);
        return Max;
    }

    private State Minimize(State state, int k) {
        NodeExpanded++;
        if (k <= 0 || isFilledBoard(state.getBoard())) {  //terminal state
            heuristic.EvaluateHeuristic(state);
            print(state.getBoard(), k, state.heuristic);
            return new State(null, state.heuristic, state.userScore, state.comScore);
        }
        State Min = new State(null, Integer.MAX_VALUE, state.userScore, state.comScore);
        for (State child : state.GetNeighbours(state, false)) {
            State childUtil = Maximize(child, k - 1);
            int childHeuristic = childUtil.heuristic;
            if (childHeuristic < Min.heuristic) {
                Min = new State(child.copyBoard(child), childHeuristic, child.userScore, child.comScore);
            }
        }
        print(state.getBoard(), k, Min.heuristic);
        return Min;
    }
}