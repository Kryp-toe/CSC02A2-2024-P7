import acsse.csc2a.fmb.gui.FireworkDisplayPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
	private FireworkDisplayPane pane = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage)
	{
		//Create pane instance
		pane = new FireworkDisplayPane(primaryStage);
		
		// Create the Scene
		Scene scene = new Scene(pane);
		
		//set stage width and height
		primaryStage.setWidth(1250);
		primaryStage.setHeight(800);
		
		// Set the Scene
		primaryStage.setScene(scene);
		primaryStage.setTitle("P07");
		
		//show the stage
		primaryStage.show();
	}
}