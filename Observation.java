package gestionMachines;

import java.util.Date;

public class Observation 
{
	String id_observation;
	Date date_ajout;
	public Observation(String id_observation, Date date_ajout) {
		super();
		this.id_observation = id_observation;
		this.date_ajout = date_ajout;
	}
	public String getId_observation() {
		return id_observation;
	}
	public void setId_observation(String id_observation) {
		this.id_observation = id_observation;
	}
	public Date getDate_ajout() {
		return date_ajout;
	}
	public void setDate_ajout(Date date_ajout) {
		this.date_ajout = date_ajout;
	}
	
	

}
