import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomePage {
    @FXML
    private ComboBox<String> miniMaxButton;
    @FXML
    private ComboBox<String> color;
    @FXML
    private Text text;
    @FXML
    private TextField input;
     @FXML
    private void start() {
        int depth;
        depth = Integer.parseInt(input.getText());
        Board b= new Board();
        if(color.getValue()==null){color.setValue("Yellow");}
        if(miniMaxButton.getValue()==null){miniMaxButton.setValue("with alpha-beta pruning");}
        b.set(color.getValue(),miniMaxButton.getValue(),depth);
         try {
            if (depth >= 0) {
                text.setVisible(false);
                input.clear();
                Main.stage.setScene(Main.loadedScenes[1]);
              // Game game ;
                if (miniMaxButton.getValue() == null) miniMaxButton.setValue("Algorithms.MiniMaxWithPruning");
              // game = new Game(miniMaxButton.getValue(), depth,playerTurn);
             //  game.newGame();
               // Algorithms.MiniMaxWithPruning.de = depth;

            } else {
                text.setVisible(true);
                text.setText("Enter a valid number");
            }
        } catch (NumberFormatException e) {
            text.setVisible(true);
            text.setText("Enter a valid number");
            e.printStackTrace();
        }
         if (miniMaxButton.getValue() == null) {
             miniMaxButton.setValue("Algorithms.MiniMaxWithPruning");
         }
         input.clear();

     }
    @FXML
    private void exit() {
        System.exit(0);
    }
}
