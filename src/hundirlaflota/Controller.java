package hundirlaflota;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
	@FXML private Button start;
	private Main main;
	
	public void setMain(Main main){
		this.main = main;
	}
	
	
	@FXML protected void Start() throws IOException{
		this.main.StartGame();
	}
}
