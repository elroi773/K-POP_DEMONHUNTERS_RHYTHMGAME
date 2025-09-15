import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class SelectSongSimple extends Application {

    private MediaPlayer currentPlayer; // 현재 재생 중인 곡

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        // 배경 이미지
        try {
            Image bgImg = new Image(getClass().getResource("/img/Select_song_background.png").toExternalForm());
            BackgroundImage bgImage = new BackgroundImage(
                    bgImg,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(800, 600, false, false, false, false)
            );
            root.setBackground(new Background(bgImage));
        } catch (Exception e) {
            System.out.println("배경 이미지 로드 실패");
        }

        // 뒤로가기 버튼
        Button backBtn = new Button("뒤로가기");
        backBtn.setStyle("-fx-font-size: 16px;");
        root.setBottom(backBtn);
        BorderPane.setAlignment(backBtn, Pos.CENTER);
        backBtn.setOnAction(e -> stopCurrentPlayer());

        // 곡 선택 이미지 박스
        HBox songBox = new HBox(30);
        songBox.setAlignment(Pos.CENTER);

        // 샘플 곡 2개
        ImageView song1Img = createSongImage("/img/Golden.png", "/music/Golden.mp3");
        ImageView song2Img = createSongImage("/img/HowItsDone.png", "/music/HowItsDone.mp3");

        songBox.getChildren().addAll(song1Img, song2Img);
        root.setCenter(songBox);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Select Song Simple");
        stage.setScene(scene);
        stage.show();
    }

    // 이미지 클릭하면 바로 재생
    private ImageView createSongImage(String imagePath, String musicPath) {
        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        imageView.setOnMouseClicked(e -> {
            stopCurrentPlayer(); // 이전 재생 종료
            Media media = new Media(getClass().getResource(musicPath).toExternalForm());
            currentPlayer = new MediaPlayer(media);
            currentPlayer.play(); // 바로 재생
        });

        return imageView;
    }

    private void stopCurrentPlayer() {
        if (currentPlayer != null) {
            currentPlayer.stop();
            currentPlayer.dispose();
            currentPlayer = null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
