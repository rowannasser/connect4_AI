import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static Stage stage;
    public static Scene[] loadedScenes;
    public static int[] scores;

    @Override
    public void start(Stage primaryStage) {
        loadedScenes = new Scene[2];
        scores = new int[2];
        scores[0] = 0;
        scores[1] = 0;
        Main.stage = primaryStage;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("connect 4");
            loadedScenes[0] = scene;
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            loadedScenes[1] = new Scene(FXMLLoader.load(getClass().getResource("board.fxml")));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}