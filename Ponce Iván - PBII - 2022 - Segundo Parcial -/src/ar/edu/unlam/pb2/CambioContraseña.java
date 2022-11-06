package ar.edu.unlam.pb2;

public class CambioContraseña extends NoMonetaria implements Monitoreable{
	
	String usuario;
	String contraseñaAnterior;
	String contraseñaNueva;
	
	public CambioContraseña(Dispositivo dispositivo, Cliente cliente, Banco banco, String usuario, String contraseñaAnterior,
			String contraseñaNueva) {
		super(dispositivo,cliente, banco); 
		this.usuario = usuario;
		this.contraseñaAnterior = contraseñaAnterior;
		this.contraseñaNueva = contraseñaNueva;
	}
	
	//Getters and setters
	public String getUsuario() {
		return usuario;
	}
  public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getContraseñaAnterior() {
		return contraseñaAnterior;
	}
	public void setContraseñaAnterior(String contraseñaAnterior) {
		this.contraseñaAnterior = contraseñaAnterior;
	}

	public String getContraseñaNueva() {
		return contraseñaNueva;
	}

    public void setContraseñaNueva(String contraseñaNueva) {
		this.contraseñaNueva = contraseñaNueva;
	}

	

}
