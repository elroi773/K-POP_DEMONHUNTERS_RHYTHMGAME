import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;

public class SelectSong extends Application {

    private MediaPlayer currentPlayer;
    private String currentPreviewPath = "";

    // 곡 데이터 클래스
    private static class SongData {
        String title;
        String imagePath;
        String previewPath;
        Class<? extends Application> targetClass;

        SongData(String title, String imagePath, String previewPath, Class<? extends Application> targetClass) {
            this.title = title;
            this.imagePath = imagePath;
            this.previewPath = previewPath;
            this.targetClass = targetClass;
        }
    }

    // 곡 목록
    private final SongData[] songs = {
        new SongData("Free", "../resources/img/Free.png", "../resources/music/preview/Free_preview.mp3", Free.class),
        new SongData("Golden", "../resources/img/Golden.png", "../resources/music/preview/Golden_preview.mp3", Golden.class),
        new SongData("How Its Done", "../resources/img/How_Its_Done.png", "../resources/music/preview/HowItsdone_preview.mp3", How_Its_done.class),
        new SongData("Your Idol", "../resources/img/Your_idol.png", "../resources/music/preview/your_idol_preview.mp3", YourIdol.class),
        new SongData("What It Sounds Like", "../resources/img/What_It_Sounds_Like.png", "../resources/music/preview/what_it_sounds_like_preview.mp3", What_It_Sounds_like.class),
        new SongData("Soda Pop", "../resources/img/Soda_Pop.png", "../resources/music/preview/soda_pop_preview.mp3", SodaPop.class)
    };

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("노래 선택");

        GridPane grid = new GridPane();
        grid.setHgap(30);
        grid.setVgap(40);
        grid.setPadding(new Insets(50, 50, 50, 50));
        grid.setAlignment(Pos.CENTER);

        int col = 0, row = 0;
        for (SongData song : songs) {
            VBox songBox = createSongItem(song, primaryStage);
            grid.add(songBox, col, row);

            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }

        Scene scene = new Scene(grid, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createSongItem(SongData song, Stage stage) {
        ImageView imageView = new ImageView(new Image(new File(song.imagePath).toURI().toString()));
        imageView.setFitWidth(220);
        imageView.setFitHeight(220);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-cursor: hand;");

        // 이미지 클릭 → 미리듣기 토글
        imageView.setOnMouseClicked(e -> togglePreview(song.previewPath));

        Text title = new Text(song.title);
        title.setFont(Font.font("Arial", 18));

        Button selectButton = new Button("선택");
        selectButton.setFont(Font.font(14));
        selectButton.setOnAction(e -> {
            stopPreview();
            openSongStage(song.targetClass, stage);
        });

        VBox box = new VBox(10, imageView, title, selectButton);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-background-color: #f9f9f9; -fx-padding: 15; -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 5,0,0,2);");

        box.setOnMouseEntered(e -> box.setStyle("-fx-background-color: #f0f0ff; -fx-padding: 15; -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10,0,0,3);"));
        box.setOnMouseExited(e -> box.setStyle("-fx-background-color: #f9f9f9; -fx-padding: 15; -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 5,0,0,2);"));

        return box;
    }

    private void togglePreview(String path) {
        if (currentPlayer != null && path.equals(currentPreviewPath)) {
            stopPreview();
            return;
        }
        if (currentPlayer != null) currentPlayer.stop();

        Media media = new Media(new File(path).toURI().toString());
        currentPlayer = new MediaPlayer(media);
        currentPreviewPath = path;
        currentPlayer.play();
    }

    private void stopPreview() {
        if (currentPlayer != null) {
            currentPlayer.stop();
            currentPlayer = null;
            currentPreviewPath = "";
        }
    }

    private void openSongStage(Class<? extends Application> songClass, Stage currentStage) {
        try {
            Stage newStage = new Stage();
            songClass.getDeclaredConstructor().newInstance().start(newStage);
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
// javac --module-path "C:\javafx-sdk-17.0.16\lib" --add-modules javafx.controls,javafx.fxml,javafx.media -encoding UTF-8 -d ../out *.java
// java --module-path "C:\javafx-sdk-17.0.16\lib" --add-modules javafx.controls,javafx.fxml,javafx.media -cp "../out;../resources" SelectSong
