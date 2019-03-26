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

public class Rules extends Application{
	Board board = new Board();
    Shape shape = new Shape();
	
  public static void main(String[] args){
    launch(args);
  }

  public void start(Stage stage) throws Exception{
	  
	//OK button
	 Node okayButton;
    ImageView okay = new ImageView(new Image("https://github.com/jshenny/T07G5/blob/master/OKAY.png?raw=true"));
    okay.setFitHeight(70);
    okay.setFitWidth(125);
    okayButton = okay;
    okayButton.setTranslateX(220);
    okayButton.setTranslateY(620);
    okayButton.setOnMouseClicked(event -> System.out.println("OK"));

 	
    stage.setTitle("Tetros Rules");
    stage.setWidth(600);
    stage.setHeight(800);
    Pane pane = new Pane();

    Scene scene = new Scene(new Group());

    final ImageView image = new ImageView();
    Image tetrisImage = new Image("https://github.com/jshenny/T07G5/blob/master/tetros%20rules.png?raw=true");
    image.setImage(tetrisImage);
	
	//new scorer
	Text texts = new Text();
	Font ruleFont = new Font("Courier New" , 18);
	
	
	Text firstRule = new Text();
	firstRule.setText("Press spacebar to respawn a random tetromino");

	firstRule.setFill(Color.WHITE);
	firstRule.setFont(ruleFont);
	firstRule.setX(70);
	firstRule.setY(200);
	
	Text secondRule = new Text();
	secondRule.setText("Press the down arrow to move down");
	secondRule.setFill(Color.WHITE);
	secondRule.setFont(ruleFont);
	secondRule.setX(70);
	secondRule.setY(270);
	
	Text thirdRule = new Text();
	thirdRule.setText("Press left arrow to move left");
	thirdRule.setFill(Color.WHITE);
	thirdRule.setFont(ruleFont);
	thirdRule.setX(70);
	thirdRule.setY(340);
	
	Text fourthRule = new Text();
	fourthRule.setText("Press right arrow to move right");
	fourthRule.setFill(Color.WHITE);
	fourthRule.setFont(ruleFont);
	fourthRule.setX(70);
	fourthRule.setY(410);
	
	Text fifthRule = new Text();
	fifthRule.setText("Press 'w' to rotate left");
	fifthRule.setFill(Color.WHITE);
	fifthRule.setFont(ruleFont);
	fifthRule.setX(70);
	fifthRule.setY(480);
	
	Text sixthRule = new Text();
	sixthRule.setText("Press 'e' to rotate right");
	sixthRule.setFill(Color.WHITE);
	sixthRule.setFont(ruleFont);
	sixthRule.setX(70);
	sixthRule.setY(550);
	
	
    pane.getChildren().addAll(image, okayButton, firstRule,secondRule, thirdRule, fourthRule, fifthRule, sixthRule);
    scene.setRoot(pane);
    stage.setScene(scene);
    stage.show();
	
	
  }
}