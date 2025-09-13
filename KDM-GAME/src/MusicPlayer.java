import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicPlayer {
    private static MediaPlayer mediaPlayer;

    // 음악 재생
    public static void play(String filePath, boolean loop) {
        stop(); // 기존 재생 중인 음악 정지

        try {
            Media media = new Media(new File(filePath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            if (loop) {
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // 무한 반복
            }

            mediaPlayer.setVolume(0.5); // 기본 볼륨
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("음악 파일을 재생할 수 없습니다: " + filePath);
        }
    }

    // 정지
    public static void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    // 일시 정지
    public static void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    // 다시 재생
    public static void resume() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    // 볼륨 조절
    public static void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }
}
