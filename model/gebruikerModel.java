package model;

public class gebruikerModel {
	private int gebruikerId;
	
	private String gebruikerVoornaam;
	private String gebruikerAchternaam;
	private String gebruikerWachtwoord;
	private String gebruikerEmail;
	
	private int gebruikerTotaalUren;
	private int gebruikerSleutel;
	private int gebruikerRol;
	
	public gebruikerModel(int id, String vnaam, String anaam, String wachtwoord, String email, int totaaluren, int sleutel, int rol){
		this.gebruikerId = id;
		this.gebruikerVoornaam = vnaam;
		this.gebruikerAchternaam = anaam;
		this.gebruikerWachtwoord = wachtwoord;
		this.gebruikerEmail = email;
		this.gebruikerTotaalUren = totaaluren;
		this.gebruikerSleutel = sleutel;
		this.gebruikerRol = rol;
	}
	
	public int getGebruikerId() {
		return gebruikerId;
	}
	public void setGebruikerId(int gebruikerId) {
		this.gebruikerId = gebruikerId;
	}
	public String getGebruikerVoornaam() {
		return gebruikerVoornaam;
	}
	public void setGebruikerVoornaam(String gebruikerVoornaam) {
		this.gebruikerVoornaam = gebruikerVoornaam;
	}
	public String getGebruikerAchternaam() {
		return gebruikerAchternaam;
	}
	public void setGebruikerAchternaam(String gebruikerAchternaam) {
		this.gebruikerAchternaam = gebruikerAchternaam;
	}
	public String getGebruikerWachtwoord() {
		return gebruikerWachtwoord;
	}
	public void setGebruikerWachtwoord(String gebruikerWachtwoord) {
		this.gebruikerWachtwoord = gebruikerWachtwoord;
	}
	public String getGebruikerEmail() {
		return gebruikerEmail;
	}
	public void setGebruikerEmail(String gebruikerEmail) {
		this.gebruikerEmail = gebruikerEmail;
	}
	public int getGebruikerTotaalUren() {
		return gebruikerTotaalUren;
	}
	public void setGebruikerTotaalUren(int gebruikerTotaalUren) {
		this.gebruikerTotaalUren = gebruikerTotaalUren;
	}
	public int getGebruikerSleutel() {
		return gebruikerSleutel;
	}
	public void setGebruikerSleutel(int gebruikerSleutel) {
		this.gebruikerSleutel = gebruikerSleutel;
	}
	public int getGebruikerRol() {
		return gebruikerRol;
	}
	public void setGebruikerRol(int gebruikerRol) {
		this.gebruikerRol = gebruikerRol;
	}
}
