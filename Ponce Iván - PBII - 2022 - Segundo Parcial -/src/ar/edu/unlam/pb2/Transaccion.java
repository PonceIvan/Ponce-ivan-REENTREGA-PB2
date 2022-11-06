package ar.edu.unlam.pb2;

public class Transaccion {
	
	private Cliente cliente;	
	private Dispositivo dispositivo;
	private Banco banco;
	
	public Transaccion(Dispositivo dispositivo, Cliente cliente, Banco banco) {
		this.dispositivo = dispositivo;
		this.cliente = cliente;
		this.banco = banco;
	}
	

	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

   public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}



	public Banco getBanco() {
		return banco;
	}



	
	
	
}
