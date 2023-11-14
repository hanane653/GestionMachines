package gestionMachines;

public class Machine 
{
	public String Nom;
	public String type;
	String etat;
	public Machine(String nom, String type,String etat) {
		super();
		Nom = nom;
		this.type = type;
		this.etat=etat;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	

}
