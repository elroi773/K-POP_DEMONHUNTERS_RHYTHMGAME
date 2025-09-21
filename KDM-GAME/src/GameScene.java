import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class GameScene {
    public void show(Stage primaryStage) {
        StackPane root = new StackPane();
        root.getChildren().add(new Text("게임 화면"));

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
