import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // 레이아웃
        BorderPane root = new BorderPane();

        // 배경 이미지
        BackgroundImage bgImage = new BackgroundImage(
                new Image("file:../resources/img/background.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(800, 600, false, false, false, false)
        );
        root.setBackground(new Background(bgImage));

        // 타이틀 텍스트
        Text title = new Text("K-POP DEMON HUNTERS - Rhythm Game");
        title.setFill(Color.DEEPPINK);
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // 제목 가운데 정렬
        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);
        root.setTop(titleBox);

        // 버튼
        Button selectSongBtn = new Button("게임 시작");
        selectSongBtn.setStyle("-fx-font-size: 18px; -fx-background-color: pink;");

        // 버튼 아래쪽 가운데 정렬
        HBox buttonBox = new HBox(selectSongBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setStyle("-fx-padding: 20px;"); // 버튼과 화면 끝 여백
        root.setBottom(buttonBox);

        // 버튼 이벤트 → SelectSong 화면으로 전환
        selectSongBtn.setOnAction(e -> {
            new SelectSong().show(primaryStage);
        });

        // 씬
        Scene scene = new Scene(root, 800, 600);

        // 윈도우 설정
        primaryStage.setTitle("KDM Rhythm Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        // OST 재생
        MusicPlayer.play("../resources/music/HowIt'sdone.mp3", true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
