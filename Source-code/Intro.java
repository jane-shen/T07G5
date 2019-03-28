import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import java.io.FileInputStream;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Intro extends Application{

  public static void main(String[] args){
    launch(args);
  }

  public void start(Stage stage) throws Exception{
    Rectangle WhiteBackground = new Rectangle(190,257,131,76);
    WhiteBackground.setFill(Color.WHITE);


    Node playButton;
    ImageView play = new ImageView(new Image("https://github.com/jshenny/T07G5/blob/master/PLAY.png?raw=true"));
    play.setFitHeight(70);
    play.setFitWidth(125);
    playButton = play;
    playButton.setTranslateX(193);
    playButton.setTranslateY(260);
    playButton.setOnMouseClicked(event -> System.out.println("HI"));


    stage.setTitle("Tetris");
    stage.setWidth(514);
    stage.setHeight(535);
    Pane pane = new Pane();

    Scene scene = new Scene(new Group());

    final ImageView image = new ImageView();
    Image tetrisImage = new Image("https://github.com/jshenny/T07G5/blob/master/TETROS.png?raw=true");
    image.setImage(tetrisImage);

    pane.getChildren().addAll(image,WhiteBackground, playButton);
    scene.setRoot(pane);
    stage.setScene(scene);
    stage.show();
  }
}
