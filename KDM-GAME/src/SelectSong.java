import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SelectSong {
    public void show(Stage stage) {
        BorderPane root = new BorderPane();

        // 뒤로가기 버튼
        Button backBtn = new Button("뒤로가기");
        root.setCenter(backBtn);

        backBtn.setOnAction(e -> {
            try {
                new Main().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // 배경 이미지 (classpath 기반)
        BackgroundImage bgImage = new BackgroundImage(
                new Image(getClass().getResource("/img/Select_song_background.png").toExternalForm()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(800, 600, false, false, false, false)
        );
        root.setBackground(new Background(bgImage));

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Select Song");
        stage.setScene(scene);
        stage.show();
    }
}
