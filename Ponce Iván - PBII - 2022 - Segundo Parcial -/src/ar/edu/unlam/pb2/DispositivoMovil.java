package ar.edu.unlam.pb2;

public class DispositivoMovil extends Dispositivo{
	
	private String imei;
	private String biometrico;
	
	
	//getters n' setters
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getBiometrico() {
		return biometrico;
	}
	public void setBiometrico(String biometrico) {
		this.biometrico = biometrico;
	}
	public DispositivoMovil(String imei, String biometrico) {
		super();
		this.imei = imei;
		this.biometrico = biometrico;
	}
	public DispositivoMovil() {
		super();
	}
	
	
	

}
