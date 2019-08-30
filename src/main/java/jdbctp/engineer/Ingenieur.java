package jdbctp.engineer;

import java.util.Date;

public class Ingenieur {
	
	private int numMatricule;
	private String nom;
	private String prenom;
	private Date datenaissance;
	private String adresse;
	private String cp;
	private String ville;
	private String tel;
	private String sexe;
	private String situation;
	private double codeprojet;
	
	public int getNumMatricule() {
		return numMatricule;
	}
	public void setNumMatricule(int numMatricule) {
		this.numMatricule = numMatricule;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public Date getDatenaissance() {
		return datenaissance;
	}
	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	
	public String getVille() {
		return ville;
	}
	
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	
	public double getCodeprojet() {
		return codeprojet;
	}
	public void setCodeprojet(double codeprojet) {
		this.codeprojet = codeprojet;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Ingenieur [numMatricule=%s, nom=%s, prenom=%s, datenaissance=%s, adresse=%s, cp=%s, ville=%s, tel=%s, sex=%s, situation=%s, codeprojet=%s]",
				numMatricule, nom, prenom, datenaissance, adresse, cp, ville, tel, sexe, situation, codeprojet);
	}
	
}
