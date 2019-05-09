package hundirlaflota;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

	DatagramSocket socket;
	 static int x=5;
	    static int y=5;
	    static List<Barco> barcos;
	    static String l;
	    static int n;
	    static String s;
	    static ArrayList<String> letras2;
	    static String [] letras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
	    		"K", "L", "M","N","O","P","Q","R","S","T","U","V","W", "X","Y","Z" };

	public void init(int port) throws SocketException {
		socket = new DatagramSocket(port);
	}

	public void runServer() throws IOException {
		byte [] receivingData = new byte[1024];
		byte [] sendingData;
		InetAddress clientIP;
		int clientPort;

		while(true) {
			DatagramPacket packet = new DatagramPacket(receivingData,1024);
			socket.receive(packet);
			sendingData = processData(packet.getData(),packet.getLength());
			clientIP = packet.getAddress();
			clientPort = packet.getPort();
			packet = new DatagramPacket(sendingData,sendingData.length,clientIP,clientPort);
			socket.send(packet);
		}
	}

	private byte[] processData(byte[] data, int lenght) {
        String btn = new String(data,0,lenght);
        boolean tocado=false;
        boolean hundido=false;
        for (Barco barco : barcos) {
        	ArrayList<String> pos = barco.getPos();
			for (int i = 0; i <pos.size(); i++) {
				System.out.println("boton:"+btn);
				System.out.println("barco:"+barco.getPos().get(i));
				if (tocado!=true) {
					if (pos.get(i).equals(btn)) {
						tocado=true;
						barco.setVidas(barco.getVidas()-1);
						if (barco.getVidas()==0) {
							hundido=true;
							barco.setHundido(true);
							barco.setVidas(barco.getPos().size());
						}
					}
				}
			}
		}
       if(hundido==true){
        	btn = "Hundido";
       }else if(tocado==true){
    	   btn="Tocado";
       }else{
    	   btn="Agua";
       }

        return btn.getBytes();
    }

	public static void main(String[] args) {
		Servidor server = new Servidor();
		letras2 = new ArrayList<String>();
    	cargarLetras();
    	cargarPosicionesBarcos();
		try {
			server.init(9999);
			server.runServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cargarPosicionesBarcos(){
		barcos= new ArrayList<>();
    	String posX;
    	String posY;
    	Barco b;
    	for (int i = 0; i < 2; i++) {
    		if (i==0) {
    			b = new Barco(i+1);
        		b.setNom(String.valueOf(i));
    			posX=calcularPosX(i);
    			b.addPos(posX);
			}else{
				boolean igual=true;
				b = new Barco(i+1);
				do {
		    		b.setNom(String.valueOf(i));
					posX=calcularPosX(i);
					for (Barco barco : barcos) {
						for (int j = 0; j <barco.getPos().size(); j++) {
							if (!barco.getPos().get(j).equals(posX)) {
								igual=false;
							}else{
								System.out.println("SON IGUALES");
							}
						}
					}
				} while (igual!=false);
				b.addPos(posX);
			}

			if (i==1) {
				boolean igual=true;
				do {
					posY=calcularPosY(i+1);
					for (Barco barco : barcos) {
						for (int j = 0; j <barco.getPos().size(); j++) {
							if (!barco.getPos().get(j).equals(posY)) {
								igual=false;
							}else{
								System.out.println("SON IGUALES");
							}
						}
					}
				} while (igual!=false);
				b.addPos(posY);
			}
			b.to_String();
			barcos.add(b);
    	}
	}

	public static void cargarLetras(){
    	for (char i = 'A'; i <='Z'; i++) {
			letras2.add(String.valueOf(i));
		}
    }

    public static String calcularPosX(int i){
   	 String letra=null;
   	if (i==1) {
   		boolean dif=false;
   		do {
   			n= (int) (Math.random()*x);
	   	    	 int letraRandom=(int) (Math.random()*x);
	   	    	 int j=0;
	   	    	 l = null;
	   		    	for (String string : letras) {
	   					if (j==letraRandom) {
	   						l=string;
	   					}
	   					j++;
	   				}
	   		    letra= n + l;
	   		    for (Barco barco : barcos) {
					for (String string : barco.getPos()) {
						dif=true;
						if (string.equals(letra)) {
							dif=false;
						}
					}
				}
			} while (dif!=true);

   	}else{
			 n= (int) (Math.random()*x);
	    	 int letraRandom=(int) (Math.random()*x);
	    	 int j=0;
	    	 l = null;
		    	for (String string : letras) {
					if (j==letraRandom) {
						l=string;
					}
					j++;
				}
	    	letra= n + l;
		}

   	 return letra;
   }

   public static String calcularPosY(int i){

   	String posY=null;
   	int j=1;
   	String letraFinal = null;
   	for (String string : letras) {
			if (j==y) {
				letraFinal=string;
			}
			j++;
		}
   	if (l.equals("A")&& n==0) {
   		int r=(int) (Math.random()*2);
   		if (r==0) {
				posY= 1 + "A";
			}else if(r==1){
				posY= 0 + "B";
			}
		}else if(l.equals("A")&& n==x-1){
			int r=(int) (Math.random()*2);
   		if (r==0) {
				posY= (x - 2) + "A";
			}else if(r==1){
				posY= (x - 1) + "B";
			}
		}else if(l.equals("A")){
			int r=(int) (Math.random()*3);
   		if (r==0) {
				posY= (n - 1) + "A";
			}else if(r==1){
				posY= (n + 1) + "A";
			}else if(r==2){
				posY= n + "B";
			}
		}else if(l.equals(letraFinal)&& n==0){
			int r=(int) (Math.random()*2);
   		if (r==0) {
				posY= 1 + letraFinal;
			}else if(r==1){
				posY= 0 + "D";
			}
		}else if(l.equals(letraFinal)&& n==x-1){
			int r=(int) (Math.random()*2);
   		if (r==0) {
				posY= (x - 2) + letraFinal;
			}else if(r==1){
				posY= (x - 1) + "D";
			}
		}else if(l.equals(letraFinal)){
			int r=(int) (Math.random()*3);
   		if (r==0) {
				posY= (n - 1) + letraFinal;
			}else if(r==1){
				posY= (n + 1) + letraFinal;
			}else if(r==2){
				posY= n + "D";
			}
		}else if(n==0){
			int r=(int) (Math.random()*3);
   		if (r==0) {
   			String let=calcularLetraAbajo(l);
				posY= n + let;
			}else if(r==1){
				posY= (n + 1) + l;
			}else if(r==2){
				String let= calcularLetraArriba(l);
				posY= n + let;
			}
		}else if(n==x-1){
			int r=(int) (Math.random()*3);
   		if (r==0) {
   			String let=calcularLetraAbajo(l);
				posY= n + let;
			}else if(r==1){
				posY= (n - 1) + l;
			}else if(r==2){
				String let= calcularLetraArriba(l);
				posY= n + let;
			}
		}else{
				 int r=(int) (Math.random()*4);

				 if (r==0) {
					posY= (n + 1) + l;
				 }else if (r==1) {
					posY= (n - 1) + l;
				 }else if (r==2) {
					String letra=null;
					boolean trobat=false;
					for (String string : letras) {
						if (trobat) {
							letra=string;
							trobat=false;
						}
						if (l.equals(string)) {
							trobat=true;
						}
					}
					posY= n + letra;
				 }else if (r==3) {
					String letra=null;
					boolean trobat=false;
					for (String string : letras) {
						if (l.equals(string)) {
							trobat=true;
						}
						if (trobat==false) {
							letra=string;
						}
					}
					posY= n + letra;
				 	}
			}

   	return posY;
   }

   public static String calcularLetraArriba(String letra){
   	boolean trobat=false;
   	String l=null;
   	for (String string : letras2) {
			if (letra.equals(string)) {
				trobat=true;
			}
			if (trobat==false) {
				l=string;
			}
		}
   	return l;
   }

   public static String calcularLetraAbajo(String letra){
   	boolean trobat=false;
   	String l = null;
   	for (String string : letras2) {
			if (trobat) {
				l=string;
				trobat=false;
			}
			if (string.equals(letra)) {
				trobat=true;
			}
		}
   	return l;
   }

}
