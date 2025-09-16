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
import javafx.util.Duration;

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
            System.out.println("배경 이미지 로드 실패: " + e.getMessage());
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

        // 🎵 샘플 곡 2개 (파일명 안전하게, 경로는 resources 기준으로)
        ImageView song1Img = createSongImage("/img/Golden.png", "/music/preview/Golden_preview.mp3");
        ImageView song2Img = createSongImage("/img/Howitsdone.png", "/music/preview/Howitsdone_preview.mp3");
        ImageView song3Img = createSongImage("/img/Howitsdone.png", "/music/preview/Howitsdone_preview.mp3");

        songBox.getChildren().addAll(song1Img, song2Img,song3Img);
        root.setCenter(songBox);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Select Song Simple");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 이미지 클릭 시 지정 구간만 재생하는 메서드
     * @param imagePath 이미지 경로
     * @param musicPath 음악 파일 경로
     * @param highlightStart 하이라이트 시작 시간(초)
     * @param highlightEnd 하이라이트 종료 시간(초)
     * @return ImageView
     */
    private ImageView createSongImage(String imagePath, String musicPath, double highlightStart, double highlightEnd) {
        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        imageView.setOnMouseClicked(e -> {
            stopCurrentPlayer(); // 이전 재생 종료
            Media media = new Media(getClass().getResource(musicPath).toExternalForm());
            currentPlayer = new MediaPlayer(media);

            Duration start = Duration.seconds(highlightStart);
            Duration end = Duration.seconds(highlightEnd);

            // MediaPlayer 준비 완료 후 실행
            currentPlayer.setOnReady(() -> {
                if (currentPlayer == null) return; // 안전 체크
                currentPlayer.seek(start);
                currentPlayer.play();

                // 재생 구간 체크
                currentPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
                    if (newTime.greaterThanOrEqualTo(end)) {
                        stopCurrentPlayer();
                    }
                });
            });

            // 혹시 준비 실패 시
            currentPlayer.setOnError(() -> {
                System.out.println("MediaPlayer Error: " + currentPlayer.getError());
            });
        });

        return imageView;
    }

    /**
     * 오버로딩: 하이라이트 구간 기본값 (30초 ~ 45초)
     */
    private ImageView createSongImage(String imagePath, String musicPath) {
        return createSongImage(imagePath, musicPath, 30, 45);
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
