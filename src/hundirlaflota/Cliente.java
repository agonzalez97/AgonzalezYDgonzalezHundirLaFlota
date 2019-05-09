 package hundirlaflota;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Cliente {

	private int barcos = 2;
	private Main main;
	@FXML private AnchorPane ap = new AnchorPane();
	@FXML private Text text = new Text();
	@FXML private Button btn0A = new Button();
	@FXML private Button btn0B = new Button();
	@FXML private Button btn0C = new Button();
	@FXML private Button btn0D = new Button();
	@FXML private Button btn0E = new Button();
	@FXML private Button btn1A = new Button();
	@FXML private Button btn1B = new Button();
	@FXML private Button btn1C = new Button();
	@FXML private Button btn1D = new Button();
	@FXML private Button btn1E = new Button();
	@FXML private Button btn2A = new Button();
	@FXML private Button btn2B = new Button();
	@FXML private Button btn2C = new Button();
	@FXML private Button btn2D = new Button();
	@FXML private Button btn2E = new Button();
	@FXML private Button btn3A = new Button();
	@FXML private Button btn3B = new Button();
	@FXML private Button btn3C = new Button();
	@FXML private Button btn3D = new Button();
	@FXML private Button btn3E = new Button();
	@FXML private Button btn4A = new Button();
	@FXML private Button btn4B = new Button();
	@FXML private Button btn4C = new Button();
	@FXML private Button btn4D = new Button();
	@FXML private Button btn4E = new Button();
	
	InetAddress serverIP;
    int serverPort;
    DatagramSocket socket;
    
	public void setMain(Main main){
		this.main = main;
		Cliente client = new Cliente();
        try {
            client.init("localhost",9999);

            socket = new DatagramSocket();
        } catch (IOException e) {
            e.getStackTrace();
        }
	}
	
	public void init(String host, int port) throws SocketException, UnknownHostException {
        serverIP = InetAddress.getByName(host);
        serverPort = port;
    }
	
	@FXML public void initialize(){
		
		btn0A.setOnAction((event) -> {
			buttonSelected(btn0A);
		});
		btn0B.setOnAction((event) -> {
			buttonSelected(btn0B);
		});
		btn0C.setOnAction((event) -> {
			buttonSelected(btn0C);
		});
		btn0D.setOnAction((event) -> {
			buttonSelected(btn0D);
		});
		btn0E.setOnAction((event) -> {
			buttonSelected(btn0E);
		});
		btn1A.setOnAction((event) -> {
			buttonSelected(btn1A);
		});
		btn1B.setOnAction((event) -> {
			buttonSelected(btn1B);
		});
		btn1C.setOnAction((event) -> {
			buttonSelected(btn1C);
		});
		btn1D.setOnAction((event) -> {
			buttonSelected(btn1D);
		});
		btn1E.setOnAction((event) -> {
			buttonSelected(btn1E);
		});
		btn2A.setOnAction((event) -> {
			buttonSelected(btn2A);
		});
		btn2B.setOnAction((event) -> {
			buttonSelected(btn2B);
		});
		btn2C.setOnAction((event) -> {
			buttonSelected(btn2C);
		});
		btn2D.setOnAction((event) -> {
			buttonSelected(btn2D);
		});
		btn2E.setOnAction((event) -> {
			buttonSelected(btn2E);
		});
		btn3A.setOnAction((event) -> {
			buttonSelected(btn3A);
		});
		btn3B.setOnAction((event) -> {
			buttonSelected(btn3B);
		});
		btn3C.setOnAction((event) -> {
			buttonSelected(btn3C);
		});
		btn3D.setOnAction((event) -> {
			buttonSelected(btn3D);
		});
		btn3E.setOnAction((event) -> {
			buttonSelected(btn3E);
		});
		btn4A.setOnAction((event) -> {
			buttonSelected(btn4A);
		});
		btn4B.setOnAction((event) -> {
			buttonSelected(btn4B);
		});
		btn4C.setOnAction((event) -> {
			buttonSelected(btn4C);
		});
		btn4D.setOnAction((event) -> {
			buttonSelected(btn4D);
		});
		btn4E.setOnAction((event) -> {
			buttonSelected(btn4E);
		});
		
	}
	
	
	private void buttonSelected(Button btn){
		
		byte [] receivedData = new byte[1024];
		
		byte [] id  = btn.getId().substring(3,btn.getId().length()).getBytes();
		try {
			serverIP = InetAddress.getByName("localhost");
			serverPort = 9999;
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		DatagramPacket packet = new DatagramPacket(id, id.length,serverIP,serverPort);
        try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
        packet = new DatagramPacket(receivedData,1024);
        try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        String msg = new String(packet.getData(),0,packet.getLength());
        
        if(msg.equals("Tocado")){
        	btn.setText("X");
			btn.setTextFill(Color.RED);
        }else if(msg.equals("Barco Hundido")){
        	btn.setText("X");
			btn.setTextFill(Color.RED);
			barcos -=1;
        }else{
        	btn.setText("O");
			btn.setTextFill(Color.BLUE);
        }

			 text.setText(new String(packet.getData()));
        
        //text.setText(new String(packet.getData()));
        
	    if (barcos ==0) {
			text.setText("Has Ganado");
			System.out.println(":D");
			Button restart = new Button();
			restart.setText("Reiniciar");
			restart.setLayoutX(972.0f);
			restart.setLayoutY(446.0f);
			
			restart.setOnAction((event)->{
				try {
					main.StartGame();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			ap.getChildren().add(restart);
			
			disableButtons();
			
		}
			 
		btn.setDisable(true);
	}
	
	public void disableButtons(){
		btn0A.setDisable(true);
		btn0B.setDisable(true);
		btn0C.setDisable(true);
		btn0D.setDisable(true);
		btn0E.setDisable(true);
		btn1A.setDisable(true);
		btn1B.setDisable(true);
		btn1C.setDisable(true);
		btn1D.setDisable(true);
		btn1E.setDisable(true);
		btn2A.setDisable(true);
		btn2B.setDisable(true);
		btn2C.setDisable(true);
		btn2D.setDisable(true);
		btn2E.setDisable(true);
		btn3A.setDisable(true);
		btn3B.setDisable(true);
		btn3C.setDisable(true);
		btn3D.setDisable(true);
		btn3E.setDisable(true);
		btn4A.setDisable(true);
		btn4B.setDisable(true);
		btn4C.setDisable(true);
		btn4D.setDisable(true);
		btn4E.setDisable(true);
	}
	
}
