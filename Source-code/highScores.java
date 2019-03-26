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
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class highScores extends Application{
	Board board = new Board();
    Shape shape = new Shape();
	//board.checkFullRow();
	//board.checkFullRow();
  public static void main(String[] args){
    launch(args);
  }

  public void start(Stage stage) throws Exception{
 	
    stage.setTitle("Score Board");
    stage.setWidth(514);
    stage.setHeight(535);
    Pane pane = new Pane();

    Scene scene = new Scene(new Group());

    final ImageView image = new ImageView();
    Image tetrisImage = new Image("https://github.com/jshenny/T07G5/blob/master/tetros%20high%20score.png?raw=true");
    image.setImage(tetrisImage);
	
	//new scorer
	Text texts = new Text();
	Font textFont = new Font("Stencil", 24);
	//int Scored = texts.score;
	texts.setText("FIRST PLACE:");
	// position
	texts.setX(80);
	texts.setY(290);
	texts.setFill(Color.WHITE);
	texts.setFont(textFont);
	//Text highestScorersScore = new Text("300");
	//highestScorersScore.setFill(Color.BLACK);
	//highestScorersScore.setFont(textFont);
	//textFlow.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
	Text secondHighest = new Text();
	secondHighest.setText("SECOND PLACE:");
	secondHighest.setFill(Color.WHITE);
	secondHighest.setFont(textFont);
	secondHighest.setX(80);
	secondHighest.setY(370);
	
	Text thirdHighest = new Text();
	thirdHighest.setText("THIRD PLACE:");
	thirdHighest.setFill(Color.WHITE);
	thirdHighest.setFont(textFont);
	thirdHighest.setX(80);
	thirdHighest.setY(450);
	
    pane.getChildren().addAll(image, texts, secondHighest, thirdHighest);
    scene.setRoot(pane);
    stage.setScene(scene);
    stage.show();
	
	
  }
}