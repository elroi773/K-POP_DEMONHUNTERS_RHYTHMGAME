import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 레이아웃
        BorderPane root = new BorderPane();

        // 타이틀 텍스트
        Text title = new Text("K-POP DEMON HUNTERS - Rhythm Game");
        title.setFill(Color.DEEPPINK);
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        root.setCenter(title);

        // 씬
        Scene scene = new Scene(root, 800, 600);

        // 윈도우 설정
        primaryStage.setTitle("KDM Rhythm Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        // TODO: MusicPlayer 실행 → OST 재생
        // TODO: GameController 호출 → 게임 시작
    }

    public static void main(String[] args) {
        launch(args); // JavaFX 앱 실행
    }
}
