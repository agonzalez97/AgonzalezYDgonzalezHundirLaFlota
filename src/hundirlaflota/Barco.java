package hundirlaflota;
import java.util.ArrayList;

public class Barco {
	String nom;
	ArrayList<String>pos;
	int vidas;
	boolean hundido;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<String> getPos() {
		return pos;
	}

	public void setPos(ArrayList<String> pos) {
		this.pos = pos;
	}

	public void addPos(String s){
		pos.add(s);
	}
	
	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public boolean isHundido() {
		return hundido;
	}

	public void setHundido(boolean hundido) {
		this.hundido = hundido;
	}

	public Barco(int vidas){
		pos=new ArrayList<String>();
		this.vidas=vidas;
		this.hundido =false;
	}
	

	public String to_String() {
		System.out.println("barco: "+ nom);
		for (int i = 0; i < pos.size(); i++) {
			System.out.println(pos.get(i));
		}
		return null;
	}

}

