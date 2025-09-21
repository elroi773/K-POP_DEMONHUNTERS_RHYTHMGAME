import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class BaseScene {
    public static Scene createScene(String titleText, String bgPath, int width, int height, Pane centerContent) {
        BorderPane root = new BorderPane();

        // 배경 이미지
        BackgroundImage bgImage = new BackgroundImage(
                new Image("file:" + bgPath),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(width, height, false, false, false, false)
        );
        root.setBackground(new Background(bgImage));

        // 타이틀
        Text title = new Text(titleText);
        title.setFill(Color.DEEPPINK);
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);
        root.setTop(titleBox);

        // 가운데 내용 삽입
        if (centerContent != null) {
            root.setCenter(centerContent);
        }

        return new Scene(root, width, height);
    }
}
