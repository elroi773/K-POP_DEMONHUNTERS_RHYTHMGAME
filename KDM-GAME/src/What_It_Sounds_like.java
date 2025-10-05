import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class What_It_Sounds_like extends Application {
    @Override
    public void start(Stage stage) {
        VBox root = new VBox(20);
        root.setStyle("-fx-alignment: center; -fx-padding: 50;");
        Label label = new Label("What It Sounds Like Song Screen");
        Button back = new Button("Back");
        back.setOnAction(e -> {
            try {
                new SelectSong().start(new Stage());
                stage.close();
            } catch (Exception ex) { ex.printStackTrace(); }
        });
        root.getChildren().addAll(label, back);

        stage.setTitle("What It Sounds Like");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }
}
