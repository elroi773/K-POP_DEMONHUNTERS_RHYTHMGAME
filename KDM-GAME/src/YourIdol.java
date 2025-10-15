import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class YourIdol extends Application {
    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage stage) {
        // --- 배경 이미지 설정 ---
        Image backgroundImage = new Image(new File("../resources/img/game_Background/YourIdol_Background.png").toURI().toString());
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(400); // 창 크기에 맞게 조정
        backgroundView.setFitHeight(300);
        backgroundView.setPreserveRatio(false);

        // --- UI 구성 ---
        VBox content = new VBox(20);
        content.setStyle("-fx-alignment: center; -fx-padding: 50;");
        Label label = new Label("Your Idol Screen");
        Button back = new Button("Back");

        back.setOnAction(e -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop(); // 노래 정지
            }
            try {
                new SelectSong().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        content.getChildren().addAll(label, back);

        // --- 배경과 내용 겹치기 ---
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundView, content);

        // --- 노래 재생 ---
        try {
            Media media = new Media(new File("../resources/music/your_idol.mp3").toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // 반복재생
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // --- Stage 설정 ---
        stage.setTitle("YourIdol");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


// javac --module-path "C:\javafx-sdk-17.0.16\lib" --add-modules javafx.controls,javafx.media -encoding UTF-8 -d ../out YourIdol.java
// java --module-path "C:\javafx-sdk-17.0.16\lib" --add-modules javafx.controls,javafx.media -cp ../out YourIdol.java