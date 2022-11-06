package ar.edu.unlam.pb2;

public class Compu extends Dispositivo{
	private String sistemaOperativo;
	private String ip;
	private String localidad;
	
	//constructores
	

	
	
	public Compu(String sistemaOperativo, String localidad, String ip) {
		super();
		this.sistemaOperativo= sistemaOperativo;
		this.ip = ip ;
		this.localidad = localidad ;
	}
	
	//Getters n' Setters
	public String getSistemaOperativo() {
		return sistemaOperativo;
	}
	
	public void setSistemaOpertativo (String sistemaOperativo) {
		this.sistemaOperativo= sistemaOperativo;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
		
}
