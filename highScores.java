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
    stage.setWidth(600);
    stage.setHeight(800);
    Pane pane = new Pane();

    Scene scene = new Scene(new Group());
	
	//DONE BUTTON 
	Node okayButton;
    ImageView okay = new ImageView(new Image("https://github.com/jshenny/T07G5/blob/master/OKAY.png?raw=true"));
    okay.setFitHeight(70);
    okay.setFitWidth(125);
    okayButton = okay;
    okayButton.setTranslateX(220);
    okayButton.setTranslateY(620);
    okayButton.setOnMouseClicked(event -> System.out.println("OK"));
	
	

    final ImageView image = new ImageView();
    Image tetrisImage = new Image("https://github.com/jshenny/T07G5/blob/master/tetros%20high%20score.png?raw=true");
    image.setImage(tetrisImage);
	
	//new scorer
	Text texts = new Text();
	Font textFont = new Font("Courier New", 40);
	//int Scored = texts.score;
	texts.setText("FIRST PLACE:");
	// position
	texts.setX(100);
	texts.setY(350);
	texts.setFill(Color.WHITE);
	texts.setFont(textFont);

	
	Text secondHighest = new Text();
	secondHighest.setText("SECOND PLACE:");
	secondHighest.setFill(Color.WHITE);
	secondHighest.setFont(textFont);
	secondHighest.setX(100);
	secondHighest.setY(430);
	
	Text thirdHighest = new Text();
	thirdHighest.setText("THIRD PLACE:");
	thirdHighest.setFill(Color.WHITE);
	thirdHighest.setFont(textFont);
	thirdHighest.setX(100);
	thirdHighest.setY(510);
	
	Text firstScore = new Text();
	firstScore.setText("900");
	firstScore.setFill(Color.WHITE);
	firstScore.setFont(textFont);
	firstScore.setX(420);
	firstScore.setY(350);
	
	Text secondScore = new Text();
	secondScore.setText("700");
	secondScore.setFill(Color.WHITE);
	secondScore.setFont(textFont);
	secondScore.setX(420);
	secondScore.setY(430);
	
	Text thirdScore = new Text();
	thirdScore.setText("400");
	thirdScore.setFill(Color.WHITE);
	thirdScore.setFont(textFont);
	thirdScore.setX(420);
	thirdScore.setY(510);
	
    pane.getChildren().addAll(image, okayButton, texts, secondHighest, thirdHighest, firstScore, secondScore, thirdScore);
    scene.setRoot(pane);
    stage.setScene(scene);
    stage.show();
	
	
  }
}