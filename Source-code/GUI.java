import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class GUI extends Application{
  public static void main(String[] args){
    launch(args);
  }

    public void start(Stage stage)throws Exception{
      Pane pane = new Pane();
      Scene scene = new Scene(pane, 300, 480);
      scene.setFill(Color.BLACK);



      Rectangle rectangle = new Rectangle(0, 0, 20, 20);
      rectangle.setFill(Color.WHITE);

      Rectangle r1 = new Rectangle(0,0,200,320);
      r1.setStroke(Color.WHITE);
      r1.setFill(null);
      r1.setStrokeWidth(3);


      pane.getChildren().addAll(rectangle, r1);
      stage.setScene(scene);
      stage.setTitle("Tetris");
      stage.show();


  }
}
