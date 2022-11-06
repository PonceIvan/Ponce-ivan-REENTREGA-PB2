package ar.edu.unlam.pb2;

public class AltaUsuario extends NoMonetaria{
	
	String usuario;
	String contraseña;
	

	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	//Constructores

	public AltaUsuario(Dispositivo dispositivo,Cliente cliente,Banco banco, String usuario, String contraseña) {
		super(dispositivo,cliente,banco);
		this.usuario = usuario;
		this.contraseña = contraseña;
	}

	public AltaUsuario(Dispositivo dispositivo,Cliente cliente, Banco banco) {
		super(dispositivo,cliente, banco); 
	}
	
	

}
