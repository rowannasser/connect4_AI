package State;

public class Heuristic {
    ///the expected number of 4 pieces that can connected in each place
    int[][] ExpectedHeuristic = { {3,4,5,7,5,4,3},
            {4,6,8,10,8,6,4},
            {5,8,11,13,11,8,5},
            {5,8,11,13,11,8,5},
            {4,6,8,10,8,6,4},
            {3,4,5,7,5,4,3} };

    public void EvaluateHeuristic(State state){
        int utility = 138;  // sum of all numbers in the expectedHeuristic =276 ,so ( 2*138=276 )
        int res = 0;
        for(int i = 0;i < ExpectedHeuristic.length; ++i){
            for(int j = 0; j < ExpectedHeuristic[0].length; ++j){
                if(state.getElement(i,j) == 2){     ///Ai
                    res += (ExpectedHeuristic[i][j] * state.comScore);
                }
                else if(state.getElement(i,j) == 1){   ///user
                    res -= (ExpectedHeuristic[i][j]  * state.userScore);
                }
            }
        }
        // her > 0 if  red is likely to win
        // her < 0 if  red is likely to  lose  >>> yellow is likely to win
        // her == 0  if  tie
        state.heuristic = utility + res ;
    }
}