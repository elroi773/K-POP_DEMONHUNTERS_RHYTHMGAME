import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Golden {
    public void show(Stage primaryStage) {
        // 가운데 표시할 내용
        Text songTitle = new Text("🎵 Playing: Soda Pop 🎵");
        songTitle.setStyle("-fx-font-size: 20px; -fx-fill: white;");

        Button backBtn = new Button("뒤로 가기");
        Button startBtn = new Button("게임 시작");

        VBox centerBox = new VBox(20, songTitle, startBtn, backBtn);
        centerBox.setAlignment(Pos.CENTER);

        // BaseScene 이용해 Scene 생성
        Scene scene = BaseScene.createScene(
                "노래: Soda Pop",                // 상단 타이틀
                "../resources/img/sodapop_bg.png", // 배경 이미지
                800, 
                600, 
                centerBox                         // 가운데 컨텐츠
        );

        // 버튼 이벤트
        backBtn.setOnAction(e -> {
            new SelectSong().show(primaryStage); // 곡 선택 화면으로 돌아가기
        });

        startBtn.setOnAction(e -> {
            new GameScene().show(primaryStage); // 게임 화면으로 이동
        });

        // Stage에 Scene 적용
        primaryStage.setScene(scene);
    }
}
