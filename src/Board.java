import Algorithms.MiniMaxWithPruning;
import Algorithms.MiniMaxWithoutPruning;
import State.State;
import State.Utils;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Board {

    @FXML
    private Circle turnColor;
    @FXML
    private Label turnName;
    @FXML
    private HBox boardBox;
    @FXML
    public Label pl;
    @FXML
    public   Label AI;
    @FXML
    private Label winner;

    static int depth;
    static String method;
    private String color;
    static Color pColor,aiColor;
    private int width;
    private int height;
    private boolean notFull = false;
    private Color yellow = Color.rgb(255, 255, 0), red = Color.rgb(255, 10, 29);
    private String player = "Player", ai = "AI";
    private State bo = new State(new int[height][width],0,0,0);


    public void set(String c, String m, int d){
    color = c ; method = m; depth = d;
    if(color.equals("Red")){ pColor= red; aiColor=yellow; }
    else{pColor=yellow; aiColor=red;}
    }

    public void initialize(){
        width = boardBox.getChildren().size();
        height = ((VBox)boardBox.getChildren().get(0)).getChildren().size();
        bo.board=new int[height][width];

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++) {
                bo.board[i][j] = 0;
            }
        }

        turnName.setText(player);
        pl.setText("0");
        AI.setText("0");
    }


    @FXML
    private void selectColumn(Event event){

        boolean notFound = true;
        int w = boardBox.getChildren().indexOf(event.getSource());
        int h;
        for(h=0; h<height; h++){
            if(bo.board[h][w] == 0){
                notFound = false;
                bo.board[h][w] = 1;
                notFull = true;
                break;
            }
           // else{selectColumn();}
        }
        if(notFound){
            notFull = false;
            return;
        }
        pl.setText(String.valueOf(bo.userScore));
        AI.setText(String.valueOf(bo.comScore));
        ((Circle)((VBox) event.getSource()).getChildren().get(5-h)).setFill(pColor);
        turnColor.setFill(aiColor);
        turnName.setText(ai);
        event.consume();
    }

    MiniMaxWithoutPruning withoutPru = new MiniMaxWithoutPruning();
    MiniMaxWithPruning withPru= new MiniMaxWithPruning();

    State next= new State(new int[height][width],0,0,0);

    @FXML
    private void AiTurn(){
        if(!notFull) {return;}
        boolean found = false;
        if(method.equals("without alpha-beta pruning")){ next = withoutPru.getFinalDecision(bo,depth);}
        else{ next = withPru.getFinalDecision(bo, depth);}

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(next.board[i][j] != bo.board[i][j]){
                    VBox col = (VBox)boardBox.getChildren().get(j);
                    ((Circle)col.getChildren().get(5-i)).setFill(aiColor);
                    bo=next;
                    bo.board[i][j]=2;
                    found = true;
                    break;
                }
            }
            if(found){break;}
        }

        turnColor.setFill(pColor);
        turnName.setText(player);
        notFull = false;
        pl.setText(String.valueOf(bo.userScore));
        AI.setText(String.valueOf(bo.comScore));
        if(Utils.isFilledBoard(bo.board)){ End();}
    }

    private void End(){
        pl.setText(String.valueOf(bo.userScore));
        AI.setText(String.valueOf(bo.comScore));
        if(bo.comScore > bo.userScore){winner.setText("Computer");}
        else if(bo.userScore > bo.comScore){winner.setText("Player");}
    }

    @FXML
    private void restart(){
        Main m = new Main();
        Stage primaryStage = m.stage ;
        m.start(primaryStage);
        pl.setText(String.valueOf(0));
        AI.setText(String.valueOf(0));
        notFull = false;
        }
}



