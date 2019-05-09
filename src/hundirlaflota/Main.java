package hundirlaflota;
	
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
	private Stage primaryStage;
	private SplitPane splitPane;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Hundir La Flota");
		initGame();
	}
	
	
	
	public void initGame() throws IOException{
		//Carga el menu principal
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/Tablero.fxml"));
		splitPane = (SplitPane) loader.load();
		
		Controller controller = loader.getController();
		controller.setMain(this);
		primaryStage.setScene(new Scene(splitPane));
		primaryStage.show();
	    
	}
	
	public void StartGame() throws IOException{
		//Empieza el juego
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/Battleship.fxml"));
		splitPane = (SplitPane) loader.load();
		
		Cliente battleship = loader.getController();
		primaryStage.setScene(new Scene(splitPane));
		primaryStage.show();
		battleship.setMain(this);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
